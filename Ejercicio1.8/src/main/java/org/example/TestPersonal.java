package personal;

import java.util.List;
import java.util.Scanner;

public class TestPersonal {

    static Scanner sc = new Scanner(System.in);

    static void menu(Personal personal) {
        int opcion;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("0. Salir de la aplicacion");
            System.out.println("1. Mostrar personas de un pais");
            System.out.println("2. Borrar persona por email");
            System.out.print("Opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    System.out.print("Pais: ");
                    String pais = sc.nextLine();
                    List<Persona> encontradas = personal.getPersonasPorPais(pais);
                    if (encontradas.isEmpty()) {
                        System.out.println("No hay personas de ese pais.");
                    } else {
                        for (Persona p : encontradas) {
                            System.out.println(p);
                        }
                        System.out.println("Total: " + encontradas.size());
                    }
                    break;
                case 2:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (personal.borrarPorEmail(email)) {
                        System.out.println("Persona borrada.");
                    } else {
                        System.out.println("No existe ninguna persona con ese email.");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
}