import java.io.*;
/**
 * Clase que se encarga de leer los números almacenados en el archivo binario (.dat) y el archivo de texto (.txt) y calcular su media.
 * Creamos el metodo mediaBinario() que devuelve un double y que lee el archivo binario "numeros.dat" usando DataInputStream y
 * BufferedInputStream. Se inicializan las variables suma y contador a 0. Se utiliza un bucle "infinito" while (a través del true)
 * para leer los números del archivo hasta que se alcance el final del archivo (EOFException). Dentro del bucle, se lee un número
 * entero del archivo (int numero = dis.readInt()) y se suma a la variable suma, y se incrementa el contador. Si se alcanza el final del
 * archivo, se rompe el bucle. Si ocurre una excepción de E/S, se lanza una RuntimeException. Después del bucle, si el contador es 0
 * (es decir, no se leyeron números), se devuelve 0. De lo contrario, se calcula la media dividiendo la suma entre el contador y se
 * devuelve el resultado como un double.
 */
public class LectorNumeros {

    double mediaBinario(){

        int suma = 0;
        int contador = 0;

        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("numeros.dat")))) {

            while (true) {
                try {
                    int numero = dis.readInt();
                    suma += numero;
                    contador++;
                } catch (EOFException e) {
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (contador == 0) {
                return 0;
            }
            return (double) suma / contador;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que calcula la media de los números almacenados en el archivo de texto "numeros.txt".
     * Se utiliza un BufferedReader para leer el archivo línea por línea. Se inicializan las variables suma y contador a 0.
     * Dentro del bucle while, se lee cada línea del archivo (Con readLine para que no sea caracter a caracter) y se convierte a un número
     * entero utilizando Integer.parseInt(). Se suma el número a la variable suma y se incrementa el contador. Si ocurre una excepción de
     * E/S, se lanza una RuntimeException. Después del bucle, si el contador es 0 (es decir, no se leyeron números), se devuelve 0.
     * De lo contrario, se calcula la media dividiendo la suma entre el contador y se devuelve el resultado como un double.
     * @return La media de los números leídos del archivo de texto.
     */

    double mediaTexto(){

        String linea;
        int suma = 0;
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("numeros.txt"))) {

            while ((linea = br.readLine()) != null) {
                    int numero = Integer.parseInt(linea);
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

