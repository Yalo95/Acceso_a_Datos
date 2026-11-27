package Practica2_Tema2.vista;


import Practica2_Tema2.Gestion_BD;

public class Main {
    static void main() {
        Gestion_BD gestion_BD = new Gestion_BD();
        String ficheroSQL = "Practica2_Tema2/src/main/resources/datos.sql";
        gestion_BD.ejecutarScript(ficheroSQL);
    }
}
