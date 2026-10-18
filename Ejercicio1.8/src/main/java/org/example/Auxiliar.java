package org.example;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/** Metodos estaticos para leer y escribir ficheros CSV, XML y JSON. */
public class Auxiliar {

    // ---------------------------------------------------------------- CSV

    /** Lee un CSV (con cabecera) y devuelve la lista de personas. */
    public static List<Persona> leerCSV(String path) {
        List<Persona> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea = br.readLine(); // se salta la cabecera
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                // Separa por comas que NO estan dentro de comillas
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length < 5) continue;
                String pais = campos[4].trim().replace("\"", "");
                lista.add(new Persona(campos[0], campos[1], campos[2], campos[3], pais));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el CSV: " + e.getMessage());
        }
        return lista;
    }

    /** Escribe la lista en personal_fecha_hora.csv. Devuelve el nombre del fichero. */
    public static String escribirCSV(List<Persona> lista) {
        String nombre = "personal_" + fechaHora() + ".csv";
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombre))) {
            pw.println("firstName,lastName,email,gender,country");
            for (Persona p : lista) {
                pw.println(p); // toString ya tiene formato CSV
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo el CSV: " + e.getMessage());
        }
        return nombre;
    }

    // ---------------------------------------------------------------- XML (DOM)

    public static List<Persona> leerXML(String path) {
        List<Persona> lista = new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList personas = doc.getElementsByTagName("persona");
            for (int i = 0; i < personas.getLength(); i++) {
                Node nodo = personas.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    Persona p = new Persona(
                            texto(e, "firstName"),
                            texto(e, "lastName"),
                            texto(e, "email"),
                            texto(e, "gender"),
                            texto(e, "country"));
                    lista.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el XML: " + e.getMessage());
        }
        return lista;
    }

    // Devuelve el texto de una etiqueta hija
    private static String texto(Element e, String etiqueta) {
        NodeList nl = e.getElementsByTagName(etiqueta);
        if (nl.getLength() == 0) return "";
        return nl.item(0).getTextContent().trim();
    }

    /** Escribe la lista en personal_fecha_hora.xml. Devuelve el nombre del fichero. */
    public static String escribirXML(List<Persona> lista) {
        String nombre = "personal_" + fechaHora() + ".xml";
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.newDocument();
            Element raiz = doc.createElement("personal");
            doc.appendChild(raiz);

            for (Persona p : lista) {
                Element persona = doc.createElement("persona");
                anadir(doc, persona, "country", p.getCountry());
                anadir(doc, persona, "email", p.getEmail());
                anadir(doc, persona, "firstName", p.getFirstName());
                anadir(doc, persona, "gender", p.getGender());
                anadir(doc, persona, "lastName", p.getLastName());
                raiz.appendChild(persona);
            }

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.transform(new DOMSource(doc), new StreamResult(new File(nombre)));
        } catch (Exception e) {
            System.out.println("Error escribiendo el XML: " + e.getMessage());
        }
        return nombre;
    }

    private static void anadir(Document doc, Element padre, String etiqueta, String valor) {
        Element el = doc.createElement(etiqueta);
        el.appendChild(doc.createTextNode(valor));
        padre.appendChild(el);
    }

    // ---------------------------------------------------------------- JSON (Gson)

    public static List<Persona> leerJSON(String path) {
        List<Persona> lista = new ArrayList<>();
        try (FileReader fr = new FileReader(path)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Persona>>() {}.getType();
            lista = gson.fromJson(fr, tipoLista);
        } catch (Exception e) {
            System.out.println("Error leyendo el JSON: " + e.getMessage());
        }
        return lista;
    }

    /** Escribe la lista en personal_fecha_hora.json. Devuelve el nombre del fichero. */
    public static String escribirJSON(List<Persona> lista) {
        String nombre = "personal_" + fechaHora() + ".json";
        try (FileWriter fw = new FileWriter(nombre)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(lista, fw);
        } catch (IOException e) {
            System.out.println("Error escribiendo el JSON: " + e.getMessage());
        }
        return nombre;
    }

    // ---------------------------------------------------------------- util

    private static String fechaHora() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}