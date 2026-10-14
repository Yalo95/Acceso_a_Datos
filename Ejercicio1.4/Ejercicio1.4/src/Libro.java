public class Libro implements java.io.Serializable {

    final String isbn;
    final String titulo;
    final String coleccion;
    // Constructor de la clase Libro que inicializa los atributos isbn, titulo y coleccion
    public Libro(String isbn, String titulo, String coleccion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.coleccion = coleccion;

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getColeccion() {
        return coleccion;
    }
    // Sobrescribimos el método toString() para mostrar la información del libro de manera legible
    @Override
    public String toString() {
        return "Libro{" +
                "ISBN = '" + isbn + '\'' +
                ", Título = '" + titulo + '\'' +
                ", Colección = '" + coleccion + '\'' +
                '}';
    }
}
