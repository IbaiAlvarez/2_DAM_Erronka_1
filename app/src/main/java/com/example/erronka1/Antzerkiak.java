package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Antzerkiak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antzerkiak);

        Button btn_errserbaAt = (Button) findViewById(R.id.btn_ErreserbaAntzerki);
        Button btn_atzeraAt = (Button) findViewById(R.id.btn_AtzeraAntzerki);

        /*btn_errserbaAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Antzerkiak.this, .class);
            }
        });*/
        btn_atzeraAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Antzerkiak.this, Eskaintzak.class);
            }
        });
    }
}