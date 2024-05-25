package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


// MainActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPersona extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonaAdapter adapter;
    private List<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_persona);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de personas
        personas = new ArrayList<>();
        personas.add(new Persona("Mauricio Alfredo Carranza", 18  , "alfred@gmail.com", "Ingeniero", R.drawable.img_3));
        personas.add(new Persona("Wesly Ariel Umanzor", 20, "wesly@gmail.com", "Ingeniero", R.drawable.img_8));
        personas.add(new Persona("Keyla Canales", 20, "keyla@gmail.com", "Ingeniera", R.drawable.img_5));
        personas.add(new Persona("Erick Josue Chavez", 19, "erick@gmail.com", "Ingeniero", R.drawable.img_6));
        personas.add(new Persona("Daniel Oswaldo Ramirez", 19, "daniel@gmail.com", "Ingeniero", R.drawable.img_7));
        // Agregar más personas a la lista...

        adapter = new PersonaAdapter(this, personas);
        recyclerView.setAdapter(adapter);
    }
}
