package Practica1_2;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        LeerFicheros lf = new LeerFicheros();

        try {
            System.out.println(lf.leerCampos("campos.dat"));
            System.out.println(lf.calcularTamaño(lf.leerCampos("campos.dat")));
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer los campos: " + e.getMessage());
        }
    }
}
