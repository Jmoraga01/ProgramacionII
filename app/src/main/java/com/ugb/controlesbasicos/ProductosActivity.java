package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ugb.controlesbasicos.databinding.ActivityMainBinding;
import com.ugb.controlesbasicos.ListAdapter;

import java.util.ArrayList;
public class ProductosActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<ListData> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listview);

        int[] imageList = {R.drawable.unol, R.drawable.dosl, R.drawable.tresl, R.drawable.cuatrol, R.drawable.cincol, R.drawable.seisl, R.drawable.sietel, R.drawable.ochol, R.drawable.nuevel, R.drawable.diesl, R.drawable.oncel, R.drawable.docel, R.drawable.trecel, R.drawable.catorcel, R.drawable.quincel};
        int[] ingredientList = {R.string.vitamia1, R.string.vitamina2, R.string.vitamina3, R.string.vitamina4, R.string.vitamina5, R.string.vitamina6, R.string.vitamina7, R.string.vitamina8, R.string.vitamina9, R.string.vitamina10, R.string.vitamina11, R.string.vitamina12, R.string.vitamina13, R.string.vitamina14, R.string.vitamina15};
        int[] descList = {R.string.vitamina1Desc, R.string.vitamina2Desc, R.string.vitamina3Desc, R.string.vitamina4Desc, R.string.vitamina5Desc, R.string.vitamina6Desc, R.string.vitamina7Desc, R.string.vitamina8Desc, R.string.vitamina9Desc, R.string.vitamina10Desc, R.string.vitamina11Desc, R.string.vitamina12Desc, R.string.vitamina13Desc, R.string.vitamina14Desc, R.string.vitamina15Desc};
        String[] nameList = {"ANTIDEX", "BROXOTAN ON", "VITASIL 25 000", "Vitasil", "Alprazolam", "AMOXICILINA L.S", "LORATADINA LS" , "NEUMOSIL" , "CLARITISIL" , "VICK JARABE" , "Antigrip" , "Babysil" , "DoloCrim" , "ACETOSIL" , "CLORFENIL"};
        String[] precioList = {"$ 12", "$ 16", "$ 10.50", "$ 12", "$ 18", "$ 20", "$ 15" , "$ 15" , "$ 15" , "$ 15" , "$ 15" , "$ 15" , "$ 15" , "$ 15" , "$ 15"};
        for (int i = 0; i < imageList.length; i++) {
            ListData listData = new ListData(nameList[i], precioList[i], ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(ProductosActivity.this, dataArrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProductosActivity.this, DetailedActivity.class);
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
        Intent intent = new Intent(ProductosActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}