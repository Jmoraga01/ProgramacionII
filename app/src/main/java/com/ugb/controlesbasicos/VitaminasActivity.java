package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class VitaminasActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<ListData> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitaminas);

        listView = findViewById(R.id.listview);

        int[] imageList = {R.drawable.v1, R.drawable.v2, R.drawable.v3, R.drawable.v4, R.drawable.v5, R.drawable.v6, R.drawable.v7, R.drawable.v8};
        int[] ingredientList = {R.string.g1, R.string.g2, R.string.g3, R.string.g4, R.string.g5, R.string.g6, R.string.g7, R.string.g8};
        int[] descList = {R.string.v1Desc, R.string.v2Desc, R.string.v3Desc, R.string.v4Desc, R.string.v5Desc, R.string.v6Desc, R.string.v7Desc, R.string.v8Desc};
        String[] nameList = {"ACULAR LS", "AFLREX", "AGGLAD", "Alin Oftalmico", "Brizolam", "Ciprowell", "CLORA MICOL" , "3-A Ofteno" };
        String[] precioList = {"$ 12", "$ 16", "$ 10.50", "$ 12", "$ 18", "$ 20", "$ 15" , "$ 15"};
        for (int i = 0; i < imageList.length; i++) {
            ListData listData = new ListData(nameList[i], precioList[i], ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(VitaminasActivity.this, dataArrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(VitaminasActivity.this, DetailedActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("precio", precioList[i]);
                intent.putExtra("ingredients", getString(ingredientList[i]));
                intent.putExtra("desc", getString(descList[i]));
                intent.putExtra("image", imageList[i]);
                startActivity(intent);
            }
        });

        ImageButton regresarButton = findViewById(R.id.regresar);
        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity();
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(VitaminasActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}