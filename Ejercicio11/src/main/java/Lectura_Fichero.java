import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;


public class Lectura_Fichero {

    //Método para leer el fichero aleatorio.
    public String lectura_ficheroAleatorio (RandomAccessFile raf, int tamaño_campo) throws  IOException {

        byte [] bytes = new byte[tamaño_campo];//Creamos un array con la limitacion de cada campo.
        raf.readFully(bytes); //Rellenamos el array de Bytes

        String fichero_Aleatorio = new String(bytes, StandardCharsets.UTF_8);//Convertimos el Array a texto y le interpretamos en formato utf-8

        return fichero_Aleatorio.trim();//Para quitar los espacios y los 0 de relleno puestos anteriormente
    }
    //Metodo para leer cada uno de los registros y guardarlos en variables para poder mostrarlos posteriormente por pantalla
    //Le pasamos por parametro que debe leer y la posición donde debe leer.
    public void leer_Registros (RandomAccessFile raf, long posicion) throws IOException {

        raf.seek(posicion * Tamaño_Campos.TAM_REGISTRO); //Para posicionarnos en cada campo del registro directamente.

        //ESTUDIANTE
        int idEstudiante = raf.readInt();
        String nombre = lectura_ficheroAleatorio(raf,Tamaño_Campos.TAM_NOMBRE);
        String telefono = lectura_ficheroAleatorio(raf,Tamaño_Campos.TAM_TELEFONO);
        String email = lectura_ficheroAleatorio(raf,Tamaño_Campos.TAM_EMAIL);
        //DIRECCION
        String calle = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_CALLE);
        int numero = raf.readInt();
        int piso = raf.readInt();
        String letra = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_LETRA);
        int cp = raf.readInt();
        //MODULO 1
        String idModulo1 = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_ID_MODULO);
        String denominacionModulo1 = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_DENOMINACION_MODULO);
        int horasM1 = raf.readInt();
        //MODULO 2
        String idModulo2 = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_ID_MODULO);
        String denominacionModulo2 = lectura_ficheroAleatorio(raf, Tamaño_Campos.TAM_DENOMINACION_MODULO);
        int horasM2 = raf.readInt();

        System.out.println("{ Id: " + idEstudiante + "\n" +
                "Nombre: " + nombre + "\n" +
                "Email: " + email + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Calle: " + calle + "\n" +
                "Número: " + numero + "\n" +
                "Piso: " + piso + "\n" +
                "Letra: " + letra + "\n" +
                "CP: " + cp + "\n" +
                "Id 1º Módulo: " + idModulo1 + "\n" +
                "Denominación del 1º Módulo: " + denominacionModulo1 + "\n" +
                "Horas: " + horasM1 + "\n" +
                "Id 2º Módulo: " + idModulo2 + "\n" +
                "Denominación del 2º Módulo: " + denominacionModulo2 + "\n" +
                "Horas: " + horasM2 + " }" + "\n");
    }
    static void main(String[] args) {

        Lectura_Fichero lf= new Lectura_Fichero();

        try (RandomAccessFile raf = new RandomAccessFile("estudiantes.dat", "r")) {
            long num_registros = raf.length() / Tamaño_Campos.TAM_REGISTRO;
            //System.out.println(num_registros);

            System.out.println("POSICIONES PARES");//Al empezar en la posicion 0 los ids impares ocupan las posiciones pares y viceversa
            for (long i = 0; i < num_registros; i+=2) {
                lf.leer_Registros(raf,i);
            }
            System.out.println("POSICIONES IMPARES");
            for (long j = 1; j < num_registros; j+=2){
                lf.leer_Registros(raf,j);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el fichero aleatorio");
        }



    }
}
