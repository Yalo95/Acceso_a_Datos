package Practica1_2;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"autor", "titulo", "generos", "precio", "fechaPublicacion", "sede"})
//Asi nos aseguramos que el orden de los elementos en el XML sea el mismo que el del xsd, ya que si no lo hacemos asi,
// el orden de los elementos en el XML puede ser diferente al del xsd y eso puede dar problemas a la hora de validarlo.
@XmlAccessorType(XmlAccessType.FIELD)
public class Libro {

    @XmlAttribute(name = "isbn")
    private int isbn;
    @XmlElement(name = "autor")
    private String autor;
    @XmlElement(name = "titulo")
    private String titulo;
    @XmlElementWrapper(name = "clasificacion")
    @XmlElement(name = "genero")
    private List<String> generos = new ArrayList<>();
    @XmlElement(name = "precio")
    private double precio;
    @XmlElement(name = "fechaPublicacion")
    private int fechaPublicacion;
    @XmlElement(name = "sede")
    private String sede;

    public Libro() {
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
    public void añadirGenero (String genero) { //Para poder añadir ambos generos a la lista.
        generos.add(genero);
    }
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", generos=" + generos +
                ", precio=" + precio +
                ", fechaPublicacion=" + fechaPublicacion +
                ", sede='" + sede + '\'' +
                '}';
    }
}
