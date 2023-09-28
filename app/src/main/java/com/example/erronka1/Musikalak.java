package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Musikalak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musikalak);

        Button btn_errserbaM = (Button) findViewById(R.id.btn_ErreserbaM);
        Button btn_atzeraM = (Button) findViewById(R.id.btn_AtzeraM);
        Spinner spinnerMusikalak = findViewById(R.id.spin_M1);
       /* ArrayList<Musikalak> MusikalakList = new ArrayList<>();

        CollectionReference collectionRef = db.collection("musikalak");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {

                if (!querySnapshot.isEmpty()) {

                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        Musikalak musikalak = documentSnapshot.toObject(Musikalak.class);
                        MusikalakList.add(musikalak);
                    }
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        });

        ArrayAdapter<Musikalak> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MusikalakList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerZinemak = findViewById(R.id.spin_M1);
        spinnerZinemak.setAdapter(adapter);*/

         btn_errserbaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Musikalak.this, Ordainketa.class);
                startActivity(intent1);
            }
        });
        btn_atzeraM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Musikalak.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}