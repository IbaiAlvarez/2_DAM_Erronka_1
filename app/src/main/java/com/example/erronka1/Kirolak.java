package com.example.erronka1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Model.Erreserba;
import Model.Kirola;

public class Kirolak<ApiFuture> extends AppCompatActivity implements OnItemSelectedListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAuth mAuth;

    Erreserba erreserba = new Erreserba();
    //aldagai globalen definizioa
    Spinner Kirolak_KirolaMota;
    Spinner Kirolak_KirolaZelai;
    Spinner Kirolak_KirolaOrdua;

    Button btn_ErreserbaKirolak;
    ImageButton btn_erreserbaData;
    TextView lbl_erreserbaData;
    List<String> kirolakMotak = new ArrayList<>();
    List<String> kirolakZelaiak = new ArrayList<>();
    List<String> kirolakOrdua = new ArrayList<>();
    ArrayAdapter<String> KirolaMotakAdaptadorea;
    ArrayAdapter<String> KirolakZelaiakAdaptadorea;
    ArrayAdapter<String> kirolakOrduaAdaptadorea;
    List<Kirola> kirolak= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirolak);

            db.collection("kirolak").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Kirola kirola = document.toObject(Kirola.class);
                        kirolak.add(kirola);
                    }
                    //Añade primer elemento para aparecer al inicio
                    kirolakMotak.add("Selecciona Deporte");
                    for(int i=0;i<kirolak.size();i++){
                        kirolakMotak.add(kirolak.get(i).getKirol_mota());
                    }
                    KirolaMotakAdaptadorea.notifyDataSetChanged();
                }
            }
            });


        btn_ErreserbaKirolak = (Button) findViewById(R.id.btn_ErreserbaKirolak);
        btn_ErreserbaKirolak.setEnabled(false);
        Button btn_atzeraK = (Button) findViewById(R.id.btn_AtzeraKirolak);
        Kirolak_KirolaMota = findViewById(R.id.spin_K1);
        Kirolak_KirolaZelai = findViewById(R.id.spin_K2);
        Kirolak_KirolaOrdua = findViewById(R.id.spin_K4);
        btn_erreserbaData = findViewById(R.id.btn_erreserbaData);
        lbl_erreserbaData = findViewById(R.id.lbl_erreserbaData);

        Kirolak_KirolaMota.setOnItemSelectedListener(this);
        Kirolak_KirolaZelai.setOnItemSelectedListener(this);
        Kirolak_KirolaOrdua.setOnItemSelectedListener(this);

        //vincular adaptadores con spinner
        KirolaMotakAdaptadorea = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item, kirolakMotak);
        KirolakZelaiakAdaptadorea = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item, kirolakZelaiak);
        kirolakOrduaAdaptadorea = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item, kirolakOrdua);

        KirolaMotakAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaMota.setAdapter(KirolaMotakAdaptadorea);

        KirolakZelaiakAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaZelai.setAdapter(KirolakZelaiakAdaptadorea);

        kirolakOrduaAdaptadorea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kirolak_KirolaOrdua.setAdapter(kirolakOrduaAdaptadorea);


        btn_ErreserbaKirolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                // Check if user is signed in (non-null) and update UI accordingly.
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Erreserba erreserba = new Erreserba(lbl_erreserbaData.getText().toString(),Kirolak_KirolaOrdua.getSelectedItem().toString(),currentUser.getEmail().toString());
                Intent intent = new Intent(Kirolak.this, Ordainketa.class);
                intent.putExtra("erreserba",erreserba);
                intent.putExtra("kirola",Kirolak_KirolaMota.getSelectedItem().toString());
                int index = 0;
                for(int i=0;i<kirolak.size();i++){
                    if(kirolak.get(i).getKirol_mota().equals(Kirolak_KirolaMota.getSelectedItem().toString())){
                        index = i;
                    }
                }
                Kirola kirol_objetu = kirolak.get(index);
                intent.putExtra("kirol_objetu", kirol_objetu);

                String seleccion = Kirolak_KirolaZelai.getSelectedItem().toString();
                intent.putExtra("zelaia", Kirolak_KirolaZelai.getSelectedItem().toString());
                startActivity(intent);
            }
        });
        btn_atzeraK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kirolak.this, Eskaintzak.class);
                startActivity(intent);
            }
        });

        //Erreserba datak onClick Listener
        btn_erreserbaData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datakIreki();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
    {
        //((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        //((TextView)  Kirolak_KirolaMota.getChildAt(0)).setTextColor(Color.WHITE);
        //((TextView)  Kirolak_KirolaOrdua.getChildAt(0)).setTextColor(Color.WHITE);

        String hartutakoBalioa = parent.getItemAtPosition(pos).toString();
        if(parent.getId()==R.id.spin_K1){
            if(!Kirolak_KirolaMota.getSelectedItem().toString().equals("Selecciona Deporte")){
                //vaciar spinner
                kirolakZelaiak.clear();
                //Añade primer elemento para aparecer al inicio
                kirolakZelaiak.add("Selecciona Campo");
                //Carga los campos del deporte seleccionado
                for(int i=0;i<kirolak.size();i++){
                    if(kirolak.get(i).getKirol_mota().equals(Kirolak_KirolaMota.getSelectedItem().toString())){
                        for(int j = 0; j< kirolak.get(i).getZelaiak().size(); j++) {
                            kirolakZelaiak.add(kirolak.get(i).getZelaiak().get(j).getZelai_izena());
                        }
                    }
                }
            }else{
                //vaciar spinner
                kirolakZelaiak.clear();
                //Añade primer elemento para aparecer al inicio
                kirolakZelaiak.add("Selecciona Campo");
                kirolakOrdua.add("Selecciona Hora");
                lbl_erreserbaData.setText("Selecciona fecha");
                KirolakZelaiakAdaptadorea.notifyDataSetChanged();
                kirolakOrduaAdaptadorea.notifyDataSetChanged();
            }
            Kirolak_KirolaZelai.setSelection(0);
            lbl_erreserbaData.setText("Selecciona fecha");
            Kirolak_KirolaOrdua.setSelection(0);
            ((TextView)  Kirolak_KirolaMota.getChildAt(0)).setTextColor(Color.WHITE);
        }
        else if(parent.getId()==R.id.spin_K2){
            if(Kirolak_KirolaZelai.getSelectedItem().toString().equals("Selecciona Campo")){
                //vaciar spinner
                kirolakOrdua.clear();
                //Añade primer elemento para aparecer al inicio
                kirolakOrdua.add("Selecciona Hora");
                lbl_erreserbaData.setText("Selecciona fecha");
                kirolakOrduaAdaptadorea.notifyDataSetChanged();
            }
            ((TextView)  Kirolak_KirolaZelai.getChildAt(0)).setTextColor(Color.WHITE);
        }
        else if(parent.getId()==R.id.spin_K4){
            erreserba.setOrdua(hartutakoBalioa);
            if(!Kirolak_KirolaOrdua.getSelectedItem().toString().equals("Selecciona Hora")){
                btn_ErreserbaKirolak.setEnabled(true);
            }else{
                btn_ErreserbaKirolak.setEnabled(false);
            }
            ((TextView)  Kirolak_KirolaOrdua.getChildAt(0)).setTextColor(Color.WHITE);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    //Date picker irekitzeko metodoa
    private void datakIreki(){
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

           //Data bat aukeratzerakoan orduak filtratzen ditu.
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                lbl_erreserbaData.setText(String.valueOf(day)+"-"+String.valueOf((month)+1)+"-"+String.valueOf(year));
                erreserba.setData(lbl_erreserbaData.getText().toString());

                //Orduak lortzeko query
                String hautatutakokirola = Kirolak_KirolaMota.getSelectedItem().toString();
                String hartutakoZelaia = Kirolak_KirolaZelai.getSelectedItem().toString();

                kirolakOrdua.clear();
                kirolakOrdua.add("Selecciona Hora");
                //Orduak kargatzen ditu
                for(int i=0;i<kirolak.size();i++){
                    if(kirolak.get(i).getKirol_mota().equals(Kirolak_KirolaMota.getSelectedItem().toString())){
                        for(int j = 0; j< kirolak.get(i).getZelaiak().size(); j++) {
                            if(kirolak.get(i).getZelaiak().get(j).getZelai_izena().equals(hartutakoZelaia)) {
                                for(int k = 0; k< kirolak.get(i).getZelaiak().get(j).getOrduak().size(); k++) {
                                    kirolakOrdua.add(kirolak.get(i).getZelaiak().get(j).getOrduak().get(k));
                                }
                            }
                        }
                    }
                }
                //Erreserba bat badago egun horretan ordua kentzen du
                for(int i=0;i<kirolak.size();i++){
                    if(kirolak.get(i).getKirol_mota().equals(Kirolak_KirolaMota.getSelectedItem().toString())){
                        for(int j = 0; j< kirolak.get(i).getZelaiak().size(); j++) {
                            if(kirolak.get(i).getZelaiak().get(j).getZelai_izena().equals(hartutakoZelaia)) {
                                if(kirolak.get(i).getZelaiak().get(j).getErreserbak() != null) {
                                    for (int k = 0; k < kirolak.get(i).getZelaiak().get(j).getErreserbak().size(); k++) {
                                        if (kirolak.get(i).getZelaiak().get(j).getErreserbak().get(k).getData().equals(lbl_erreserbaData.getText().toString())) {
                                            kirolakOrdua.remove(kirolak.get(i).getZelaiak().get(j).getErreserbak().get(k).getOrdua());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //kirolakOrduaAdaptadorea.notifyDataSetChanged();
                Kirolak_KirolaOrdua.setSelection(0);
            }
        }, 2023, 0, 0);
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis()+86400000);
        datePicker.show();
    }
}