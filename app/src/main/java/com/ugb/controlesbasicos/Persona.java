package com.ugb.controlesbasicos;

// Persona.java
public class Persona {
    private String nombre;
    private int edad;
    private String correo;
    private String profesion;
    private int foto;

    public Persona(String nombre, int edad, String correo, String profesion, int foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.profesion = profesion;
        this.foto = foto;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCorreo() { return correo; }
    public String getProfesion() { return profesion; }
    public int getFoto() { return foto; }
}
