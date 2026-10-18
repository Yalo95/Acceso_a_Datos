package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nombre del fichero (csv, xml o json): ");
            String fichero = sc.nextLine().trim();

            String ext = fichero.toLowerCase();
            List<Persona> lista;
            if (ext.endsWith(".xml")) {
                lista = Auxiliar.leerXML(fichero);
            } else if (ext.endsWith(".json")) {
                lista = Auxiliar.leerJSON(fichero);
            } else {
                lista = Auxiliar.leerCSV(fichero);
            }

            Personal personal = new Personal(lista);
            System.out.println("Personas cargadas: " + personal.getLista().size());

            TestPersonal.menu(personal);

            String salida;
            if (ext.endsWith(".xml")) {
                salida = Auxiliar.escribirXML(personal.getLista());
            } else if (ext.endsWith(".json")) {
                salida = Auxiliar.escribirJSON(personal.getLista());
            } else {
                salida = Auxiliar.escribirCSV(personal.getLista());
            }
            System.out.println("Datos guardados en " + salida);
        }
}
