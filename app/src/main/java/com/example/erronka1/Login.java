package com.example.erronka1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        db.collection("message").document("Texto").set(mensaje);
         */
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText txt_erabiltzailea = findViewById(R.id.txt_erabiltzailea);
        EditText txt_pasahitza = findViewById(R.id.txt_pasahitza);
        Button btn_login = findViewById(R.id.btn_login);
        TextView lbl_erregistratzea = (TextView) findViewById(R.id.lbl_erregistratzea);

        //Verifica Login del usuario
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signInWithEmailAndPassword(txt_erabiltzailea.getText().toString(), txt_pasahitza.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(Login.this, Hasiera.class);
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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