import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public static void particion (Path fichero, int trozosValidados)  {

        try (InputStream ips = new BufferedInputStream(new FileInputStream(fichero.toFile()))){ //Como no puede leer un path como ruta lo pasamos a file con .toFile

            long tamanioTotal = Files.size(fichero); //Con este Files.size() nos devuelve el numero de bytes que ocupa el fichero.
            long tamanioTrozo = tamanioTotal/trozosValidados; //Operación para obtener el tamaño de cada trozo.
            long tamanioRestante = tamanioTotal%trozosValidados; //El operador % nos devuelve el resto directamente de la operacion marcada.

            // Aseguramos que no se pidan mas trozos que bytes tiene el fichero o que el fichero no esté vacío
            if (tamanioTrozo == 0) {
                System.out.println("El fichero tiene menos bytes que trozos pedidos, no se puede dividir.");
                return;
            }
            System.out.println("Estos son los diferentes tamaños: " + "Tamaño Total: " + tamanioTotal + "\n" +
                    "Tamaño de cada trozo: " + tamanioTrozo + "\n" + "Tamaño restante: " + tamanioRestante);

            for (int i = 0; i < trozosValidados; i++) {

                long bytesDelTrozo = tamanioTrozo;
                if (i == trozosValidados - 1) {
                    bytesDelTrozo += tamanioRestante;   // el último trozo absorbe el resto
                }

                try (FileOutputStream salida = new FileOutputStream(fichero + ".parte" + (i + 1))) {
                    for (long j = 0; j < bytesDelTrozo; j++) {
                        salida.write(ips.read());
                    }
                }
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
