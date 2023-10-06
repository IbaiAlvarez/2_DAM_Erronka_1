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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class Kirolak<ApiFuture> extends AppCompatActivity implements OnItemSelectedListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //aldagai globalen definizioa
    Spinner Kirolak_KirolaMota;
    Spinner Kirolak_KirolaZelai;
    Spinner Kirolak_KirolaEguna;
    Spinner Kirolak_KirolaOrdua;
    List<String> kirolakMotak = new ArrayList<>();
    List<String> kirolakZelaiak = new ArrayList<>();
    List<String> kirolakEguna = new ArrayList<>();
    List<String> kirolakOrdua = new ArrayList<>();
    ArrayAdapter<String> KirolaMotakAdaptadorea;
    ArrayAdapter<String> KirolakZelaiakAdaptadorea;
    ArrayAdapter<String> kirolakEgunaAdaptadorea;
    ArrayAdapter<String> kirolakOrduaAdaptadorea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

        Button btn_errserbaK = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);
        Kirolak_KirolaMota = findViewById(R.id.spin_K1);
        Kirolak_KirolaZelai = findViewById(R.id.spin_K2);
        Kirolak_KirolaEguna = findViewById(R.id.spin_K3);
        Kirolak_KirolaOrdua = findViewById(R.id.spin_K4);

        Kirolak_KirolaMota.setOnItemSelectedListener(this);
        Kirolak_KirolaZelai.setOnItemSelectedListener(this);
        Kirolak_KirolaEguna.setOnItemSelectedListener(this);
        Kirolak_KirolaOrdua.setOnItemSelectedListener(this);

        //vincular adaptadores con spinner
        KirolaMotakAdaptadorea = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakMotak);
        KirolakZelaiakAdaptadorea = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakZelaiak);
        kirolakEgunaAdaptadorea = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakEguna);
        kirolakOrduaAdaptadorea = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakOrdua);

        KirolaMotakAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaMota.setAdapter(KirolaMotakAdaptadorea);

        KirolakZelaiakAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaZelai.setAdapter(KirolakZelaiakAdaptadorea);

        kirolakOrduaAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaOrdua.setAdapter(kirolakOrduaAdaptadorea);

        db.collection("kirolak")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Añade primer elemento para aparecer al inicio
                            kirolakMotak.add("Selecciona Deporte");
                            KirolaMotakAdaptadorea.notifyDataSetChanged();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                kirolakMotak.add(document.getId());
                            }
                            KirolaMotakAdaptadorea.notifyDataSetChanged();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
    {
        String hartutakoBalioa = parent.getItemAtPosition(pos).toString();
        if(parent.getId()==R.id.spin_K1){
            //cargar spinner
            db.collection("kirolak")
                    .document(hartutakoBalioa)
                    .collection("zelaiak")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //vaciar spinner
                            kirolakZelaiak.clear();
                            //Añade primer elemento para aparecer al inicio
                            kirolakZelaiak.add("Selecciona Campo");
                            KirolakZelaiakAdaptadorea.notifyDataSetChanged();
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    kirolakZelaiak.add(document.getId());
                                }
                                KirolakZelaiakAdaptadorea.notifyDataSetChanged();
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }else if(parent.getId()==R.id.spin_K2){
            String hautatutakokirola = Kirolak_KirolaMota.getSelectedItem().toString();
            DocumentReference docRef = db.collection("kirolak").document(hautatutakokirola).collection("zelaiak").document(hartutakoBalioa);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    kirolakOrdua.clear();
                    kirolakOrdua.add("Selecciona Hora");
                    kirolakOrduaAdaptadorea.notifyDataSetChanged();
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                           String horas[] = new String [10];
                            //kirolakOrdua.add(document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }else if(parent.getId()==R.id.spin_K3){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}