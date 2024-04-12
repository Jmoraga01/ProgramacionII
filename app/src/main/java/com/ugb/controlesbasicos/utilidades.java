package com.ugb.controlesbasicos;

import java.util.Base64;

public class utilidades {
    static String url_consulta = "http://192.168.8.39:5984/productos/_design/productos/_view/productos";
    static String url_mto = "http://192.168.8.39:5984/productos";
    static String user="lo tuyo";
    static String passwd="lo tuyo";
    static String credencialesCodificadas = Base64.getEncoder().encodeToString((user+":"+passwd).getBytes());
    public String generarIdUnico(){
        return java.util.UUID.randomUUID().toString();
    }
}