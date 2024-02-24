package com.example.aguacoversor;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class cuotaAgua extends AppCompatActivity {

    TextView total;

    EditText consumo;

    Button calcular, regresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuota_agua);

        try {

        }catch (Exception e){
            ;
        }

        calcular = findViewById(R.id.btnCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumo = findViewById(R.id.etConsumo);
                double consu = Double.parseDouble(consumo.getText().toString());
                if (consu<=18){
                    total = findViewById(R.id.lbTotal);
                    total.setText("Su cuota mensual es de $6");
                } else if (consu>18&&consu<=28) {
                    total = findViewById(R.id.lbTotal);
                    double Total;
                    Total = (consu - 18) * 0.45 + 6;
                    total.setText("Su cuota mensual es de $" + Total);
                    
                } else if (consu>=29) {
                    total = findViewById(R.id.lbTotal);
                    double Total;
                    Total = (((consu - 28) * 0.65)+(28 - 18) * 0.45) + 6 ;
                    total.setText("Su cuota mensual es de $" + Total);

                }
            }
        });

        regresa = findViewById(R.id.btnRegresa);
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}