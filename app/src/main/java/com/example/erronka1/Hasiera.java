package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Hasiera extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email;
    TextView lbl_prueba;
    TextView txt_saioa;
    TextView lbl_saioaItxi;
    TextView txt_HasieraBarra;
    TextView txt_erregistroa;
    TextView lbl_hasieraErabiltzaile;
    Button btn_erreserbaEgin;
    Button btn_ezabatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasiera);

        txt_saioa = (TextView) findViewById(R.id.txt_HasieraInicio);
        txt_erregistroa = (TextView) findViewById(R.id.txt_HasieraRegistro);
        txt_HasieraBarra = (TextView) findViewById(R.id.txt_HasieraBarra);
        lbl_hasieraErabiltzaile =  (TextView) findViewById(R.id.lbl_hasieraErabiltzaile);
        lbl_saioaItxi = (TextView) findViewById(R.id.lbl_saioaItxi);
        TextView txt_noticia = (TextView) findViewById(R.id.lbl_hasieraInfo1);
        btn_erreserbaEgin = findViewById(R.id.btn_erreserbaEgin);
        btn_ezabatu = findViewById(R.id.btn_ezabatu);


        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences.getString("email_key", "");


        lbl_saioaItxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cierra sesion de FirebaseAuth
                mAuth.signOut();
                // calling method to edit values in shared prefs.
                SharedPreferences.Editor editor = sharedpreferences.edit();

                // below line will clear
                // the data in shared prefs.
                editor.clear();

                // below line will apply empty
                // data to shared prefs.
                editor.apply();


                Intent intent = new Intent(Hasiera.this, Hasiera.class);
                startActivity(intent);
                finish();
                startActivity(getIntent());
            }
        });

        txt_erregistroa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Erregistroa.class);
                startActivity(intent);
                finish();
            }
        });
        txt_saioa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_erreserbaEgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Eskaintzak.class);
                startActivity(intent);
            }
        });

    }

    //Change UI according to user data.
    public void updateUI(FirebaseUser account){

         if(account != null && !account.getEmail().equals("")){
            db.collection("erabiltzaileak").document(account.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                        if(document.get("erabiltzaile_mota").toString().equals("administratzailea")){
                            Intent intent = new Intent(Hasiera.this, MenuAdmin.class);
                            startActivity(intent);
                            finish();
                        }else {
                            lbl_hasieraErabiltzaile.setText("Bienvenido " + account.getEmail());
                            lbl_hasieraErabiltzaile.setVisibility(View.VISIBLE);
                            lbl_saioaItxi.setVisibility(View.VISIBLE);
                            btn_erreserbaEgin.setEnabled(true);
                            btn_ezabatu.setVisibility(View.VISIBLE);
                            btn_ezabatu.setEnabled(true);
                            txt_saioa.setVisibility(View.INVISIBLE);
                            txt_HasieraBarra.setVisibility(View.INVISIBLE);
                            txt_erregistroa.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
        }
        //Anonimo bezala logeatzen da erabiltzaile bat logeatuta ez badago
        else if(account == null || account.getEmail().equals("")){
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInAnonymously:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInAnonymously:failure", task.getException());
                            }
                        }
                    });
            lbl_hasieraErabiltzaile.setVisibility(View.INVISIBLE);
            lbl_saioaItxi.setVisibility(View.INVISIBLE);
            btn_erreserbaEgin.setEnabled(false);
             btn_ezabatu.setVisibility(View.INVISIBLE);
             btn_ezabatu.setEnabled(false);
            txt_saioa.setVisibility(View.VISIBLE);
            txt_HasieraBarra.setVisibility(View.VISIBLE);
            txt_erregistroa.setVisibility(View.VISIBLE);
        }
    }

}