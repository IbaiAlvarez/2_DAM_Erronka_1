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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class Kirolak<ApiFuture> extends AppCompatActivity implements OnItemSelectedListener  {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

        Button btn_errserbaK = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);
        Spinner Kirolak_Kirola=findViewById(R.id.spin_K1);
        Kirolak_Kirola.setOnItemSelectedListener(this);

        List<String> kirolakMotak = new ArrayList<>();
        List<String> kirolakZelaiak = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kirolakMotak);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_Kirola.setAdapter(adapter);

        //Añade primer elemento para aparecer al inicio
        kirolakMotak.add("Selecciona Deporte");
        adapter.notifyDataSetChanged();

        db.collection("kirolak")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                kirolakMotak.add(document.getId());
                                adapter.notifyDataSetChanged();
                            }
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
    /*
        if(k1){
            //vaciar spinner 2
           //carga spinner 2
        }else if(k2){
            //vaciar spinner 3
            //carga spinner 3
        }else if(k3){
            //vaciar spinner 4
            //carga spinner 4
        }
    */
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}