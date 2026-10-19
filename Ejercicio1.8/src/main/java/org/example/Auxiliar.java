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
    public static List<Persona> leerCSV(String path) {
        List<Persona> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // cabecera, se ignora
            String linea;
            while ((linea = br.readLine()) != null) {
                // El 5 hace que el ultimo campo (country) se quede entero,
                // aunque lleve una coma dentro
                String[] campos = linea.split(",", 5);
                String pais = campos[4].replace("\"", "");
                lista.add(new Persona(campos[0], campos[1], campos[2], campos[3], pais));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }

    // Escribe la lista en personal_fecha_hora.csv
    public static void escribirCSV(List<Persona> lista) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nombre = "personal_" + fecha + ".csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombre))) {
            pw.println("firstName,lastName,email,gender,country");
            for (Persona p : lista) {
                pw.println(p);
            }
            System.out.println("Datos guardados en " + nombre);
        } catch (Exception e) {
            System.out.println("Error al escribir el CSV: " + e.getMessage());
        }
    }

    // Lee un XML (DOM) y devuelve una lista de Persona
    public static List<Persona> leerXML(String path) {
        List<Persona> lista = new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new File(path));

            NodeList nodos = doc.getElementsByTagName("persona");
            for (int i = 0; i < nodos.getLength(); i++) {
                Element e = (Element) nodos.item(i);
                String firstName = e.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = e.getElementsByTagName("lastName").item(0).getTextContent();
                String email = e.getElementsByTagName("email").item(0).getTextContent();
                String gender = e.getElementsByTagName("gender").item(0).getTextContent();
                String country = e.getElementsByTagName("country").item(0).getTextContent();
                lista.add(new Persona(firstName, lastName, email, gender, country));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
        return lista;
    }

    // Lee un JSON (Gson) y devuelve una lista de Persona
    public static List<Persona> leerJSON(String path) {
        List<Persona> lista = new ArrayList<>();
        try (FileReader fr = new FileReader(path)) {
            Gson gson = new Gson();
            Persona[] personas = gson.fromJson(fr, Persona[].class);
            for (Persona p : personas) {
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el JSON: " + e.getMessage());
        }
        return lista;
    }
}