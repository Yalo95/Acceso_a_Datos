package Practica1_2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Modificacion {
    //Para saber donde esta el salto del campo especifico que queremos y poder realizar el cambio
    public int calcularDesplazamiento(LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos, String nombreCampo) throws IOException {

        int desplazamiento = 0; //Acumulador de los los saltos de campos que llevamos
        for (AbstractMap.SimpleEntry<String, Integer> campo: campos) {
            if(campo.getKey().equals(nombreCampo)) {// Si el campo es el que buscamos, devolvemos el desplazamiento calculado hasta ese momento
                return desplazamiento;
            } else if (campo.getKey().equals("genero")) {//Si el campo es "genero", lo multiplicamos por 2 ya que hay 2 generos y se suman 2 veces
                desplazamiento += campo.getValue() * 2;
            } else{
                desplazamiento += campo.getValue(); //Cualquier otro campo lo sumamos una sola vez
            }
        }
        throw new IOException("El campo solicitado " + nombreCampo + " no se puede localizar en el archivo");//Salta esta excepción si no se ha encontrado el campo deseado
    }
    public void actualizarSede(String ruta, LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos, Biblioteca juvenil, int tamañoRegistro) throws IOException {

        HashSet<Integer> isbnJuveniles = new HashSet<>(); //Para guardar los valores del isbn de cada libro juvenil. El Set calcula directamente dónde estaría el elemento
        for(Libro lj: juvenil.getLibros()){
            isbnJuveniles.add(lj.getIsbn());
        }

        int desplazamientoIsbn = calcularDesplazamiento(campos,"isbn");//Calculamos el desplazamiento hasta llegar al isbn
        int desplazamientoSede = calcularDesplazamiento(campos,"sede");//Calculamos el desplazamiento hasta llegar al campo sede

        int tamañoSede = 0;
        for ( AbstractMap.SimpleEntry<String, Integer> campo: campos){
            if (campo.getKey().equals("sede")) {
                tamañoSede = campo.getValue();//Calculamos y guardamos el tamaño del campo sede, para escribir exactamente ese numero de bytes
            }
        }
        try(RandomAccessFile raf = new RandomAccessFile(ruta, "rw")){
            long numeroRegistros = raf.length() / tamañoRegistro; //Calculamos el numero de libros que hay.

            for (int i = 0; i < numeroRegistros; i++) {
                raf.seek(i * tamañoRegistro + desplazamientoIsbn); //Para posicionarse al principio del isbn del registro i
                int isbn = raf.readInt();//Leemos el isbn del registro i para saber que libro es.

                String nuevaSede = "JUVENIL";//Para escribir el cambio de sede en el archivo
                if (isbnJuveniles.contains(isbn)) {//Solo si el libro pertenece a la sede juvenil
                    raf.seek(i * tamañoRegistro + desplazamientoSede);//Para posicionarse en el campo sede del registro i
                    byte [] jBytes = nuevaSede.getBytes(StandardCharsets.ISO_8859_1);//Convierte "JUVENIL" a bytes en ese formato
                    byte [] juvenilBytes = Arrays.copyOf(jBytes,tamañoSede);//Ajustamos a los bytes limites del campo sede y rellenamos con 0 lo sobrante.
                    raf.write(juvenilBytes);//Se sobreescribe la nueva sede sin tocar el restro del registro.
                }
            }
        }
    }
}
