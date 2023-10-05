package com.example.erronka1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Kirolak<ApiFuture> extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

        Button btn_errserbaK = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);
        Spinner Kirolak_Kirola=findViewById(R.id.spin_K1);
        List<String> kirolakList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_Kirola.setAdapter(adapter);

        db.collection("kirolak")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                kirolakList.add(document.getId());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());

                        }
                    }
                });

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