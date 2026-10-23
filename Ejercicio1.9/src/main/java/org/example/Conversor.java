package org.example;


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


    private static Files convert_to_json(Path fichero_convertir){

        String ruta = "Ejercicio1.8\\src\\main\\resources";

        Files ficheroToString = new Files(fichero_convertir);

                    XML conversion = ficheroToString();

        }
    }

    //FORMA MAS TRABAJOSA SIN USAR LAS LIBRERIAS, LO QUE HACEN ESAS LIBRERIAS PARA RECORRER EL ARBOL.//
    /* private static void convert_to_json(Path fichero_convertir){

        Map biblioteca = new HashMap();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("Ejercicio1.8\\src\\main\\resources" + fichero_convertir));

            Element elemento_raiz = document.getDocumentElement(); //Para obtener el elemento raiz del fichero xml
            NodeList nodeList = elemento_raiz.getChildNodes(); //Para obtener los nodos hijos del elemento raiz.
            for (int i = 0; i < nodeList.getLength(); i++) { //Recorremos los nodos hijos del elemento raiz  y los guardamos en node.
                Element nodeChild = (Element) nodeList.item(i); //Se castea para poder guardarlo de nodo a elemento, con .item(i)
                element_convert(nodeChild);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    private static Object element_convert (Element nodechild){

        NodeList nodeListChild = nodechild.getChildNodes(); //Para obtener los nodos hijos de los nodos principales.
        for (int i = 0; i < nodeListChild.getLength(); i++) { //Recorremos los nodos hijos del elemento raiz  y los guardamos en node.
            Node nodeChild = nodeListChild.item(i);
        }

        return null;
    }*/
    private static void convert_to_xml(Path fichero_convertir){

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

        //
        if (nombre_fichero1.endsWith(".xml") && nombre_fichero2.endsWith(".json")){
            convert_to_json(fichero_convertir);
        } else if (nombre_fichero1.endsWith(".json") && nombre_fichero2.endsWith(".xml")){
            convert_to_xml(fichero_convertir);
        }else {
            System.out.println("Fallo en el nombre o en la extension del fichero, la conversión no se puede realizar");
        }

    }
}
