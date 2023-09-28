package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hasiera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasiera);

        TextView txt_saioa = (TextView) findViewById(R.id.txt_HasieraInicio);
        TextView txt_erregistroa = (TextView) findViewById(R.id.txt_HasieraRegistro);
        TextView txt_noticia = (TextView) findViewById(R.id.txt_bocetoH);

        txt_erregistroa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Erregistroa.class);
                startActivity(intent);
            }
        });
        txt_saioa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hasiera.this, Login.class);
                startActivity(intent);
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

}