package Practica1_2;
import java.util.AbstractMap;
import java.util.LinkedHashSet;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        Lectura lf = new Lectura();
        Escritura es = new Escritura();

        Biblioteca juvenil = new Biblioteca();
        Biblioteca central = new Biblioteca();

        try {
            LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos = lf.leerCampos("campos.dat");
            int tamañoRegistro = lf.calcularTamaño(lf.leerCampos("campos.dat"));
            Biblioteca biblioteca = lf.leerRegistros("datos-bib.dat", lf.leerCampos("campos.dat"), tamañoRegistro);
            es.separarCatalogo(biblioteca, juvenil, central);
            System.out.println(juvenil.getLibros().size());
            System.out.println(central.getLibros().size());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer los campos: " + e.getMessage());
        }
    }
}
