package com.example.aguacoversor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class aguaConversor extends AppCompatActivity {

    TextView metro, Pie, Vara, Yarda, Tarea, Manzana, Hectarea;

    Button convertir, regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua_conversor);

        convertir = findViewById(R.id.btnConvertir);
        convertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metro = findViewById(R.id.etAgua);
                double agua = Double.parseDouble(metro.getText().toString());

                double resultado = 0;
                resultado = agua * 10.76;

                Pie = findViewById(R.id.lbPie);
                Pie.setText(resultado + " Pies cuadrados");

                Vara = findViewById(R.id.lbVaraCuadrada);
                double result2 = 0;
                result2 = agua * 1.43;
                Vara.setText(result2 + " Varas cuadradas");

                Yarda = findViewById(R.id.lbYarda);
                double result3 = 0;
                result3 = agua * 1.19599;
                Yarda.setText(result3 + " Yardas cuadradas");

                Tarea = findViewById(R.id.lbTarea);
                double result4 = 0;
                result4 = agua * 0.0016;
                Tarea.setText(result4 + " Tareas");

                Manzana = findViewById(R.id.lbManzana);
                double result5 = 0;
                result5 = agua * 0.0001431;
                Manzana.setText(result5+ " Manzanas");

                Hectarea = findViewById(R.id.lbHectarea);
                double result6 = 0;
                result6 = agua * 0.0001;
                Hectarea.setText(result6 + " Hectareas");

            }
        });

        regresar = findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}