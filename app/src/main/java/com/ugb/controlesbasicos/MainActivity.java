package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;

    String id="", accion = "nuevo";

    Button btn;

    FloatingActionButton regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnGuardarAmigo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtnombre);
                String nombre = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtdireccion);
                String direccion = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtTelefono);
                String telefono = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtEmail);
                String email = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtDui);
                String dui = tempVal.getText().toString();

                String[] datos = new String[]{id,nombre,direccion,telefono,email,dui};
                DB db = new DB(getApplicationContext(), "", null, 1);
                String respuesta = db.administrar_amigos(accion, datos);
                if (respuesta.equals("ok")){
                    Toast.makeText(getApplicationContext(),"Amigo registrado cion exito", Toast.LENGTH_LONG);
                }else {
                    Toast.makeText(getApplicationContext(),"Error al intentar registrar amigo", Toast.LENGTH_LONG);
                }

            }
        });

        regresar = findViewById(R.id.fabReverse);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }º