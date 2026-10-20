package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

public class Auxiliar {

    // Lee un CSV y devuelve una lista de Persona
    public static List<Persona> leerCSV(String fichero) {
        String ruta = "Ejercicio1.8\\src\\main\\resources\\csv\\" + fichero;
        List<Persona> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            br.readLine(); // cabecera, se ignora
            String linea;
            while ((linea = br.readLine()) != null) {//Lee la siguiente línea y la guarda en linea. Repite mientras no sea null
                // El 5 hace que el ultimo campo (country) se quede entero,
                // aunque lleve una coma dentro
                String[] campos = linea.split(",", 5);//Parte la línea en un array separando por comas.
                //El 5 es el máximo de trozos. Así, aunque el país lleve una coma dentro, el último trozo se queda entero y no se parte en dos.
                String pais = campos[4].replace("\"", "");//Quita las comillas del país si las tiene.
                // "\"" es el carácter comilla dentro de un String.
                lista.add(new Persona(campos[0], campos[1], campos[2], campos[3], pais));//Crea una persona y la añade a la lista.
            }
        } catch (Exception e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }

    // Escribe la lista en personal_fecha_hora.csv
    public static void escribirCSV(List<Persona> lista) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        //LocalDateTime.now() da la fecha y hora actuales.
        //DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss") las convierte a texto: año, mes, día, guion bajo, hora, minutos y segundos
        //Evitamos usar asi : y / porque no valen en nombres de fichero.
        String nombre = "personal_" + fecha + ".csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombre))) {
            pw.println("firstName,lastName,email,gender,country");//Escribimos la cabecera.
            for (Persona p : lista) {
                pw.println(p);//Recorre la lista y escribe cada persona en una línea. println(p) llama solo al toString() de Persona,
                            // que ya devuelve los datos separados por comas.
            }
            System.out.println("Datos guardados en " + nombre);
        } catch (Exception e) {
            System.out.println("Error al escribir el CSV: " + e.getMessage());
        }
    }


    public static List<Persona> leerXML(String fichero) {//Lee un XML (DOM) y devuelve una lista de Persona
        String ruta = "Ejercicio1.8\\src\\main\\resources\\xml\\" + fichero;
        List<Persona> lista = new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Crea el analizador dom. Es siempre la misma línea para empezar a leer un XML.
            Document doc = db.parse(new File(ruta));//Lee el fichero y construye el árbol completo en memoria. doc representa todo el documento.

            NodeList nodos = doc.getElementsByTagName("persona");//Busca todas las etiquetas <persona> del documento y las devuelve en una lista de nodos
            for (int i = 0; i < nodos.getLength(); i++) {//getLength() dice cuántos nodos hay
                Element e = (Element) nodos.item(i);//item(i) devuelve el nodo número i. Se convierte a Element (cast) para poder buscar dentro de esa <persona> sus etiquetas hijas.
                String firstName = e.getElementsByTagName("firstName").item(0).getTextContent();
                //Con ByTagName buscamos la etiqueta <firstName> dentro de esta persona, con .item(0) coge la primera (y única)
                // que encuentra. y el .getTextContent() devuelve el texto de dentro
                String lastName = e.getElementsByTagName("lastName").item(0).getTextContent();
                String email = e.getElementsByTagName("email").item(0).getTextContent();
                String gender = e.getElementsByTagName("gender").item(0).getTextContent();
                String country = e.getElementsByTagName("country").item(0).getTextContent();
                lista.add(new Persona(firstName, lastName, email, gender, country));//Creamos a la persona y la añadimos a la lista.
            }
        } catch (Exception e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
        return lista;
    }

    // Lee un JSON (Gson) y devuelve una lista de Persona
    public static List<Persona> leerJSON(String fichero) {
        String ruta = "Ejercicio1.8\\src\\main\\resources\\json\\" + fichero;        List<Persona> lista = new ArrayList<>();
        try (FileReader fr = new FileReader(ruta)) {
            Gson gson = new Gson();//Crea el objeto Gson, que convierte entre JSON y objetos Java.
            Persona[] personas = gson.fromJson(fr, Persona[].class);
            //Lee el fichero y lo convierte en un array de Persona. Persona[].class le dice a Gson “el JSON es un array de personas”.
            // Gson crea un objeto por cada {...} y rellena los atributos cuyo nombre coincide con la clave del JSON.
            // Por eso los atributos de Persona se llaman igual que las claves, y por eso hace falta el constructor vacío.
            for (Persona p : personas) {
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el JSON: " + e.getMessage());
        }
        return lista;
    }
}