package org.example;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
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

           Files.writeString(fichero_convertido,fichero_json);//Guardamos el fichero nuevo creado en el path del args[1]
           System.out.println("Conversion completada con éxito. El archivo " + fichero_convertir + " se ha convertido a "
                   + fichero_convertido);

       } catch (IOException | JSONException e) { //Una excepción por si hay problemas al leer o escribir y otra por si el xml no era valido.
           throw new RuntimeException(e);
       }
   }
    private static void convert_to_xml(Path fichero_convertir, Path fichero_convertido){
        try {

            String fichero = Files.readString(fichero_convertir);
            JSONObject object_json = new JSONObject(fichero);
            String fichero_xml = XML.toString(object_json); //Convertimos a String XML el objeto anterior.

            Files.writeString(fichero_convertido,fichero_xml);//Guardamos el fichero nuevo creado en el path del args[1]
            System.out.println("Conversion completada con éxito. " + fichero_convertir + " se ha convertido a "
                    + fichero_convertido);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        //Comprobamos y validamos los argumentos para ejecutar el programa.
        if (args.length != 2){ //Con esto nos aseguramos que la sintaxis del comando de ejecucion es la necesaria para desarrollar el programa.
            System.out.println("La sintaxis correcta para ejecutar el programa debe ser: java Conversor nombre_fichero nombre_fichero_reconvertido");
            return;
        }

       //args[0] es el nombre del fichero
        String nombre_fichero1 = args[0];
        Path fichero_convertir = Paths.get(nombre_fichero1);//convertimos el argumento en Path(ruta) para poder pasarlo a las funciones
        // args[1] El fichero convertido
        String nombre_fichero2 = args[1];
        Path fichero_convertido = Paths.get(nombre_fichero2);//Para pasar a path el argumento numero 2 y poder sacar el archivo1 convertido

        //Comprobamos que el fichero existe
        if (!Files.exists(fichero_convertir) || !Files.isReadable(fichero_convertir)) {
            System.out.println("El fichero " + nombre_fichero1 + " no existe o no se puede leer");
            return;
        }
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