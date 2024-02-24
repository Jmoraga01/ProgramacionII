package com.example.aguacoversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn, btnCuota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnIr);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent siga = new Intent(MainActivity.this, aguaConversor.class);
                startActivity(siga);

            }
        });

        btnCuota = findViewById(R.id.btnCuota);
        btnCuota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sigaCuota = new Intent(MainActivity.this, cuotaAgua.class);
                startActivity(sigaCuota);
            }
        });
    }
}