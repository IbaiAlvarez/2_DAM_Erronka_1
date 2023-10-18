package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class Ordainketa extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordainketa);
        Button btn_atzeraO = (Button) findViewById(R.id.btn_atzeraO);

        //Recupera los datos de Intent
        Intent i = getIntent();
        Erreserba erreserba = (Erreserba) getIntent().getSerializableExtra("erreserba");
        String kirola = getIntent().getStringExtra("kirola");
        String zelaia = getIntent().getStringExtra("zelaia");


        TextView lbl_aukeratutakoKirola = findViewById(R.id.lbl_aukeratutakoKirola);
        TextView lbl_aukeratutakoZelaia = findViewById(R.id.lbl_aukeratutakoZelaia);
        TextView lbl_aukeratutakoData = findViewById(R.id.lbl_aukeratutakoData);
        TextView lbl_aukeratutakoOrdua = findViewById(R.id.lbl_aukeratutakoOrdua);
        Button btn_ordainduO = findViewById(R.id.btn_ordainduO);

        lbl_aukeratutakoKirola.setText("Kirola: "+kirola);
        lbl_aukeratutakoZelaia.setText("Zelaia: "+zelaia);
        lbl_aukeratutakoData.setText("Data: "+erreserba.data);
        lbl_aukeratutakoOrdua.setText("Ordua: "+erreserba.ordua);

        btn_atzeraO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prueba = "Eskaintzak";
                Intent intent = new Intent(Ordainketa.this, Eskaintzak.class);
                startActivity(intent);
            }
        });

        btn_ordainduO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //let query=db.collection("kirolak").document(kirola).set(erreserba);
                String prueba = "Eskaintzak";
                Intent intent = new Intent(Ordainketa.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}