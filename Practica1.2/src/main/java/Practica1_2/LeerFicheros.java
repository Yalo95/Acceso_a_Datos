package Practica1_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.AbstractMap;
import java.util.LinkedHashSet;

public class LeerFicheros {
    //Leemos los campos primeramente para saber la estructura de los datos que vamos a leer
    public LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> leerCampos(String ruta) throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {

            LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos = (LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>>) ois.readObject();
            return campos;
        }
    }
    //Calculamos el tamaño de los campos para saber cuanto espacio ocupará cada registro en el fichero aleatorio
    public int calcularTamaño(LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos) {
        int tamañoTotal = 0;
        for (AbstractMap.SimpleEntry<String, Integer> campo: campos) {
            if(campo.getKey().equals("genero")) {// Si el campo es genero lo sumamos doble
                tamañoTotal += campo.getValue() * 2;
            } else {
                tamañoTotal += campo.getValue();
            }
        }
        return tamañoTotal;
    }
}