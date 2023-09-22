package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Button btn_registrar = findViewById(R.id.btn_registrar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edt_erabiltzaiela = findViewById(R.id.edt_erabiltzaiela);
        EditText edt_pasahitza = findViewById(R.id.edt_pasahitza);
        Button btn_iniciar = findViewById(R.id.btn_iniciar);
        Button btn_registrar = findViewById(R.id.btn_registrar);
        Button btn_ofertas = findViewById(R.id.btn_ofertas);

        btn_ofertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Eskaintzak.class);

                startActivity(intent);
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Erregistroa.class);
                startActivity(intent);
            }
        });
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Eskaintzak.class);
                startActivity(intent);
            }
        });

    }






}