import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LecturaCsv {

    public List<Libro> leerCsv() {
        // Creamos una lista de libros para almacenar los objetos Libro leídos del CSV
        List<Libro> libros = new ArrayList<>();
        String ruta = "C:\\Users\\alumnom\\Desktop\\2ºDAM_ZV\\EntregasAccesoDatos\\Libros.csv";

        //Abrimos el archivo CSV usando Scanner y especificamos la codificación Windows-1252 (que es la que tiene el CSV)
        // para evitar problemas con caracteres especiales
        try (Scanner sc = new Scanner(new File(ruta), "Windows-1252")) {
            sc.useDelimiter(";|\\r?\\n"); // Usamos ; como delimitador de campos y \r?\n para saltos de línea

            // Leemos cada línea del CSV y creamos un objeto Libro con isbn, titulo y coleccion
            while (sc.hasNext()) {
                String isbn = sc.next().trim(); // Leemos el ISBN y eliminamos espacios en blanco al inicio y al final el .trim() quita los espacios en blanco
                String codigo = sc.hasNext() ? sc.next().trim() : "";// (el ? : es un if) El hashNext nos dice si quedan mas datos por leer y si es que si
                                                                    // salta al sc.next().trim() guardando los datos en codigo. Si es que no guarda "", es decir
                                                                    //un vacio y pasa a la siguiente fila.
                String titulo = sc.hasNext() ? sc.next().trim() : "";
                String grados = sc.hasNext() ? sc.next().trim() : "";
                String coleccion = sc.hasNext() ? sc.next().trim() : "";
                //
                String ano = sc.hasNext() ? sc.next().trim() : "";
                String paginas = sc.hasNext() ? sc.next().trim() : "";
                String interior = sc.hasNext() ? sc.next().trim() : "";
                String peso = sc.hasNext() ? sc.next().trim() : "";
                String tamano = sc.hasNext() ? sc.next().trim() : "";
                String precio = sc.hasNext() ? sc.next().trim() : "";
                String codBarras = sc.hasNext() ? sc.next().trim() : "";
                String imagen = sc.hasNext() ? sc.next().trim() : "";

                //Comprobamos que el ISBN no sea la cabecera del CSV ni esté vacío. Si es así,
                // continuamos con la siguiente iteración. Así evitamos añadir un libro con ISBN inválido a la lista o
                // que se añada la cabecera del CSV como un libro más.
                if (isbn.equalsIgnoreCase("ISBN") || isbn.isEmpty()) {
                    continue;
                }

                // Guardamos el objeto Libro con isbn, titulo y coleccion
                libros.add(new Libro(isbn, titulo, coleccion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public List<Libro> crearLibrosAleatorio() {

        // Leemos todos los libros del CSV guardandolos en una lista y creamos otra lista para los 10 libros aleatorios
        List<Libro> todosLosLibros = leerCsv();
        List<Libro> librosAleatorios = new ArrayList<>();
        //Con este if comprobamos que hay al menos 10 libros en el CSV.
        //Si no es así, mostramos un mensaje y devolvemos la lista vacía
        if (todosLosLibros.size() < 10) {
            System.out.println("No hay suficientes libros en el fichero CSV (mínimo 10).");
            return todosLosLibros;
        }
        // Mientras la lista de libros aleatorios tenga menos de 10 elementos,
        //generamos un índice aleatorio y añadimos el libro correspondiente a la lista si no está ya presente
        while (librosAleatorios.size() < 10) {
            int indiceAleatorio = (int) (Math.random() * todosLosLibros.size());
            Libro libroAleatorio = todosLosLibros.get(indiceAleatorio);
            // Comprobamos si el libro ya está en la lista de libros aleatorios antes de añadirlo
            if (!librosAleatorios.contains(libroAleatorio)) {
                librosAleatorios.add(libroAleatorio);
            }
        }
        return librosAleatorios;
    }
}