package org.example;

import java.util.ArrayList;
import java.util.List;

/** Agregacion de objetos Persona. */
public class Personal {

    private List<Persona> lista;

    // Constructor que inicializa la coleccion vacia
    public Personal() {
        this.lista = new ArrayList<>();
    }

    // Constructor que inicializa con una lista ya existente
    public Personal(List<Persona> personas) {
        this.lista = new ArrayList<>(personas);
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void addPersona(Persona p) {
        lista.add(p);
    }

    /** Devuelve las personas de un pais (sin distinguir mayusculas). */
    public List<Persona> getPersonasPorPais(String pais) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona p : lista) {
            if (p.getCountry() != null && p.getCountry().trim().equalsIgnoreCase(pais.trim())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /** Borra la persona con ese email. Devuelve true si se borro. */
    public boolean borrarPorEmail(String email) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEmail().equalsIgnoreCase(email.trim())) {
                lista.remove(i);
                return true;
            }
        }
        return false;
    }

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Persona p : lista) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}