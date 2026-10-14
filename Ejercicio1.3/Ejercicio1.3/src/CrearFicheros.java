import java.io.*;

public class CrearFicheros {
    // Método para rellenar un array con 20 números aleatorios entre 20 y 40
    int[] rellenarNumeros (){

        int [] arrayNumeros = new int[20];
        for (int i = 0; i < arrayNumeros.length; i++){
            arrayNumeros[i] = (int) (Math.random() * 21) + 20; // Genera un número aleatorio entre 20 y 40. * 21 para incluir el 40
                                                                // y + 20 para desplazar el rango a partir de 20
        }
        return arrayNumeros;
    }
    // Método para crear un fichero binario "numeros.dat" y escribir los números del array en él
    void crearFicheroBinario(int [] numeros) {
        try (RandomAccessFile inout = new RandomAccessFile("numeros.dat", "rw")) {
            for (int i = 0; i < numeros.length; i++) {
                inout.writeInt(numeros[i]); // Escribe cada número como un entero en el fichero binario
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Método para crear un fichero de texto "numeros.txt" y escribir los números del array en él
    void crearFicheroTexto(int [] numeros) {
        try (RandomAccessFile inout = new RandomAccessFile("numeros.txt", "rw")) {
            for (int i = 0; i < numeros.length; i++) {
                inout.writeBytes(Integer.toString(numeros[i]) + "\n"); // Escribe cada número como una cadena de texto seguida
                                                                            // de un salto de línea en el fichero de texto
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
