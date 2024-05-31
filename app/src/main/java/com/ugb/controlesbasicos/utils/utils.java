package com.ugb.controlesbasicos.utils;


import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import java.util.HashMap;
import java.util.List;

public class utils {
    public static Coordenadas coordenadas=new Coordenadas();
    public static void markersDefault(GoogleMap nMap, Context context){
        new Marcadores(nMap,context).addMarkersDefault();
    }
}