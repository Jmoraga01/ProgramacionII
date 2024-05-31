package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ugb.controlesbasicos.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TrasarLinea extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap nMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        nMap.setMyLocationEnabled(true);
        nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Utils.markersDefault(nMap, getApplicationContext());

        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;


        for (int i = 0; i < Utils.route.size(); i++) {
            Log.d("aqui", String.valueOf(Utils.route.size()));
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();


            List<HashMap<String, String>> path = Utils.route.get(i);


            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
                lineOptions.addAll(points);

                lineOptions.width(9);

                lineOptions.color(Color.BLUE);
            }
            nMap.addPolyline(lineOptions);

            LatLng destino = new LatLng(Utils.coordenadas.getDestinoLatitud(), Utils.coordenadas.getDestinoLongitud());

            nMap.moveCamera((CameraUpdateFactory.newLatLngZoom(destino, 15)));


        }
    }
}