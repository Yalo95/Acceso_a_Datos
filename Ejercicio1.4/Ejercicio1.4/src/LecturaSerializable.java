import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class LecturaSerializable {
    // Método para leer un fichero serializado que contiene una lista de objetos Libro
    public List<Libro> leerFicheroSerializado(String ruta) {
        // Inicializamos la lista de libros a null. Esta lista contendrá los objetos Libro leídos del fichero serializado
        List<Libro> librosSer = null;

        // Abrimos el fichero serializado usando ObjectInputStream para leer objetos
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            // Leemos el objeto del fichero y lo casteamos a una lista de libros
            librosSer = (List<Libro>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el fichero serializado: " + e.getMessage());
        }
        return librosSer;
    }
}
