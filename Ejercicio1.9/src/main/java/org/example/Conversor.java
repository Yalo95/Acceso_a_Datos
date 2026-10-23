package org.example;


import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Conversor {


   private static void convert_to_json(Path fichero_convertir, Path fichero_convertido){

       try {

            String fichero = Files.readString(fichero_convertir); //Leemos el fichero en String con la libreria Files y guardamos con String
            JSONObject object_json = XML.toJSONObject(fichero);//Pasamos el string del fichero a un objeto JSON con la libreria de XML, ya bien estructurado y separado.
            String fichero_json = object_json.toString(); //Convertimos a Strin JSON el objeto anterior.

            Path ficheroJSON = Files.writeString(fichero_convertido,fichero_json);
           System.out.println("Conversion completada con éxito. El archivo " + fichero_convertir + " se ha convertido a " + fichero_convertido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }

    /*//FORMA MAS TRABAJOSA SIN USAR LAS LIBRERIAS, LO QUE HACEN ESAS LIBRERIAS PARA RECORRER EL ARBOL.//
    private static void convert_to_json(Path fichero_convertir){

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("Ejercicio1.8\\src\\main\\resources" + fichero_convertir));
            document.getDocumentElement();
            
            Element elemento_raiz = document.getDocumentElement(); //Para obtener el elemento raiz del fichero xml
            NodeList nodeList = elemento_raiz.getChildNodes(); //Para obtener los nodos hijos del elemento raiz.
            for (int i = 0; i < nodeList.getLength(); i++) { //Recorremos los nodos hijos del elemento raiz  y los guardamos en node.
                Element nodeChild = (Element) nodeList.item(i);
            }
            element_convert(nodeChild);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    private static Object element_convert (NodeList nodechild){

        for (int i = 0; i < nodechild.getLength(); i++) { //Recorremos los nodos hijos del elemento raiz  y los guardamos en node.
            Element nodeChild2 = (Element) nodechild.item(i);//Se castea para poder guardarlo de nodo a elemento, con .item(i)
        }
    }*/
    private static void convert_to_xml(Path fichero_convertir, Path fichero_convertido){

        String ruta = "Ejercicio1.8\\src\\main\\resources";


    }
    public static void main(String[] args) {

        //Comprobamos y validamos los argumentos para ejecutar el programa.
        if (args.length != 2){ //Con esto nos aseguramos que la sintaxis del comando de ejecucion es la necesaria para desarrollar el programa.
            System.out.println("La sintaxis correcta para ejecutar el programa debe ser: java Conversor nombre_fichero nombre_fichero_reconvertido");
            return;
        }

        // Comprobamos que todos los ficheros existen antes de crear el fichero final
        for (String argumento : args) {
            File fichero = new File(argumento);
            if (!fichero.isFile() || !fichero.canRead()) {
                System.out.println("El fichero " + argumento + " no existe o no se puede leer");
                return;
            }
        }

        //args[0] es el nombre del fichero
        String nombre_fichero1 = args[0];
        Path fichero_convertir = Paths.get(nombre_fichero1);//convertimos el argumento en Path(ruta) para poder pasarlo a las funciones

        // args[1] El fichero a convertir
        String nombre_fichero2 = args[1];
        Path fichero_convertido = Paths.get(nombre_fichero2);//Para pasar a path el argumento numero 2 y poder sacar el archivo1 convertido

        //Para validar y comprobar las extensiones de los documentos.
        if (nombre_fichero1.endsWith(".xml") && nombre_fichero2.endsWith(".json")){
            convert_to_json(fichero_convertir,fichero_convertido);
        } else if (nombre_fichero1.endsWith(".json") && nombre_fichero2.endsWith(".xml")){
            convert_to_xml(fichero_convertir, fichero_convertido);
        }else {
            System.out.println("Fallo en el nombre o en la extension del fichero, la conversión no se puede realizar");
        }
    }
}

