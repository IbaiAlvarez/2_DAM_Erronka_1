package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Hasiera extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasiera);


        txt_saioa = (TextView) findViewById(R.id.txt_HasieraInicio);
        txt_erregistroa = (TextView) findViewById(R.id.txt_HasieraRegistro);
        txt_HasieraBarra = (TextView) findViewById(R.id.txt_HasieraBarra);
        lbl_hasieraErabiltzaile =  (TextView) findViewById(R.id.lbl_hasieraErabiltzaile);
        lbl_saioaItxi = (TextView) findViewById(R.id.lbl_saioaItxi);
        TextView txt_noticia = (TextView) findViewById(R.id.txt_bocetoH);


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

        txt_noticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Eskaintzak.class);
                startActivity(intent);
            }
        });

    }

    //Change UI according to user data.
    public void updateUI(FirebaseUser account){

        if(account != null){
            lbl_hasieraErabiltzaile.setText("Bienvenido "+account.getEmail());
            lbl_hasieraErabiltzaile.setVisibility(View.VISIBLE);
            lbl_saioaItxi.setVisibility(View.VISIBLE);
            txt_saioa.setVisibility(View.INVISIBLE);
            txt_HasieraBarra.setVisibility(View.INVISIBLE);
            txt_erregistroa.setVisibility(View.INVISIBLE);
        }
        //Anonimo bezala logeatzen da erabiltzaile bat logeatuta ez badago
        else if(account == null){
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
            txt_saioa.setVisibility(View.VISIBLE);
            txt_HasieraBarra.setVisibility(View.VISIBLE);
            txt_erregistroa.setVisibility(View.VISIBLE);
        }
    }

}