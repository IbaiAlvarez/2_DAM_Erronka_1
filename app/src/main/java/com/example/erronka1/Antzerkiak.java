package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Antzerkiak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antzerkiak);

        Button btn_errserbaAt = (Button) findViewById(R.id.btn_ErreserbaAntzerki);
        Button btn_atzeraAt = (Button) findViewById(R.id.btn_AtzeraAntzerki);
        /*ArrayList<Antzerkiak> AntzerkiakList = new ArrayList<>();

        CollectionReference collectionRef = db.collection("antzerkiak");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {

                if (!querySnapshot.isEmpty()) {

                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        Antzerkiak antzerkiak = documentSnapshot.toObject(Antzerkiak.class);
                        antzerkiakList.add(antzerkiak);
                    }
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        });

        ArrayAdapter<Antzerkiak> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AntzerkiakList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerAntzerkiak = findViewById(R.id.spin_A1);
        spinnerAntzerkiak.setAdapter(adapter);*/

        btn_errserbaAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Antzerkiak.this, Ordainketa.class);
                startActivity(intent1);
            }
        });
        btn_atzeraAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Antzerkiak.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}