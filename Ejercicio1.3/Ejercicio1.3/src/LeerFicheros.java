import java.io.*;

public class LeerFicheros {

    // Método para calcular la media de los números almacenados en el fichero binario "numeros.dat"
    double mediaBinario(){
        int suma = 0;
        int contador = 0;
        // Abrimos el fichero binario "numeros.dat" en modo lectura
        try (RandomAccessFile inout = new RandomAccessFile("numeros.dat", "r")) {

            // Leemos los números del fichero binario hasta llegar al final del archivo
            while(inout.getFilePointer() < inout.length()) {
                int numero = inout.readInt();
                suma += numero;
                contador++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } if (contador == 0) { // Si no se han leído números, devolvemos 0 para evitar división por cero
            return 0;
        }
        return (double) suma / contador;
    }

    // Método para calcular la media de los números almacenados en el fichero de texto "numeros.txt"
    double mediaTexto(){

        String linea;
        int suma = 0;
        int contador = 0;
        // Abrimos el fichero de texto "numeros.txt" en modo lectura
        try (RandomAccessFile inout = new RandomAccessFile("numeros.txt", "r")) {

            // Leemos las líneas del fichero de texto y convertimos cada una a un entero
            while ((linea = inout.readLine()) != null) { // Leemos una línea del fichero de texto
                int numero = Integer.parseInt(linea); // Convertimos la línea leída a un número entero
                suma += numero;
                contador++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } if (contador == 0) {
            return 0;
        }
        return (double) suma / contador;
    }
}
