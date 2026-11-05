package Practica1_2;

public class Escritura {
    //Para separar el catalago de la biblioteca.
    public void separarCatalogo(Biblioteca biblioteca, Biblioteca juvenil, Biblioteca central) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getGeneros().contains("Juvenil") || libro.getGeneros().contains("Infantil")) {
                libro.setSede("JUVENIL");
                juvenil.añadirLibro(libro);
            } else{
                libro.setSede("CENTRAL");
                central.añadirLibro(libro);
            }
        }
    }
    public void escribirXML(){

    }
}
