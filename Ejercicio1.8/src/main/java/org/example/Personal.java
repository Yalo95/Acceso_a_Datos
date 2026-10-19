package org.example;

import java.util.ArrayList;
import java.util.List;


public class Personal {//Agregacion de objetos Persona

    private List<Persona> lista; //Donde vamos a guardar todas las personas


    public Personal() {
        this.lista = new ArrayList<>();
    }//Constructor vacio que inicializa la coleccion vacia

    // Constructor que inicializa con una lista ya existente
    public Personal(List<Persona> personas) {//Creamos una copia de una lista que ya existe
        this.lista = new ArrayList<>(personas);
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void addPersona(Persona p) {
        lista.add(p);
    } //Añadimos personas a la lista


    public List<Persona> getPersonasPorPais(String pais) {//Devuelve una lista nueva con las personas que son de ese país.
        List<Persona> resultado = new ArrayList<>(); //Lista vacía
        for (Persona p : lista) { //Recorremos la lista
            if (p.getCountry() != null && p.getCountry().trim().equalsIgnoreCase(pais.trim())) { //equalsIgnoreCase compara textos ignorando mayus y minus.
                resultado.add(p);
            }
        }
        return resultado;//Devolvemos la lista con las personas que coinciden en país.
    }

    public boolean borrarPorEmail(String email) {//Para borrar a una persona pasandole el email de la misma.
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEmail().equalsIgnoreCase(email.trim())) {
                lista.remove(i);
                return true; //Devuelve true si lo ha encontrado y borrado de la lista.
            }
        }
        return false;//Devuelve false si no lo encuentra
    }
    //Cada uno llama al metodo de lectura correspondiente de Auxiliar, que devuelve una lista de personas, y la añade entera con addAll
    public void addDesdeCSV(String path) {
        lista.addAll(Auxiliar.leerCSV(path));
    }

    public void addDesdeXML(String path) {
        lista.addAll(Auxiliar.leerXML(path));
    }

    public void addDesdeJSON(String path) {
        lista.addAll(Auxiliar.leerJSON(path));
    }

    @Override
    public String toString() { // Vamos añadiendo al sb vacío cada persona y un salto de línea, y al terminar devuelve todo como un solo String
        StringBuilder sb = new StringBuilder();//StringBuilder (es como una libreta vacía) sirve para ir uniendo texto de forma eficiente
                                        // Es lo mismo que haber hecho texto = texto + p + "\n" dentro del bucle, pero más eficiente.
        for (Persona p : lista) {
            sb.append(p).append("\n"); //Se añade el texto de esa persona a sb y añade un salto de línea, para que cada persona quede en su línea
        }
        return sb.toString();//Para convertir todo en un String y devolverlo.
    }
}