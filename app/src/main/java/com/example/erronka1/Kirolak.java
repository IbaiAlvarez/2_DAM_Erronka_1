package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;

public class Kirolak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Spinner spinnerKirolak_Kirola=findViewById(R.id.spin_K1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

        Button btn_errserbaK = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);

        /*btn_errserbaK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Kirolak.this, .class);
            }
        });*/
        btn_atzeraK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kirolak.this, Eskaintzak.class);
            }
        });
    }
}