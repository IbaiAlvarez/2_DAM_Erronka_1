package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErreserbaKudeaketa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreserba_kudeaketa);
        Button btn_atzeraErreserbak = (Button) findViewById(R.id.btn_AtzeraErreserbak);
        btn_atzeraErreserbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErreserbaKudeaketa.this, MenuAdmin.class);
                startActivity(intent);
            }
        });
    }
}