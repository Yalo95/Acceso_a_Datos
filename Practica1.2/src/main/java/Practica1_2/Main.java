package Practica1_2;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.util.AbstractMap;
import java.util.LinkedHashSet;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Lectura lf = new Lectura();
        Escritura es = new Escritura();
        Modificacion modificacion = new Modificacion();

        Biblioteca juvenil = new Biblioteca();
        Biblioteca central = new Biblioteca();

        try {
            //LECTURA DE CAMPOS Y CÁLCULO DE TAMAÑO
            LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos = lf.leerCampos("campos.dat");
            int tamañoRegistro = lf.calcularTamaño(campos);

            //LECTURA DEL FICHERO ALEATORIO
            Biblioteca biblioteca = lf.leerRegistros("datos-bib.dat", campos, tamañoRegistro);

            //SEPARACION POR GÉNERO Y ESCRITURA DE FICHERO XML Y JSON
            es.separarCatalogo(biblioteca, juvenil, central);
            es.escribirXML(juvenil, "biblioteca.xsd", "biblioteca-juvenil.xml");
            System.out.println("XML validado y generado con éxito");
            es.escribirJson(central,"biblioteca.json");
            System.out.println("JSON generado con éxito");

            //MODIFICACION DEL FICHERO .DAT
            modificacion.actualizarSede("datos-bib.dat",campos,juvenil,tamañoRegistro);
            System.out.println("Sede actualizada con éxito");
            Biblioteca bibliotecaActualizada = lf.leerRegistros("datos-bib.dat", campos, tamañoRegistro);
            for(Libro libro: bibliotecaActualizada.getLibros()){
                System.out.println("ISBN: " + libro.getIsbn() + " || " + "SEDE: " + libro.getSede());
            }

        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Error al leer o escribir los ficheros: " + e.getMessage());
        } catch (JAXBException e) {
            System.err.println("Error al generar o validar el archivo XML: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error al cargar el archivo XSD: " + e.getMessage());
        }
    }
}
