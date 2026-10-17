import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Leer {

    public static boolean validarFicheroEntrada(Path fichero) { //Validamos que el fichero existe y se puede leer, sino da error.

        boolean valido = true;

        if (!Files.isRegularFile(fichero) || !Files.isReadable(fichero)) {
            System.out.println("El fichero no existe o es inválido");
            valido = false;
        }
        return valido;
    }
    public static boolean validarNumeroTrozos(String numeroTrozos){//Pasamos a entero args[1] que es el numero de trozos en los que se quiere
        //partir y nos aseguramos que el numero sea un entero valido para que se pueda dividir.

        boolean valido = true;

        try {
            int trozos = Integer.parseInt(numeroTrozos);

            if(trozos <= 0){
                System.out.println("El numero de trozos requerido no es valido");
                valido = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("El numero de trozos deber ser un entero");
            valido = false;
        }

        return valido;
    }
    public static void particion(Path fichero, int trozosValidados) {

        try {
            byte[] datos = Files.readAllBytes(fichero);
            int tamanioTrozo = datos.length / trozos;

            if (tamanioTrozo == 0) {
                System.out.println("El fichero es demasiado pequeño para dividirlo en " + trozos + " trozos.");
                return;
            }

            for (int i = 0; i < trozos; i++) {
                int inicio = i * tamanioTrozo;
                int fin = (i == trozos - 1) ? datos.length : inicio + tamanioTrozo;

                byte[] trozo = Arrays.copyOfRange(datos, inicio, fin);
                Files.write(Paths.get(fichero + ".parte" + (i + 1)), trozo);
            }

            System.out.println("Fichero dividido en " + trozos + " trozos.");

        } catch (IOException e) {
            System.out.println("No se ha podido realizar la partición: " + e.getMessage());
        }
    }
    public static void main(String[] args) {

        if (args.length != 2){ //Con esto nos aseguramos que la sintaxis del comando de ejecucion es la necesaria para desarrollar el programa.
            System.out.println("La sintaxis correcta para ejecutar el programa debe ser: java Leer nombre_fichero numero_de_trozos");
            return;
        }

        //args[0] es el nombre del fichero
        String rutaArchivo = args[0];
        Path fichero = Paths.get(rutaArchivo);
        // args[1] es "4". Por eso lo guardamos en un String, para convertirlo en el metodo validarNumeroTrozos.
        String numeroDeTrozos = args[1];

            if (!validarFicheroEntrada(fichero) || !validarNumeroTrozos(numeroDeTrozos)) { //Para saber si el fichero y el numero no son validos y cerrar el programa
                return; //Para dar por concluido el programa
            }

        int trozosValidados = Integer.parseInt(numeroDeTrozos);
        particion(fichero, trozosValidados);
        //Hay que compilar con javac Leer.java en la terminal dentro de la carpeta donde este Leer.java.
        // Asi se crea Leer.class en la carpeta src. Este paso de compilar hay que hacerlo cada vez que haya un cambio en Leer.java
        //por minimo que sea. Se puede poner asi en la terminal javac Leer.java; java Leer texto.txt 4 para ahorrar tiempo.
    }
}
