package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class GripeTosActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<ListData> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gripe_tos);

        listView = findViewById(R.id.listview);

        int[] imageList = {R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4, R.drawable.g5, R.drawable.g6, R.drawable.g7, R.drawable.g8};
        int[] ingredientList = {R.string.g1, R.string.g2, R.string.g3, R.string.g4, R.string.g5, R.string.g6, R.string.g7, R.string.g8};
        int[] descList = {R.string.g1Desc, R.string.g2Desc, R.string.g3Desc, R.string.g4Desc, R.string.g5Desc, R.string.g6Desc, R.string.g7Desc, R.string.g8Desc};
        String[] nameList = {"Antigrip", "Anti.Grip dia", "ABRILLAR EA 575", "ACEITE GOMENOLADO", "alicol D", "Ambroxol MK", "AMBROXOL SUIZOS" , "ANTIFLU-DES" };
        String[] precioList = {"$ 12", "$ 16", "$ 10.50", "$ 12", "$ 18", "$ 20", "$ 15" , "$ 15"};
        for (int i = 0; i < imageList.length; i++) {
            ListData listData = new ListData(nameList[i], precioList[i], ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(GripeTosActivity.this, dataArrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GripeTosActivity.this, DetailedActivity.class);
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
        Intent intent = new Intent(GripeTosActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}