package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Kirolak extends AppCompatActivity {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

        Button btn_errserbaK = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);

        Spinner spinnerKirolak_orduak = findViewById(R.id.spin_K2);
        Spinner spinnerKirolak_data = findViewById(R.id.spin_K3);
        Spinner spinnerKirolak_aretoak = findViewById(R.id.spin_K4);
        /*ArrayList<Kirolak> kirolakList = new ArrayList<>();

        CollectionReference collectionRef = db.collection("kirolak");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {

                if (!querySnapshot.isEmpty()) {

                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        Kirolak kirolak = documentSnapshot.toObject(Kirolak.class);
                        kirolakList.add(kirolak);
                    }
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        });

        ArrayAdapter<Kirolak> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerKirolak = findViewById(R.id.spin_K1);
        spinnerKirolak.setAdapter(adapter);*/


        btn_errserbaK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Kirolak.this, Ordainketa.class);
                startActivity(intent1);
            }
        });
        btn_atzeraK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kirolak.this, Eskaintzak.class);
                startActivity(intent);
            }
        });
    }
}