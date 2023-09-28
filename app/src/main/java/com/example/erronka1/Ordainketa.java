package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ordainketa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordainketa);
        Button btn_atzeraO = (Button) findViewById(R.id.btn_atzeraO);

        btn_atzeraO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prueba = "Eskaintzak";
                Intent intent = new Intent(Ordainketa.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}