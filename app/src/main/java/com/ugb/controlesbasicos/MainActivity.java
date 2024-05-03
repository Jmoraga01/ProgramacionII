package com.ugb.controlesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraAguaActivity extends AppCompatActivity {

    private EditText metrosConsumidosEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_calculadora_agua);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        metrosConsumidosEditText = findViewById(R.id.editTextMetrosConsumidos);
        resultadoTextView = findViewById(R.id.textViewResultado);
        calcularButton = findViewById(R.id.buttonCalcular);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularValorPagar();
            }
        });
    }

    private void calcularValorPagar() {
        try {
            int metrosConsumidos = Integer.parseInt(metrosConsumidosEditText.getText().toString());

            double valorPagar;

            if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
                valorPagar = 6.0;  // Cuota fija
            } else if (metrosConsumidos >= 19 && metrosConsumidos <= 28) {
                double exceso = metrosConsumidos - 18;
                valorPagar = 6.0 + (exceso * 0.45);
            } else {
                double exceso = metrosConsumidos - 28;
                valorPagar = 6.0 + (10 * 0.45) + (exceso * 0.65);
            }

            resultadoTextView.setText(getString(R.string.resultado, String.format("$%.2f", valorPagar)));

        } catch (NumberFormatException e) {
            resultadoTextView.setText(R.string.mensaje_error);
        }
    }
}
