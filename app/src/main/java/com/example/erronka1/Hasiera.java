package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Hasiera extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    // shared pref
    SharedPreferences sharedpreferences;
    // deklarazio string shared pref
    public static final String SHARED_PREFS = "shared_prefs";

    // deklarazio strings datuak shared pref
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";

    //String, Button, Text view deklarazioa
    String email;
    TextView txt_LoginHasiera;
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

        //Hasieraketa
        txt_LoginHasiera = (TextView) findViewById(R.id.txt_LoginHasiera);
        txt_erregistroa = (TextView) findViewById(R.id.txt_HasieraRegistro);
        txt_HasieraBarra = (TextView) findViewById(R.id.txt_HasieraBarra);
        lbl_hasieraErabiltzaile =  (TextView) findViewById(R.id.lbl_hasieraErabiltzaile);
        lbl_saioaItxi = (TextView) findViewById(R.id.lbl_saioaItxi);
        btn_erreserbaEgin = findViewById(R.id.btn_erreserbaEgin);
        btn_ezabatu = findViewById(R.id.btn_ezabatu);

        //Authentication instantzia lortu
        mAuth = FirebaseAuth.getInstance();
        //Erabiltzailea lortzen du
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        // Hasieraketa shared pref
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // Shared pref emaila gorde
        email = sharedpreferences.getString("email_key", "");

        //LBL saioa itxi
        lbl_saioaItxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saioa itxi FirebaseAuth
                mAuth.signOut();
                // Editor shared pref
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                //Aktibitatea freskatzen du
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
        txt_LoginHasiera.setOnClickListener(new View.OnClickListener() {
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
                if(email.equals("")) {
                    Toast.makeText(Hasiera.this, "Tienes que estar registrado para realizar una reserva.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Hasiera.this, Eskaintzak.class);
                    startActivity(intent);
                }
            }
        });

    }

    //UI aldatu
    public void updateUI(FirebaseUser account) {
        //Erabiltzailea anonimoa ez bada
        if (account != null && !account.getEmail().equals("")) {
            db.collection("erabiltzaileak").document(account.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                        //Erabiltzailea bada admin menura bidaltzen du
                        if (document.get("erabiltzaile_mota").toString().equals("administratzailea")) {
                            Intent intent = new Intent(Hasiera.this, MenuAdmin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            lbl_hasieraErabiltzaile.setText("Bienvenido " + account.getEmail());
                            lbl_hasieraErabiltzaile.setVisibility(View.VISIBLE);
                            btn_ezabatu.setVisibility(View.VISIBLE);
                            btn_ezabatu.setEnabled(true);
                            txt_LoginHasiera.setVisibility(View.INVISIBLE);
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
        else if (account == null || account.getEmail().equals("")) {
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
            btn_ezabatu.setVisibility(View.INVISIBLE);
            btn_ezabatu.setEnabled(false);
            txt_LoginHasiera.setVisibility(View.VISIBLE);
            txt_HasieraBarra.setVisibility(View.VISIBLE);
            txt_erregistroa.setVisibility(View.VISIBLE);
        }

        btn_ezabatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// inflate the layout of the popup window
                popUpErakutsi(account);
            }
        });
    }

    //Berrespen leioa erakutzi
    public void popUpErakutsi(FirebaseUser account){
        // Crea un AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(Hasiera.this);
        builder.setTitle("Confirmar Borrado de Cuenta");
        builder.setMessage("Esta seguro de querer eliminar su cuenta?");

        // Agrega un botón "Aceptar" para cerrar el diálogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.collection("erabiltzaileak").document(email).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                account.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Hasiera.this, "La cuenta se ha eliminado correctamente.",
                                                    Toast.LENGTH_SHORT).show();
                                            //Saio itxi FirebaseAuth
                                            mAuth.signOut();
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.clear();
                                            editor.apply();

                                            Intent intent = new Intent(Hasiera.this, Hasiera.class);
                                            startActivity(intent);
                                            finish();
                                            startActivity(getIntent());

                                            //Leioa itxi
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Hasiera.this, "Error al eliminar la cuenta.",
                                        Toast.LENGTH_SHORT).show();
                                //Leioa itxi
                                dialog.dismiss();
                            }
                        });

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Leioa itxi
                dialog.dismiss();
            }
        });

        // AlertDialog erakutsi
        builder.create().show();
    }
}