package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Zinema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zinema);

        Button btn_errserbaZ = (Button) findViewById(R.id.btn_ErreserbaZ);
        Button btn_atzeraZ = (Button) findViewById(R.id.btn_AtzeraZ);

         /*btn_errserbaAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Zinema.this, .class);
            }
        });*/
        btn_atzeraZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zinema.this, Eskaintzak.class);
            }
        });
    }
}