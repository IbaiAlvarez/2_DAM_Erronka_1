package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;

public class Kirolak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Spinner spinnerKirolak_Kirola=findViewById(R.id.spin_K1);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.kirolak, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerKirolak_Kirola.setAdapter(adapter);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);
    }
}