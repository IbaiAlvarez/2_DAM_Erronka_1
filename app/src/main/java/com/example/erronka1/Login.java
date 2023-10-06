package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;


public class Login extends AppCompatActivity {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText txt_erabiltzailea = findViewById(R.id.txt_erabiltzailea);
        EditText txt_pasahitza = findViewById(R.id.txt_pasahitza);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setEnabled(false);
        TextView lbl_erregistratzea = (TextView) findViewById(R.id.lbl_erregistratzea);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signInWithEmailAndPassword(txt_erabiltzailea.getText().toString(), txt_pasahitza.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Erabiltzailea zuzena bada
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    DocumentReference docRef = db.collection("erabiltzaileak").document(txt_erabiltzailea.getText().toString());
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                                        if(document.get("erabiltzaile_mota").toString().equals("erabiltzailea")){
                                                            Intent intent = new Intent(Login.this, Hasiera.class);
                                                            startActivity(intent);
                                                        }else if(document.get("erabiltzaile_mota").toString().equals("administratzailea")){
                                                            Intent intent = new Intent(Login.this, MenuAdmin.class);
                                                            startActivity(intent);
                                                        }
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                                //Erabiltzailea okerra bada
                                else {
                                    // If sign in fails, display a message to the user.
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