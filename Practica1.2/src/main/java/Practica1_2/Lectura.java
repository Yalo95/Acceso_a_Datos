package Practica1_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.LinkedHashSet;

public class Lectura {
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
    private String leerFicheroDat (RandomAccessFile raf, int tamaño_campo) throws IOException {

        byte [] bytes = new byte[tamaño_campo];//Creamos un array con la limitacion de cada campo.
        raf.readFully(bytes); //Rellenamos el array de Bytes

        String fichero_Aleatorio = new String(bytes, StandardCharsets.ISO_8859_1).trim();//Convertimos el Array a texto y lo interpretamos en formato ISO-8859-1(Las tildes ocupan 1 byte en este formato, mientras que en UTF-8 ocupan 2 bytes).
        // .trim() Para quitar los espacios y los 0 de relleno puestos anteriormente
        return fichero_Aleatorio;
    }

    public Biblioteca leerRegistros (String ruta,LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos, int tamañoTotal) throws IOException {

        try(RandomAccessFile raf = new RandomAccessFile(ruta,"r")){
            Biblioteca biblioteca = new Biblioteca();

            int numeroLibros = (int) (raf.length() / tamañoTotal); //Calculamos el numero de libros que hay en el fichero aleatorio dividiendo el tamaño del fichero entre el tamaño de cada registro.

            for (int i = 0; i < numeroLibros; i++) {
                raf.seek(i * tamañoTotal);//.seek coloca el puntero en la posicion que le indiquemos,
                // en este caso, la posicion del registro que queremos leer multiplicado por el tamaño de cada registro.

                Libro libro = new Libro();
                for (AbstractMap.SimpleEntry<String, Integer> campo : campos) {
                    switch (campo.getKey()) {
                        case "isbn":
                            libro.setIsbn(raf.readInt()); //Leemos el isbn y lo guardamos en el objeto libro
                            break;
                        case "autor":
                            libro.setAutor(leerFicheroDat(raf, campo.getValue()));
                            break;
                        case "titulo":
                            libro.setTitulo(leerFicheroDat(raf, campo.getValue()));
                            break;
                        case "genero":
                            libro.añadirGenero(leerFicheroDat(raf, campo.getValue()));
                            libro.añadirGenero(leerFicheroDat(raf, campo.getValue()));
                            break;
                        case "precio":
                            libro.setPrecio(raf.readDouble());
                            break;
                        case "fechaPublicacion":
                            libro.setFechaPublicacion(raf.readInt());
                            break;
                        case "sede":
                            libro.setSede(leerFicheroDat(raf, campo.getValue()));
                            break;
                    }
                }
                biblioteca.añadirLibro(libro);
            }
            return biblioteca;
        }
    }
}