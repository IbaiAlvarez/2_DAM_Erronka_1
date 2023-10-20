package com.example.erronka1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfoErabiltzailea extends AppCompatActivity {

    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_erabiltzailea);
        mAuth = FirebaseAuth.getInstance();

        Button btn_erabiltzaileAldatu = (Button) findViewById(R.id.btn_erabiltzaileAldatu);
        Button btn_erabiltzaileEzabatu = (Button) findViewById(R.id.btn_erabiltzaileEzabatu);
        Button btn_AtzeraInfo = (Button) findViewById(R.id.btn_AtzeraInfoErabiltzaile);

        Intent i = getIntent();
        String izena = getIntent().getStringExtra("izena");
        String abizena = getIntent().getStringExtra("abizena");
        String email = getIntent().getStringExtra("email");
        String erabiltzaile_nick = getIntent().getStringExtra("erabiltzaile_nick");

        EditText txt_email_InfoErabiltzailea = findViewById(R.id.txt_email_InfoErabiltzailea);
        EditText txt_izen_InfoErabiltzaile = findViewById(R.id.txt_izen_InfoErabiltzaile);
        EditText txt_abizen_InfoErabiltzaile = findViewById(R.id.txt_abizen_InfoErabiltzaile);
        EditText txt_erabiltzaile_InfoErabiltzaile = findViewById(R.id.txt_erabiltzaile_InfoErabiltzaile);

        txt_izen_InfoErabiltzaile.setText(izena);
        txt_abizen_InfoErabiltzaile.setText(abizena);
        txt_email_InfoErabiltzailea.setText(email);
        txt_erabiltzaile_InfoErabiltzaile.setText(erabiltzaile_nick);
        btn_erabiltzaileEzabatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("erabiltzaileak").document(email)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(InfoErabiltzailea.this, "Eliminado correctamente",
                                    Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InfoErabiltzailea.this, AdminUsuarios.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });

            }
        });
        /*btn_erabiltzaileAldatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.

                mAuth.createUserWithEmailAndPassword(txt_email_InfoErabiltzailea.getText().toString(), txt_pasahitza_erregistro.getText().toString())
                        .addOnCompleteListener(InfoErabiltzailea.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Si el registro en authentication es correcto
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Pertsona pertsona = new Pertsona(txt_izen_InfoErabiltzaile.getText().toString(),txt_abizen_InfoErabiltzaile.getText().toString(),txt_email_InfoErabiltzailea.getText().toString(),txt_erabiltzaile_InfoErabiltzaile.getText().toString(),"erabiltzailea","aktiboa");

                                    db.collection("erabiltzaileak").document(pertsona.getEmail()).set(pertsona);

                                }
                                //Si el registro en authentication da error
                                else {
                                    if(task.getException().getLocalizedMessage().toString().equals("The email address is already in use by another account.")) {
                                        Toast.makeText(InfoErabiltzailea.this, "Este correo ya esta registrado.",
                                                Toast.LENGTH_SHORT).show();
                                    }else if(task.getException().getLocalizedMessage().toString().equals("The email address is badly formatted.")){
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(InfoErabiltzailea.this, "Correo no valido.",
                                                Toast.LENGTH_SHORT).show();
                                    }else if(task.getException().getLocalizedMessage().toString().equals("The given password is invalid. [ Password should be at least 6 characters ]")){
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(InfoErabiltzailea.this, "La contraseña tiene que tener mínimo 6 carácteres.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        }););*/
        btn_AtzeraInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoErabiltzailea.this, AdminUsuarios.class);
                startActivity(intent);
            }
        });
    }
}