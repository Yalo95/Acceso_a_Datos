package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Persona persona1 = new Persona(null, 25);

        Gson gson = new GsonBuilder().serializeNulls().create(); // Configura Gson para serializar valores nulos

        String json = gson.toJson(persona1);

        try {
            BufferedWriter bf = Files.newBufferedWriter(Paths.get("Mifichero.json")); // Escribe el JSON en un archivo llamado "Mifichero.json")

            bf.write(json);
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}

