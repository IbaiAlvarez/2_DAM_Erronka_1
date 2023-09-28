package com.example.erronka1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class Erregistroa extends AppCompatActivity {


    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erregistroa);

        mAuth = FirebaseAuth.getInstance();

        EditText txt_izen_erregistro = findViewById(R.id.txt_izen_erregistro);
        EditText txt_abizen_erregistro = findViewById(R.id.txt_abizen_erregistro);
        EditText txt_email_erregistro = findViewById(R.id.txt_email_erregistro);
        EditText txt_erabiltzaile_erregistro = findViewById(R.id.txt_erabiltzaile_erregistro);
        EditText txt_pasahitza_erregistro = findViewById(R.id.txt_pasahitza_erregistro);
        Button btn_erregistratu_erregistroa = findViewById(R.id.btn_erregistratu_erregistroa);
        TextView lbl_logeatzea = findViewById(R.id.lbl_logeatzea);

        btn_erregistratu_erregistroa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(txt_email_erregistro.getText().toString(), txt_pasahitza_erregistro.getText().toString())
                    .addOnCompleteListener(Erregistroa.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();



                                Intent intent = new Intent(Erregistroa.this, Hasiera.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Erregistroa.this, "Este correo ya esta registrado.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });

        lbl_logeatzea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Erregistroa.this, Login.class);
                startActivity(intent);
            }
        });
    }
}