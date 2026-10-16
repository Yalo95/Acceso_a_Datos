package org.example;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "individuo")
@XmlAccessorType(value = XmlAccessType.FIELD)

public class Persona {

    @XmlAttribute
    private String dni;
    private String nombre;
    private int edad;

    @XmlElementWrapper(name = )
    @XmlElement
    private List<String> titulos;


    Persona (){}

    public Persona(String dni, String nombre, int edad) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
