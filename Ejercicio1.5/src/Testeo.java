import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Testeo {
    String ruta = "C:\\Users\\Work\\Desktop\\DAM\\2ºDAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio1.5\\geografia.csv";
    public void testeoGuardar (){

        Mapa mapa = new Mapa();


        try (Scanner sc = new Scanner(new File(ruta), "UTF-8")){

            if(sc.hasNextLine()){ // Para saltar la cabecera
                sc.nextLine();
            }

            while(sc.hasNextLine()){
                String linea = sc.nextLine().trim(); // NextLine para el salto de linea y trim para quitar si tiene espacios
                if (linea.isEmpty()){               // en blanco al principio y al final
                    continue;
                }
                String [] campos = linea.split(";"); // Creamos una lista para guardar los distintos campos del archivo. Asegurandonos
                                                        // de que se el string de la linea se parte por cada ; (gracias al metodo split)

                //Creamos la localidad y le añadimos al Mapa
                Localidad localidad = new Localidad();
                localidad.setId(campos[0].trim()); //Guardamos cada string en su campo correspondiente
                localidad.setLatitude(Float.parseFloat(campos[1].trim()));
                localidad.setLongitude(Float.parseFloat(campos[2].trim()));
                localidad.setElevation(Integer.parseInt(campos[3].trim()));
                localidad.setName(campos[4].trim());
                localidad.setUrl(campos[5].trim());
                localidad.setYear(Integer.parseInt(campos[6].trim()));
                mapa.addLocalidad(localidad);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No ha sido posible encontrar el archivo " + e.getMessage());
        }

        System.out.println(mapa); //Para mostrar el contenido del mapa


        try (ObjectOutputStream oos = new ObjectOutputStream( // Para guardar el objeto Mapa serializado
                new FileOutputStream("C:\\Users\\Work\\Desktop\\DAM\\2ºDAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio1.5\\mapa.dat"))) {
            oos.writeObject(mapa);
            System.out.println("Mapa guardado en la ruta correspondiente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void testeoBuscador() {

    }

    static void main() {

    }
}
