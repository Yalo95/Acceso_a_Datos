import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

class Main {
    static void main(String[] args) {
        //Ruta del fichero serializado
        String rutaSer = "C:\\Users\\Work\\Desktop\\DAM\\2ºDAM\\Acceso a Datos\\libros.ser";

        // 1. Crear una lista de 10 libros aleatorios a partir del CSV
        LecturaCsv lectorCsv = new LecturaCsv();
        List<Libro> diezLibros = lectorCsv.crearLibrosAleatorio();

        // 2. Serializar la lista de 10 libros en un fichero binario
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaSer))) {
            out.writeObject(diezLibros);
            out.flush();
            System.out.println("Fichero serializado creado con éxito en: " + rutaSer + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Leer la lista de libros desde el fichero serializado y mostrarla por pantalla
        System.out.println("--- Libros recuperados desde el fichero serializado ---");

        // Creamos un objeto de la clase LecturaSerializable para leer el fichero serializado
        LecturaSerializable lectorSer = new LecturaSerializable();

        // Llamamos al método leerFicheroSerializado pasando la ruta del fichero y guardamos la lista de libros recuperados
        List<Libro> librosRecuperados = lectorSer.leerFicheroSerializado(rutaSer);

        // Comprobamos si la lista de libros recuperados no es nula ni está vacía.
        // Si tiene elementos, los mostramos por pantalla.
        if (librosRecuperados != null && !librosRecuperados.isEmpty()) {
            for (Libro libro : librosRecuperados) {
                System.out.println(libro);
            }
        } else {
            System.out.println("La lista recuperada está vacía.");
        }
    }
}
