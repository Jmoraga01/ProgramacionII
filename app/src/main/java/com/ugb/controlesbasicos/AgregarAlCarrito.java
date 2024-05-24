package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileOutputStream;
import com.itextpdf.layout.Document;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;

public class AgregarAlCarrito extends AppCompatActivity {

    private String nombreProducto;
    private double precioProducto;
    private int cantidad = 1; // Cantidad inicial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_al_carrito);

        Intent intent = getIntent();
        nombreProducto = intent.getStringExtra("name");
        String precioProductoStr = intent.getStringExtra("precio");

        // Verificamos si el precio contiene el símbolo "$"
        if (precioProductoStr != null && precioProductoStr.startsWith("$")) {
            // Eliminamos el símbolo "$" antes de convertir a double
            precioProductoStr = precioProductoStr.substring(1);
        }

        precioProducto = Double.parseDouble(precioProductoStr);

        TextView nombreProductoTextView = findViewById(R.id.nombreProductoTextView);
        TextView precioProductoTextView = findViewById(R.id.precioProductoTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        TextView totalTextView = findViewById(R.id.totalTextView);
        Button generarPDFButton = findViewById(R.id.generarPDFButton);
        ImageButton masButton = findViewById(R.id.masButton);
        ImageButton menosButton = findViewById(R.id.menosButton);

        nombreProductoTextView.setText(nombreProducto);
        precioProductoTextView.setText("$" + precioProductoStr);
        updateTotal(totalTextView);

        masButton.setOnClickListener(v -> {
            cantidad++;
            quantityTextView.setText(String.valueOf(cantidad));
            updateTotal(totalTextView);
        });

        menosButton.setOnClickListener(v -> {
            if (cantidad > 1) {
                cantidad--;
                quantityTextView.setText(String.valueOf(cantidad));
                updateTotal(totalTextView);
            }
        });

        generarPDFButton.setOnClickListener(v -> {
            String nombreCliente = ((EditText) findViewById(R.id.nombreClienteEditText)).getText().toString();
            String correoCliente = ((EditText) findViewById(R.id.correoClienteEditText)).getText().toString();
            String telefonoCliente = ((EditText) findViewById(R.id.telefonoClienteEditText)).getText().toString();
            double total = cantidad * precioProducto;

            generarPDF(nombreCliente, correoCliente, telefonoCliente, nombreProducto, precioProducto, cantidad, total);
        });
    }

    private void updateTotal(TextView totalTextView) {
        double total = cantidad * precioProducto;
        totalTextView.setText("Total: $" + String.format("%.2f", total));
    }

    private void generarPDF(String nombreCliente, String correoCliente, String telefonoCliente, String nombreProducto, double precioProducto, int cantidad, double total) {
        try {
            String filePath = getExternalFilesDir(null) + "/Factura.pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Información del Cliente:"));
            document.add(new Paragraph("Nombre: " + nombreCliente));
            document.add(new Paragraph("Correo Electrónico: " + correoCliente));
            document.add(new Paragraph("Teléfono: " + telefonoCliente));
            document.add(new Paragraph("\nInformación del Producto:"));
            document.add(new Paragraph("Nombre del Producto: " + nombreProducto));
            document.add(new Paragraph("Precio del Producto: $" + precioProducto));
            document.add(new Paragraph("Cantidad: " + cantidad));
            document.add(new Paragraph("Total: $" + String.format("%.2f", total)));

            document.close();

            enviarPDFPorCorreo(correoCliente, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarPDFPorCorreo(String correo, String filePath) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Factura de Compra");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Adjunto encontrarás la factura de tu compra.");

        Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(filePath));
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(emailIntent, "Enviar Email"));
    }
}