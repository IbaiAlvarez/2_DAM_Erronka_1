package com.example.erronka1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Erregistroa extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erregistroa);

        EditText txt_izen_erregistro = findViewById(R.id.txt_izen_erregistro);
        EditText txt_abizen_erregistro = findViewById(R.id.txt_abizen_erregistro);
        EditText txt_email_erregistro = findViewById(R.id.txt_email_erregistro);
        EditText txt_erabiltzaile_erregistro = findViewById(R.id.txt_erabiltzaile_erregistro);
        EditText txt_pasahitza_erregistro = findViewById(R.id.txt_pasahitza_erregistro);
        Button btn_erregistratu_erregistroa = findViewById(R.id.btn_erregistratu_erregistroa);
        TextView lbl_logeatzea = findViewById(R.id.lbl_logeatzea);

        lbl_logeatzea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Erregistroa.this, Login.class);
                startActivity(intent);
            }
        });
    }
}