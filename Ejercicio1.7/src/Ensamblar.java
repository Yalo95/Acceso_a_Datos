import java.io.*;

public class Ensamblar {

    public static void main(String[] args) {

        if (args.length == 0) { // Necesitamos al menos un trozo para poder ensamblar
            System.out.println("La sintaxis correcta es: java Ensamblar nombrefichero1 nombrefichero2...");
            return;
        }

        // Comprobamos que todos los trozos existen antes de crear el fichero final
        for (String argumento : args) {
            File trozo = new File(argumento);
            if (!trozo.isFile() || !trozo.canRead()) {
                System.out.println("El fichero " + argumento + " no existe o no se puede leer");
                return;
            }
        }

        String ficheroSalida = "ficheroEnsamblado"; // Nombre del fichero resultante
        long totalBytes = 0;

        // BufferedOutputStream sobre FileOutputStream: escribe grupos de bytes (flujo binario con buffer)
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ficheroSalida))) {

            byte[] buffer = new byte[1108]; // Array donde guardamos los bytes que vamos leyendo

            for (String argumento : args) { // Una vuelta por cada trozo, en el orden en que se han escrito
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(argumento))) {
                    int bytesLeidos = bis.read(buffer);//Guardamos cuántos bytes ha leído read en cada tanda.
                    //.read(buffer)Lee del fichero y rellena el array buffer con hasta 1108 bytes. Devuelve un número:
                    //Si lee bytes, devuelve cuántos ha leído (por ejemplo 1108, o menos si queda poco). Si ya no quedan bytes (llegó al final, EOF), devuelve -1.
                    while (bytesLeidos != -1) { // Mientras bytesleidos no sea -1 (EOF)
                        bos.write(buffer, 0, bytesLeidos);//Escribimos en el fichero final los bytes del buffer, desde la posición 0 y solo los que se han leído
                        totalBytes += bytesLeidos; //Actualizamos el contador añadiendo los bytes ya leidos.
                        bytesLeidos = bis.read(buffer); // Leemos la siguiente tanda
                    }
                }
            }
            System.out.println("Fichero ensamblado en '" + ficheroSalida + "' (" + totalBytes + " bytes).");

        } catch (IOException e) {
            System.out.println("No se ha podido ensamblar: " + e.getMessage());
        }
        // Compilar: javac Ensamblar.java
        // Ejecutar: java Ensamblar texto.txt.parte1 texto.txt.parte2 texto.txt.parte3 texto.txt.parte4
        // Para comprobar que esta bien, el tamaño (length) debe coincidir con el del fichero original.
    }
}
