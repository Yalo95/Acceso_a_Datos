package org.example;
import java.util.List;
import java.util.Scanner;

public class TestPersonal {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Nombre del fichero (csv, xml o json): ");
        String fichero = sc.nextLine();

        List<Persona> lista;
        if (fichero.endsWith(".xml")) {//LLamamos a los metodos de lectura segun su formato.
            lista = Auxiliar.leerXML(fichero);
        } else if (fichero.endsWith(".json")) {
            lista = Auxiliar.leerJSON(fichero);
        } else {
            lista = Auxiliar.leerCSV(fichero);
        }

        Personal personal = new Personal(lista);
        System.out.println("Personas cargadas: " + lista.size());//Crea el objeto Personal pasándole la lista leída,
                                                                // y muestra cuántas personas hay (size() devuelve el tamaño de la lista).

        menu(personal);//Entra en el menú. El programa se queda aquí hasta que el usuario elige la opción 0.

        Auxiliar.escribirCSV(personal.getLista());//Cuando el usuario sale del menú, se guarda la lista
                                                // (con las personas que se hayan borrado) en un CSV nuevo, usando el método de Auxiliar.
    }

    public static void menu(Personal personal) {
        int opcion;
        do {
            System.out.println();
            System.out.println("0. Salir de la aplicacion");
            System.out.println("1. Mostrar personas de un pais");
            System.out.println("2. Borrar una persona por email");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Pais: ");
                    String pais = sc.nextLine();
                    List<Persona> encontradas = personal.getPersonasPorPais(pais);
                    if (encontradas.isEmpty()) {
                        System.out.println("No hay personas de ese pais");
                    } else {
                        for (Persona p : encontradas) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (personal.borrarPorEmail(email)) {
                        System.out.println("Persona borrada");
                    } else {
                        System.out.println("No existe ese email");
                    }
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
}