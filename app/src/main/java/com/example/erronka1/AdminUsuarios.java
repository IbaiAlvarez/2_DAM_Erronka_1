package com.example.erronka1;

import static android.content.ContentValues.TAG;

import static com.example.erronka1.Login.EMAIL_KEY;
import static com.example.erronka1.Login.PASSWORD_KEY;
import static com.example.erronka1.Login.SHARED_PREFS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminUsuarios extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios);
        Button btn_atzeraAdminU = (Button) findViewById(R.id.btn_AtzeraAdminU);
        TableLayout adminTaula = (TableLayout) findViewById(R.id.tbl_AdminU);


        db.collection("erabiltzaileak")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String email = document.get("email").toString();
                                String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                String nick = document.get("erabiltzaile_nick").toString();
                                Log.d(TAG, document.getData().toString());

                                TableRow fila_Erabiltzaileak = new TableRow(getApplicationContext());//Instanciamos
                                //Declaramos atributos de la fila:
                                //Parametros del layout para el txt
                                TableLayout.LayoutParams lpErabiltzaileak = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
                                //Los añadimos a la fila
                                fila_Erabiltzaileak.setLayoutParams(lpErabiltzaileak);
                                //Instanciamos
                                TextView txt_emaila = new TextView(getApplicationContext());
                                txt_emaila.setId(View.generateViewId());
                                //Instanciamos
                                TextView txt_erabiltzailea = new TextView(getApplicationContext());
                                txt_erabiltzailea.setId(View.generateViewId());
                                //Instanciamos
                                TextView txt_nick = new TextView(getApplicationContext());
                                txt_nick.setId(View.generateViewId());
                                //Parametros del layout para el txt
                                TableRow.LayoutParams lpTxt_erabiltzailea = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
                                //Añadimos los parametros al txt
                                txt_emaila.setLayoutParams(lpTxt_erabiltzailea);
                                txt_erabiltzailea.setLayoutParams(lpTxt_erabiltzailea);
                                txt_erabiltzailea.setLayoutParams(lpTxt_erabiltzailea);
                                //Añadimos los txt a la fila
                                fila_Erabiltzaileak.addView(txt_emaila);
                                fila_Erabiltzaileak.addView(txt_erabiltzailea);
                                fila_Erabiltzaileak.addView(txt_nick);
                                txt_emaila.setText(email);
                                txt_erabiltzailea.setText(erabiltzaile_mota);
                                txt_nick.setText(nick);
                                txt_emaila.setTextColor(Color.WHITE);
                                txt_nick.setTextColor(Color.WHITE);
                                txt_erabiltzailea.setTextColor(Color.WHITE);


                                adminTaula.addView(fila_Erabiltzaileak);
                                txt_emaila.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        DocumentReference docRef = db.collection("erabiltzaileak").document(txt_emaila.getText().toString());
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, document.getData().toString());
                                                    sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                                    DocumentSnapshot document = task.getResult();
                                                    String email = document.get("email").toString();
                                                    String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                                    String erabiltzaile_nick = document.get("erabiltzaile_nick").toString();
                                                    String izena = document.get("izena").toString();
                                                    String abizena = document.get("abizena").toString();

                                                    Pertsona erabiltzailea = new Pertsona(izena, abizena, email, erabiltzaile_nick, erabiltzaile_mota);

                                                    Intent intent = new Intent(AdminUsuarios.this, InfoErabiltzailea.class);
                                                    intent.putExtra("erabiltzailea",erabiltzailea);
                                                    startActivity(intent);

                                                } else {
                                                    Log.d(TAG, document.getData().toString());
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });

                                    }
                                });

                                txt_erabiltzailea.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        DocumentReference docRef = db.collection("erabiltzaileak").document(txt_emaila.getText().toString());
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, document.getData().toString());
                                                    sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                                    DocumentSnapshot document = task.getResult();
                                                    String email = document.get("email").toString();
                                                    String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                                    String erabiltzaile_nick = document.get("erabiltzaile_nick").toString();
                                                    String izena = document.get("izena").toString();
                                                    String abizena = document.get("abizena").toString();

                                                    Pertsona erabiltzailea = new Pertsona(izena, abizena, email, erabiltzaile_nick, erabiltzaile_mota);

                                                    Intent intent = new Intent(AdminUsuarios.this, InfoErabiltzailea.class);
                                                    intent.putExtra("erabiltzailea",erabiltzailea);
                                                    startActivity(intent);

                                                } else {
                                                    Log.d(TAG, document.getData().toString());
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                    }
                                });
                                txt_nick.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        DocumentReference docRef = db.collection("erabiltzaileak").document(txt_emaila.getText().toString());
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, document.getData().toString());
                                                    sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                                    DocumentSnapshot document = task.getResult();
                                                    String email = document.get("email").toString();
                                                    String erabiltzaile_mota = document.get("erabiltzaile_mota").toString();
                                                    String erabiltzaile_nick = document.get("erabiltzaile_nick").toString();
                                                    String izena = document.get("izena").toString();
                                                    String abizena = document.get("abizena").toString();

                                                    Pertsona erabiltzailea = new Pertsona(izena, abizena, email, erabiltzaile_nick, erabiltzaile_mota);

                                                    Intent intent = new Intent(AdminUsuarios.this, InfoErabiltzailea.class);
                                                    intent.putExtra("erabiltzailea",erabiltzailea);
                                                    startActivity(intent);

                                                } else {
                                                    Log.d(TAG, document.getData().toString());
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        btn_atzeraAdminU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminUsuarios.this, MenuAdmin.class);
                startActivity(intent);
            }
        });

    }
}