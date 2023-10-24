package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Pertsona;

public class InfoErabiltzailea extends AppCompatActivity {

    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_erabiltzailea);
        mAuth = FirebaseAuth.getInstance();

        Button btn_erabiltzaileAldatu = (Button) findViewById(R.id.btn_erabiltzaileAldatu);
        Button btn_AtzeraInfo = (Button) findViewById(R.id.btn_AtzeraInfoErabiltzaile);

        Intent i = getIntent();
        Pertsona erabiltzailea = (Pertsona) i.getSerializableExtra("erabiltzailea");

        EditText txt_email_InfoErabiltzailea = findViewById(R.id.txt_email_InfoErabiltzailea);
        EditText txt_izen_InfoErabiltzaile = findViewById(R.id.txt_izen_InfoErabiltzaile);
        EditText txt_abizen_InfoErabiltzaile = findViewById(R.id.txt_abizen_InfoErabiltzaile);
        EditText txt_erabiltzaile_InfoErabiltzaile = findViewById(R.id.txt_erabiltzaile_InfoErabiltzaile);

        txt_izen_InfoErabiltzaile.setText(erabiltzailea.getIzena());
        txt_abizen_InfoErabiltzaile.setText(erabiltzailea.getAbizena());
        txt_email_InfoErabiltzailea.setText(erabiltzailea.getEmail());
        txt_email_InfoErabiltzailea.setEnabled(false);
        txt_erabiltzaile_InfoErabiltzaile.setText(erabiltzailea.getErabiltzaile_nick());
        btn_erabiltzaileAldatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 erabiltzailea.setIzena(txt_izen_InfoErabiltzaile.getText().toString());
                 erabiltzailea.setAbizena(txt_abizen_InfoErabiltzaile.getText().toString());
                 erabiltzailea.setErabiltzaile_nick(txt_erabiltzaile_InfoErabiltzaile.getText().toString());
                 db.collection("erabiltzaileak").document(erabiltzailea.getEmail()).set(erabiltzailea);
                 Intent intent = new Intent(InfoErabiltzailea.this, AdminUsuarios.class);
                 startActivity(intent);
            }
        });
        btn_AtzeraInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoErabiltzailea.this, AdminUsuarios.class);
                startActivity(intent);
            }
        });
    }
}