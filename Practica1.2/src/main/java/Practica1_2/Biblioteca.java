package Practica1_2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "biblioteca")
@XmlAccessorType(XmlAccessType.FIELD)
public class Biblioteca {

    @XmlElement(name = "libro")
    private List<Libro> libros = new ArrayList<>();

    public Biblioteca() {
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    public void addLibro(Libro libro) {
        this.libros.add(libro);
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "libros=" + libros +
                '}';
    }
}
