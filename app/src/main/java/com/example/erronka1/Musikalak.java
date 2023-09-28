package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Musikalak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musikalak);

        Button btn_errserbaM = (Button) findViewById(R.id.btn_ErreserbaM);
        Button btn_atzeraM = (Button) findViewById(R.id.btn_AtzeraM);

         /*btn_errserbaAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Antzerkiak.this, .class);
            }
        });*/
        btn_atzeraM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Musikalak.this, Eskaintzak.class);
            }
        });
    }
}