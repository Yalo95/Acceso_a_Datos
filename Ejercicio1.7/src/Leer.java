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
            byte[] datos = Files.readAllBytes(fichero); //Leemos el fichero y lo guardamos en bytes en un array, con 1 byte por posicion(Ej: Un fichero de 10 byte da un array de 10 posiciones)
            int tamanioTrozo = datos.length / trozosValidados; //Hacemos el cálculo para saber el tamaño de cada trozo en entero.

            if (tamanioTrozo == 0) { //Nos aseguramos de que el fichero se pueda dividir en los números de trozos.
                System.out.println("El fichero es demasiado pequeño para dividirlo en " + trozosValidados + " trozos.");
                return;
            }
            for (int i = 0; i < trozosValidados; i++) {// Una vuelta por cada trozo en el que hay que dividir el fichero
                int inicio = i * tamanioTrozo;//Posición del array donde empieza este trozo(cada uno empieza donde acabó el anterior, de ahi la multiplicacion)
                int fin = (i == trozosValidados - 1) ? datos.length : inicio + tamanioTrozo;//Posición donde acaba (sin incluirla, porque empieza en 0, por eso el -1).
                //En el último trozo llega hasta el final del array, así absorbe los bytes sobrantes de la división y no se pierde ninguno
                byte[] trozo = Arrays.copyOfRange(datos, inicio, fin);//copyOfRange crea un array nuevo con una copia de las posiciones desde inicio hasta fin (sin incluir fin).
                // Por ejemplo, copyOfRange(datos, 2, 4) devuelve las posiciones 2 y 3. En este caso seria fin - 1.
                Files.write(Paths.get(fichero + ".parte" + (i + 1)), trozo);//Crea el fichero "nombre.parteN" (N empieza en 1) y
                // escribe en él los bytes del array de trozo para cada trozo.
            }
            System.out.println("Fichero dividido en " + trozosValidados + " trozos.");
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
        // args[1] es "4". Por eso lo guardamos en un String, para validarlo en el metodo validarNumeroTrozos.
        String numeroDeTrozos = args[1];

        if (!validarFicheroEntrada(fichero) || !validarNumeroTrozos(numeroDeTrozos)) { //Para saber si el fichero y el numero no son validos y cerrar el programa
            return; //Para dar por concluido el programa
        }
        int trozosValidados = Integer.parseInt(numeroDeTrozos);//Pasamos de String a entero args[1]
        particion(fichero, trozosValidados);
        //Hay que compilar con javac Leer.java en la terminal dentro de la carpeta donde este Leer.java.
        // Asi se crea Leer.class en la carpeta src. Este paso de compilar hay que hacerlo cada vez que haya un cambio en Leer.java
        //por minimo que sea. Se puede poner asi en la terminal javac Leer.java; java Leer texto.txt 4 para ahorrar tiempo.
        //En la terminal tenemos el length que nos indica el numero de bytes de cada documento. Si la suma es = a la del fichero, esta bien.
    }
}
