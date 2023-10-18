package com.example.erronka1;

import static com.example.erronka1.Login.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;


public class MenuAdmin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        mAuth = FirebaseAuth.getInstance();
        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        Button btn_AtzeraMenuAdm = findViewById(R.id.btn_AtzeraMenuAdm);

        btn_AtzeraMenuAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cierra sesion de FirebaseAuth
                mAuth.signOut();
                // calling method to edit values in shared prefs.
                SharedPreferences.Editor editor = sharedpreferences.edit();

                // below line will clear
                // the data in shared prefs.
                editor.clear();

                // below line will apply empty
                // data to shared prefs.
                editor.apply();

                Intent intent = new Intent(MenuAdmin.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}