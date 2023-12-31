package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Eskaintzak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eskaintzak);

        //Intent intent = getIntent();
        Button btn_atzeraEsk = (Button) findViewById(R.id.btn_atzera_kategoriak);
        Button btn_kirola = (Button) findViewById(R.id.btn_kirolak);
        Button btn_musika = (Button) findViewById(R.id.btn_musika);
        Button btn_antzerki = (Button) findViewById(R.id.btn_antzerki);
        Button btn_zinema = (Button) findViewById(R.id.btn_zine);

        btn_kirola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eskaintzak.this, Kirolak.class);
                startActivity(intent);            }
        });
        btn_musika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eskaintzak.this, Musikalak.class);
                startActivity(intent);
            }
        });
        btn_antzerki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eskaintzak.this, Antzerkiak.class);
                startActivity(intent);
            }
        });
        btn_zinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eskaintzak.this, Zinema.class);
                startActivity(intent);
            }
        });
        btn_atzeraEsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eskaintzak.this, Hasiera.class);
                startActivity(intent);
            }
        });
    }
}