package com.ugb.controlesbasicos.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ugb.controlesbasicos.R;

public class Marcadores {
    GoogleMap nMap;
    Context context;

    public Marcadores(GoogleMap nMap, Context context) {
        this.nMap = nMap;
        this.context = context;
    }
    public void addMarkersDefault(){
        uno(-13.5206875,-88.3731835, "uno");
        dos(-13.5206875,-88.3731835, "punto1");
        tres(-13.5206875,-88.3731835, "punto9");
        cuatro(-13.5206875,-88.3731835, "punto8");
        cinco(-13.5206875,-88.3731835, "punto7");
        seis(-13.5206875,-88.3731835, "tebet");
    }

    public void dos(Double latitud, Double longitud, String titulo){

    }
    public void tres(Double latitud, Double longitud, String titulo){

    }
    public void cuatro(Double latitud, Double longitud, String titulo){

    }
    public void cinco(Double latitud, Double longitud, String titulo){

    }
    public void seis(Double latitud, Double longitud, String titulo){

    }
    public void uno(Double latitud, Double longitud, String titulo){
        LatLng punto=new LatLng(latitud, longitud);
        int height=140;
        int width=165;
        BitmapDrawable uno=(BitmapDrawable) context.getResources().getDrawable(R.drawable.img_17);
        Bitmap unos=uno.getBitmap();
        Bitmap uns=Bitmap.createScaledBitmap(unos,width,height,false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));

    }
}