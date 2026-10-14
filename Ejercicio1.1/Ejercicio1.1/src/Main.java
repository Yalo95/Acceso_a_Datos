import java.io.*;
/**
 * Clase principal que contiene el método main para ejecutar el programa.
 * LLama a la clase ContadorNumeros y luego escribe esos números en un archivo binario (.dat) y
 * en un archivo de texto (.txt). Todo lo hacen a través de un bucle for each que recorre el array
 * de numeros y lo guarda en content como enteros. Salvo en el caso del archivo de texto que se guarda
 * como String(String.valueOf(content)) y se añade un salto de línea.
 */
class Main {
    public static void main(String[] args) {

        ContadorNumeros contadorNumeros = new ContadorNumeros();
        int[] numeros = contadorNumeros.rellenarNumeros();

        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("numeros.dat")))) {

            for (int content : numeros) {
                dos.writeInt(content);
            }
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileName = "numeros.txt";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {

            for (int content : numeros) {
                bw.write(String.valueOf(content));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
