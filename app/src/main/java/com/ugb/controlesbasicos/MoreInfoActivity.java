package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// MoreInfoActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        // Obtener datos de la persona del intent
        Intent intent = getIntent();
        if (intent != null) {
            Person person = (Person) intent.getSerializableExtra("person");
            if (person != null) {
                // Mostrar los datos de la persona en la interfaz de usuario
                TextView textViewName = findViewById(R.id.text_view_name);
                textViewName.setText(person.getName());
                // Similarmente, puedes mostrar otros datos como edad, origen, país, profesión, etc.
            }
        }
    }
}

