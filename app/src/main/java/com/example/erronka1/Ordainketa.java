package com.example.erronka1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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
        Kirola kirol_objetu = (Kirola) getIntent().getSerializableExtra("kirol_objetu");

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
                String prueba = "Eskaintzak";

                db.collection("kirolak").document(kirola).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Kirola kirol_obj_query = documentSnapshot.toObject(Kirola.class);
                        if (kirol_obj_query.getKirol_mota().equals(kirol_objetu.getKirol_mota())) {
                             if(kirol_obj_query.equals(kirol_objetu)) {
                                for (int j = 0; j < kirol_objetu.zelaiak.size(); j++) {
                                    if (kirol_objetu.zelaiak.get(j).zelai_izena.equals(zelaia)) {
                                        if(kirol_objetu.zelaiak.get(j).erreserbak==null){
                                            List<Erreserba> erreserbak = new ArrayList<Erreserba>();
                                            erreserbak.add(erreserba);
                                            kirol_objetu.zelaiak.get(j).setErreserbak(erreserbak);
                                        }else {
                                            kirol_objetu.zelaiak.get(j).erreserbak.add(erreserba);
                                        }
                                    }
                                }
                            }

                            db.collection("kirolak").document(kirola).set(kirol_objetu);

                            Intent intent = new Intent(Ordainketa.this, Hasiera.class);
                            startActivity(intent);
                        }

                    }

                });
            }

        });
    }
}