package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mensaje mensaje= new Mensaje("Hola Mundo!");
        db.collection("message").document("Texto").set(mensaje);

        EditText txt_erabiltzailea = findViewById(R.id.txt_erabiltzailea);
        EditText txt_pasahitza = findViewById(R.id.txt_pasahitza);
        Button btn_login = findViewById(R.id.btn_login);
        TextView lbl_erregistratzea = (TextView) findViewById(R.id.lbl_erregistratzea);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Hasiera.class);
                startActivity(intent);
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