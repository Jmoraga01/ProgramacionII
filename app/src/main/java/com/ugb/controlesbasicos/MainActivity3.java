package com.ugb.controlesbasicos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personList = new ArrayList<>();
        personList.add(new Person("Maurico Alfredo Carranza", "Experto en la programacion, y el ambito tecnologico.", R.drawable.img_3));
        personList.add(new Person("Wesly Ariel Umanzor", "Experto en la programacion, y el ambito tecnologico.", R.drawable.img_8));
        personList.add(new Person("Keyla Canales", "Experta en la programacion, y el ambito tecnologico.", R.drawable.img_5));
        personList.add(new Person("Erick Josue Chavez", "Experto en la programacion, y el ambito tecnologico.", R.drawable.img_6));
        personList.add(new Person("Daniel Oswaldo Ramirez", "Experto en la programacion, y el ambito tecnologico.", R.drawable.img_7));

        personAdapter = new PersonAdapter(this, personList);
        recyclerView.setAdapter(personAdapter);
    }
}
