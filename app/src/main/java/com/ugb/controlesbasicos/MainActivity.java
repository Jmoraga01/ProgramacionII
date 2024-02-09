package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    TextView tempval;
    Spinner spn;
    Button btnLongitud;
    Button btnVolumen;
    Button btnMasa;
    Button btnAlmacenamiento;
    Button btnTransmision;
    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversor);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("LON").setContent(R.id.tabLongitud).setIndicator("LONGITUD", null));
        tbh.addTab(tbh.newTabSpec("VOL").setContent(R.id.tabVolumen).setIndicator("VOLUMEN", null));
        tbh.addTab(tbh.newTabSpec("MAS").setContent(R.id.tabMasa).setIndicator("MASA", null));
        tbh.addTab(tbh.newTabSpec("ALM").setContent(R.id.tabAlmacenamiento).setIndicator("ALMACENAMIENTO", null));
        tbh.addTab(tbh.newTabSpec("TRS").setContent(R.id.tabTransmision).setIndicator("TRANSMISION", null));

        // Botón para conversión de longitud
        btnLongitud = findViewById(R.id.btnConvertirLongitud);
        btnLongitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica de conversión de longitud
                spn = findViewById(R.id.spnDElongitud);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAlongitud);
                int a = spn.getSelectedItemPosition();

                tempval = findViewById(R.id.txtCantidadLongitud);
                double cantidad = Double.parseDouble(tempval.getText().toString());
                double resp = miObj.convertir(0, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta:" + resp, Toast.LENGTH_LONG).show();
            }
        });

        btnVolumen = findViewById(R.id.btnConvertirVolumen);
        btnVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spn = findViewById(R.id.spnDEVolumen);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAVolumen);
                int a = spn.getSelectedItemPosition();

                tempval = findViewById(R.id.txtCantidadDeVolumen);
                double cantidad = Double.parseDouble(tempval.getText().toString());
                double resp = miObj.convertir(1, de, a, cantidad); // Se pasa 1 como opción para la conversión de volumen
                Toast.makeText(getApplicationContext(), "Respuesta:" + resp, Toast.LENGTH_LONG).show();
            }
        });
        btnMasa = findViewById(R.id.btnConvertirMasa);
        btnMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spn = findViewById(R.id.spnDEMasa);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAMasa);
                int a = spn.getSelectedItemPosition();

                tempval = findViewById(R.id.txtCantidadDeMasa);
                double cantidad = Double.parseDouble(tempval.getText().toString());
                double resp = miObj.convertir(2, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta:" + resp, Toast.LENGTH_LONG).show();
            }
        });
        btnAlmacenamiento = findViewById(R.id.btnConvertirAlmacenamiento);
        btnAlmacenamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spn = findViewById(R.id.spnDEAlmacenamiento);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAAlmacenamiento);
                int a = spn.getSelectedItemPosition();

                tempval = findViewById(R.id.txtCantidadDeAlmacenamiento);
                double cantidad = Double.parseDouble(tempval.getText().toString());
                double resp = miObj.convertir(3, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta:" + resp, Toast.LENGTH_LONG).show();
            }
        });
        btnTransmision = findViewById(R.id.btnConvertirTransmision);
        btnTransmision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spn = findViewById(R.id.spnDETransmision);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnATransmision);
                int a = spn.getSelectedItemPosition();

                tempval = findViewById(R.id.txtCantidadDeTransmision);
                double cantidad = Double.parseDouble(tempval.getText().toString());
                double resp = miObj.convertir(4, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta:" + resp, Toast.LENGTH_LONG).show();
            }
        });
    }
}

class conversores {
    double[][] valores = {
            // Longitud
            {1, 100, 39.3701, 3.28084, 1.193, 1.09361, 0.001, 0.000621371},
            // Volumen
            {1, 1000, 0.001, 0},
            // Masa
            {1, 1000, 0.00220462, 0.035274, 0.001, 1e+6, 9.8421e-7, 1.1023e-6},
            // Almacenamiento
            {1, 1e-6, 1e-9, 0.125, 0.000125, 1.25e-7, 1.25e-10, 1.25e-13},
            //transmision
            {1, 0.001, 1e-6, 0.000125, 0.000976563, 1.000000512e-6, 1.25e-7, 9.5367e-7, 1e-9, 1.25e-10, 9.3132e-10, 1e-12, 1.25e-13, 9.0949e-13 }
    };

    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}