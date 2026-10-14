public class Main {
    public static void main(String[] args) {

        // Creamos instancias de las clases CrearFicheros y LeerFicheros
        CrearFicheros crearFicheros = new CrearFicheros();
        LeerFicheros leerFicheros = new LeerFicheros();
        int[] numeros = crearFicheros.rellenarNumeros(); // Rellenamos un array con 20 números aleatorios entre 20 y 40

        crearFicheros.crearFicheroBinario(numeros);// Creamos el fichero binario "numeros.dat" y escribimos los números del array en él
        crearFicheros.crearFicheroTexto(numeros);//

        double mediaBinario = leerFicheros.mediaBinario();// Calculamos la media de los números almacenados en el fichero binario
        double mediaTexto = leerFicheros.mediaTexto();// Calculamos la media de los números almacenados en el fichero txt
        System.out.println("Media del archivo binario: " + mediaBinario);
        System.out.println("Media del archivo de texto: " + mediaTexto);
    }
}