package com.example.erronka1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Zinema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zinema);

        Button btn_errserbaZ = (Button) findViewById(R.id.btn_ErreserbaZ);
        Button btn_atzeraZ = (Button) findViewById(R.id.btn_AtzeraZ);
        /*ArrayList<Zinema> ZinemakList = new ArrayList<>();

        CollectionReference collectionRef = db.collection("zinemak");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {

                if (!querySnapshot.isEmpty()) {

                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        Zinemak zinemal = documentSnapshot.toObject(Zinemak.class);
                        ZinemakList.add(kirolak);
                    }
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        });

        ArrayAdapter<Zinema> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ZinemakList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerZinemak = findViewById(R.id.spin_Z1);
        spinnerZinemak.setAdapter(adapter);*/

         btn_errserbaZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Zinema.this, Ordainketa.class);
                startActivity(intent1);
            }
        });
        btn_atzeraZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Zinema.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}