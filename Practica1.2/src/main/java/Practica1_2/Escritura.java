package Practica1_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Escritura {
    //Para separar el catalago de la biblioteca.
    public void separarCatalogo(Biblioteca biblioteca, Biblioteca juvenil, Biblioteca central) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getGeneros().contains("Juvenil") || libro.getGeneros().contains("Infantil")) {
                libro.setSede("JUVENIL");
                juvenil.añadirLibro(libro);
            } else{
                libro.setSede("CENTRAL");
                central.añadirLibro(libro);
            }
        }
    }

    //ESCRITURA XML Y VALIDACION CON XSD

    // Implementación para escribir la biblioteca en un archivo XML, validando con el XSD correspondiente.
    public void escribirXML(Biblioteca juvenil, String ficheroxsd, String ficheroXml) throws JAXBException, SAXException {

        //Aquí validamos el xml con el xsd
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(ficheroxsd));//Aqui leemos el archivo xsd y lo guardamos como un objeto Schema.

        JAXBContext context = JAXBContext.newInstance(Biblioteca.class);//Se pone el .class para que pueda leer la clase del ElementoRaiz y analizar su estructura
        Marshaller marshaller = context.createMarshaller();
        marshaller.setSchema(schema);//Esta línea es la que valida la estructura del xml con la del xsd. Aqui se efectua la validación.
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        File bibliotecaJuvenilXMLFile = new File(ficheroXml);
        marshaller.marshal(juvenil,bibliotecaJuvenilXMLFile);
        //Aqui convertimos el objeto biblioteca juvenil en XML y lo validamos con el xsd, si es correcto lo escribimos en el archivo XML.
        // Si no cumple con el xsd, se lanza una excepción y no se escribe el archivo XML.
    }

    //ESCRITURA JSON

    public void escribirJson(Biblioteca central, String ficheroJson) throws IOException {//Escribimos el resto de los libros en formato Json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();//Creamos el objeto GSON que convierte objetos Java a Json.
        //con el setPrettyPrinting() hacemos que el Json se escriba con saltos de línea y sangrías, para que sea más legible.

        //Abrimos el fichero para escribir en el con el formato UTF-8, para que no haya problemas con caracteres especiales.
        try (Writer writer = Files.newBufferedWriter(Paths.get(ficheroJson), StandardCharsets.UTF_8)) {
            gson.toJson(central, writer); //Escribimos el objeto Biblioteca central en formato JSON en el archivo especificado
        }
    }
}
