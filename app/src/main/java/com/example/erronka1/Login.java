package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;


public class Login extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    // shared pref
    SharedPreferences sharedpreferences;
    String email, password;

    // deklarazio strings datuak shared pref
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Authentication instantzia lortu
        mAuth = FirebaseAuth.getInstance();

        //Hasieraketa
        EditText txt_erabiltzailea = findViewById(R.id.txt_erabiltzailea);
        EditText txt_pasahitza = findViewById(R.id.txt_pasahitza);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setEnabled(false);
        TextView lbl_erregistratzea = (TextView) findViewById(R.id.lbl_erregistratzea);
        ImageView etxea_ikono_login = (ImageView) findViewById(R.id.etxea_ikono_login);

        // Hasieraketa shared pref
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedpreferences.getString("email_key", null);
        password = sharedpreferences.getString("password_key", null);

        txt_erabiltzailea.setText(sharedpreferences.getString("email_key", "").toString());
        txt_pasahitza.setText(sharedpreferences.getString("password_key", "").toString());


        etxea_ikono_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Hasiera.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sioa itxi FirebaseAuth
                mAuth.signOut();

                mAuth.signInWithEmailAndPassword(txt_erabiltzailea.getText().toString(), txt_pasahitza.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Erabiltzailea zuzena bada
                                if (task.isSuccessful()) {
                                    DocumentReference docRef = db.collection("erabiltzaileak").document(txt_erabiltzailea.getText().toString());
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                                        // shared pref gordetzen ditu email eta pasahitza
                                                        editor.putString(EMAIL_KEY, txt_erabiltzailea.getText().toString());
                                                        editor.putString(PASSWORD_KEY, txt_pasahitza.getText().toString());
                                                        editor.apply();

                                                        DocumentSnapshot document = task.getResult();
                                                        String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                                        if(document.get("erabiltzaile_mota").toString().equals("erabiltzailea")){
                                                            Intent intent = new Intent(Login.this, Hasiera.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }else if(document.get("erabiltzaile_mota").toString().equals("administratzailea")){
                                                            Intent intent = new Intent(Login.this, MenuAdmin.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                                //Erabiltzailea okerra bada
                                else {
                                    Toast.makeText(Login.this, "Erabiltzaile edo pasahitza okerrak.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        txt_erabiltzailea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txt_erabiltzailea.getText().toString().equals("")){
                    btn_login.setEnabled(false);
                }else if(!txt_erabiltzailea.getText().toString().equals("") && !txt_pasahitza.getText().toString().equals("")){
                    btn_login.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txt_pasahitza.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txt_pasahitza.getText().toString().equals("")){
                    btn_login.setEnabled(false);
                }else if(!txt_erabiltzailea.getText().toString().equals("") && !txt_pasahitza.getText().toString().equals("")){
                    btn_login.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        lbl_erregistratzea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Erregistroa.class);
                startActivity(intent);
            }
        });
    }
}