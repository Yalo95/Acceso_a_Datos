package org.example;

import com.google.gson.annotations.SerializedName;

public class Persona {

    @SerializedName("Nombre Persona") //Sirve para cambiar el nombre de la variable en el JSON. Engloba todo lo que tiene debajo.
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
