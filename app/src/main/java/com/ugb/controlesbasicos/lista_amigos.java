package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


public class lista_amigos extends AppCompatActivity {
    Bundle parametros = new Bundle();
    FloatingActionButton btnAgregarProductos;
    ListView lts;
    Cursor cProductos;
    amigos misClientes;
    DB db;
    final ArrayList<amigos> alProductos = new ArrayList<amigos>();
    final ArrayList<amigos> alProductosCopy = new ArrayList<amigos>();

    JSONArray datosJSON;
    JSONObject jsonObject;
    obtenerDatosServidor datosServidor;
    detectarInternet di;
    int posicion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_amigos);

        db = new DB(lista_amigos.this, "", null, 1);
        btnAgregarProductos = findViewById(R.id.fabAgregarProductos);
        btnAgregarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parametros.putString("accion", "nuevo");
                abrirActividad(parametros);
            }
        });
        try {
            di = new detectarInternet(getApplicationContext());
            if (di.hayConexionInternet()) {
                obtenerDatosProductosServidor();
            } else {//offline
                obtenerDatosProducto();
            }
        }catch (Exception e){
            mostrarMsg("Error al cargar lista Producto: "+ e.getMessage());
        }
        buscarProductos();
    }

    private void obtenerDatosProductosServidor(){
        try {
            datosServidor = new obtenerDatosServidor();
            String data = datosServidor.execute().get();
            jsonObject = new JSONObject(data);
            datosJSON = jsonObject.getJSONArray("rows");
            mostrarDatosAmigos();
        }catch (Exception e){
            mostrarMsg("Error al obtener datos del server: "+e.getMessage());
        }
    }
    private void mostrarDatosAmigos() {
        try {
            if (datosJSON.length() > 0) {
                lts = findViewById(R.id.ltsProductos);
                alProductos.clear();
                alProductosCopy.clear();
                JSONObject misDatosJSONObject;
                for (int i = 0; i < datosJSON.length(); i++) {
                    misDatosJSONObject = datosJSON.getJSONObject(i).getJSONObject("value");
                    misClientes = new amigos(
                            misDatosJSONObject.getString("_id"),
                            misDatosJSONObject.getString("_rev"),
                            misDatosJSONObject.getString("idproducto"),
                            misDatosJSONObject.getString("codigo"),
                            misDatosJSONObject.getString("descripcion"),
                            misDatosJSONObject.getString("marca"),
                            misDatosJSONObject.getString("presentacion"),
                            misDatosJSONObject.getString("precio"),
                            misDatosJSONObject.getString("urlCompletaFoto")
                    );
                    alProductos.add(misClientes);
                }
                alProductosCopy.addAll(alProductos);
                adaptadorImagenes adImagenes = new adaptadorImagenes(lista_amigos.this, alProductos);
                lts.setAdapter(adImagenes);
                registerForContextMenu(lts);
            } else {
                mostrarMsg("No hay datos que mostrar.");
            }
        } catch (Exception e) {
            mostrarMsg("Error al mostrar los datos: " + e.getMessage());
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);

        try {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            posicion = info.position;
            menu.setHeaderTitle(datosJSON.getJSONObject(posicion).getJSONObject("value").getString("nombre"));
        }catch (Exception e){
            mostrarMsg("Error al mostrar el menu: "+ e.getMessage());
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try{
            switch (item.getItemId()){
                case R.id.mnxAgregar:
                    parametros.putString("accion","nuevo");
                    abrirActividad(parametros);
                    break;
                case R.id.mnxModificar:
                    parametros.putString("accion", "modificar");
                    parametros.putString("amigos", datosJSON.getJSONObject(posicion).toString());
                    abrirActividad(parametros);
                    break;
                case R.id.mnxEliminar:
                    eliminarProductos();
                    break;
            }
            return true;
        }catch (Exception e){
            mostrarMsg("Error al seleccionar una opcion del mennu: "+ e.getMessage());
            return super.onContextItemSelected(item);
        }
    }
    private void eliminarProductos(){
        try{
            AlertDialog.Builder confirmar = new AlertDialog.Builder(lista_amigos.this);
            confirmar.setTitle("Estas seguro de eliminar a: ");
            confirmar.setMessage(datosJSON.getJSONObject(posicion).getJSONObject("value").getString("nombre")); //1 es el nombre
            confirmar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        String respuesta = db.administrar_amigos("eliminar",
                                new String[]{"", "", datosJSON.getJSONObject(posicion).getJSONObject("value").getString("idAmigo")});
                        if (respuesta.equals("ok")) {
                            mostrarMsg("Producto eliminado con exito");
                            obtenerDatosProducto();
                        } else {
                            mostrarMsg("Error al eliminar el producto: " + respuesta);
                        }
                    }catch (Exception e){
                        mostrarMsg("Error al intentar elimianr: "+ e.getMessage());
                    }
                }
            });
            confirmar.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            confirmar.create().show();
        }catch (Exception e){
            mostrarMsg("Error al eliminar producto: "+ e.getMessage());
        }
    }
    private void abrirActividad(Bundle parametros){
        Intent abrirActividad = new Intent(getApplicationContext(), MainActivity.class);
        abrirActividad.putExtras(parametros);
        startActivity(abrirActividad);
    }
    private void obtenerDatosProducto(){//offline
        try {
            alProductos.clear();
            alProductosCopy.clear();

            cProductos = db.consultar_amigos();

            if( cProductos.moveToFirst() ){
                lts = findViewById(R.id.ltsProductos);//eliminar despues
                datosJSON = new JSONArray();
                do{
                    jsonObject =new JSONObject();
                    JSONObject jsonObjectValue = new JSONObject();
                    jsonObject.put("_id", cProductos.getString(0));
                    jsonObject.put("_rev", cProductos.getString(1));
                    jsonObject.put("idproducto", cProductos.getString(2));
                    jsonObject.put("codigo", cProductos.getString(3));
                    jsonObject.put("descripcion", cProductos.getString(4));
                    jsonObject.put("marca", cProductos.getString(5));
                    jsonObject.put("presentacion", cProductos.getString(6));
                    jsonObject.put("precio", cProductos.getString(7));
                    jsonObject.put("urlCompletaFoto", cProductos.getString(8));


                    jsonObjectValue.put("value", jsonObject);
                    datosJSON.put(jsonObjectValue);

                    alProductos.add(misClientes);//eliminar despues

                }while(cProductos.moveToNext());
                mostrarDatosAmigos();
                alProductosCopy.addAll(alProductos);//eliminar despues

                adaptadorImagenes adImagenes = new adaptadorImagenes(lista_amigos.this, alProductos);
                lts.setAdapter(adImagenes);//eliminar despues

                registerForContextMenu(lts);//eliminar despues
            }else{
                mostrarMsg("No hay Datos de productos que mostrar.");
            }
        }catch (Exception e){
            mostrarMsg("Error al mostrar datos: "+ e.getMessage());
        }
    }
    private void buscarProductos(){
        TextView tempVal;
        tempVal = findViewById(R.id.txtBuscarProductos);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    alProductos.clear();
                    String valor = tempVal.getText().toString().trim().toLowerCase();
                    if( valor.length()<=0 ){
                        alProductos.addAll(alProductosCopy);
                    }else{
                        for (amigos productos : alProductosCopy){
                            String codigo = productos.getCodigo();
                            String descripcion = productos.getDescripcion();
                            String marca = productos.getMarca();
                            String presentacion = productos.getPresentacion();
                            String precio = productos.getPrecio();
                            if(codigo.toLowerCase().trim().contains(valor) ||
                                    descripcion.toLowerCase().trim().contains(valor) ||
                                    marca.trim().contains(valor) ||
                                    presentacion.trim().toLowerCase().contains(valor) ||
                                    precio.trim().contains(valor)){
                                alProductos.add(productos);
                            }
                        }
                        adaptadorImagenes adImagenes = new adaptadorImagenes(getApplicationContext(), alProductos);
                        lts.setAdapter(adImagenes);
                    }
                }catch (Exception e){
                    mostrarMsg("Error al buscar: "+ e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }
    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}