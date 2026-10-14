import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Mapa extends Localidad {

    public HashMap<String,Localidad> leerCsv()  { //***Rehacerlo*** Quizas hacerlo en una coleccion de objetos, para guardarlo ahi y luego otra clase para guardar eso en un hashmap.

        HashMap<String,Localidad> localidades = new HashMap<>();

        String ruta = "C:\\Users\\alumnom\\Desktop\\2ºDAM_ZV\\EntregasAccesoDatos\\geografia.csv";

        try (Scanner sc = new Scanner(new File(ruta), "UTF-8")){
            sc.useDelimiter(";|\\r?\\n");

            while(sc.hasNext()){

                String id = sc.hasNext() ? sc.next().trim() : "";
                float latitude = sc.hasNextFloat() ? sc.nextFloat() : null; //Condición para leer los decimales. El null final es lo que escribe si no encuentra nada para leer
                float longitude = sc.hasNextFloat() ? sc.nextFloat() : null;;
                int elevation = sc.hasNextInt() ? sc.nextInt() : null;
                String name = sc.hasNext() ? sc.next() : "";
                String url = sc.hasNext() ? sc.next() : "";
                int year = sc.hasNextInt() ? sc.nextInt() : null;

                //Comprobamos que el ISBN no sea la cabecera del CSV ni esté vacío. Si es así,
                // continuamos con la siguiente iteración. Así evitamos añadir un libro con ISBN inválido a la lista o
                // que se añada la cabecera del CSV como un libro más.
                if (id.equalsIgnoreCase("ID") || id.isEmpty()) {
                    continue;
                }
            }
            localidades.put(id,);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return localidades;
    }

    //
}
