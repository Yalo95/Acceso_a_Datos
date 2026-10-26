import com.google.gson.Gson;
import com.sun.source.tree.Tree;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.Unmarshaller;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeMap;

public class Fusionar_Ficheros {

    public Estudiantado leerFicherosXML (){

        Estudiantado estudiantadoXML = null; //Para poder retornarlo fuera del try
        try {

            JAXBContext context = JAXBContext.newInstance(Estudiantado.class);//Se pone el .class para que pueda leer la clase del ElementoRaiz y analizar su estructura
            Unmarshaller unmarshaller = context.createUnmarshaller();//Creamos el unmarshall que lee de xml a objetos Java.
            estudiantadoXML = (Estudiantado) unmarshaller.unmarshal(new File("estudiantes.xml"));
            //Y aqui creamos el objeto Estudiantado con todos los datos del archivo xml dentro.

            System.out.println("El XML se ha podido leer de forma correcta");

        } catch (JAXBException e) {
            System.out.println("El XML no se ha podido crear: " + e.getLinkedException());
        }
        return estudiantadoXML;
    }
    public Estudiantado leerFicherosJson (){
        Estudiantado estudiantadoJson = null;
        try{
            Gson gson = new Gson(); //Creamos un objero Json

            Reader r = Files.newBufferedReader(Paths.get("estudiantes.json")); //Leemos el fichero json

            estudiantadoJson = gson.fromJson(r,Estudiantado.class);//Guardamos el fichero Json como un objeto Estudiante.

            System.out.println("Le fichero Json se ha leído correctamente");
            r.close(); //Cerramps el lector

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return estudiantadoJson;
    }
    public TreeMap fusionar_Ficheros (Estudiantado estudiantadoXML, Estudiantado estudiantadoJson){
        //Añadimos en un TreeMap
        TreeMap<Integer, Estudiante> estudianteTreeMap = new TreeMap<>();//Se usa TreeMap porque es la manera más eficiente para no repetir claves y sobre todo tener ordenados los idMatricula(Hashmap y listas no los ordenan).

        for (Estudiante estudiantesXml: estudiantadoXML.getEstudiante()){//Recorremos la lista del estudianteXML.
            estudianteTreeMap.put(estudiantesXml.getIdMatricula(),estudiantesXml);//Lo guardamos cada uno en el TreeMap, con idmatricula como key y el resto del estudiante como valor.
        }
        for (Estudiante estudiantesJson: estudiantadoJson.getEstudiante()){
            if (estudianteTreeMap.putIfAbsent(estudiantesJson.getIdMatricula(),estudiantesJson) != null){ //Con el putIfAbsent, los id de las matriculas que ya esten se ignoran y no se guardan.
                System.out.println("Estudiante repetido, no se ha añadido el estudiante: " + estudiantesJson.getIdMatricula());
            }//Y msotramos los estudiantes que se repetían al hacer la fusion de ambos archivos

        }
        return estudianteTreeMap;
    }
    //Aqui le pasamos el fichero donde escribir, el texto y el numero de bytes que debe ocupar
    //Con este metodo escribimos una cadena en el fichero, ocupando exactamente el tamaño de bytes. Si el texto los sobrepasa, ser corta justo por el tamaño limite.
    public void escribir_Cadenas (RandomAccessFile raf, String cadena, int tamaño) throws IOException { // Con el throws IOException, si falla la escritura, el error lo gestiona el método que lo llama.
        if(cadena == null){ //Si la cadena es null, no hay dato y la cambia por texto vacío para que no de fallo porque no se puede llamar a un método sobre null
            cadena = "";
        }
        byte[] bytes = cadena.getBytes(StandardCharsets.UTF_8); // Convierte el texto en bytes, para guardarlo en el fichero. Con utf-8 las letras normales ocupan 1 byte y las que tienen tilde o ñ 2 bytes
        byte[] tamaño_ajustado = Arrays.copyOf(bytes, tamaño); //Un array nuevo con el tamaño de bytes exacto que le pasamos y copia en el los bytes del texto(cadena)
        raf.write(tamaño_ajustado); //Escribe el array de bytes en el fichero, en la posicion actual del puntero en ese momento.
    }
    //Para crear y traspasar los datos del mapa al fichero aleatorio, estudiantes.dat
    public void fichero_Aleatorio (TreeMap<Integer,Estudiante> estudianteTreeMap){

        try (RandomAccessFile raf = new RandomAccessFile("estudiantes.dat","rw")) {

            raf.setLength(0);//Nos aseguramos de vaciar el archivo antes de traspasar los datos

            for(Estudiante estudiantes: estudianteTreeMap.values()){ //Un for-each que recorre los valores del TreeMap creado anteriormente
                //ESTUDIANTE
                raf.writeInt(estudiantes.getIdMatricula()); //Escribimos en el archivo los id de la matricula. Se escribe asi sin limite porque un entero siempre equivale a 4 bytes. No hay que limitar tamaño, ya lo hace java con los tipos alfanumericos
                escribir_Cadenas(raf, estudiantes.getNombre(), Tamaño_Campos.TAM_NOMBRE); //Aqui le tenemos que indicar para escribirlo mas facilmente el metodo, y dentro de el los parametros de dond ese escribre, que queremos escribir y el tamaño limitante concreto, porque es tipo string este dato.
                escribir_Cadenas(raf, estudiantes.getTelefono(), Tamaño_Campos.TAM_TELEFONO);
                escribir_Cadenas(raf, estudiantes.getEmail(), Tamaño_Campos.TAM_EMAIL);
                //DIRECCION
                escribir_Cadenas(raf, estudiantes.getDireccion().getCalle(), Tamaño_Campos.TAM_CALLE);
                raf.writeInt(estudiantes.getDireccion().getNumero());
                raf.writeInt(estudiantes.getDireccion().getPiso());
                escribir_Cadenas(raf, estudiantes.getDireccion().getLetra(),Tamaño_Campos.TAM_LETRA);
                raf.writeInt(estudiantes.getDireccion().getCp());
                //MODULO 1
                escribir_Cadenas(raf, estudiantes.getMatricula().get(0).getId(), Tamaño_Campos.TAM_ID_MODULO);
                escribir_Cadenas(raf, estudiantes.getMatricula().get(0).getDenominacion(), Tamaño_Campos.TAM_DENOMINACION_MODULO);
                raf.writeInt(estudiantes.getMatricula().get(0).getHoras());
                //MODULO 2
                escribir_Cadenas(raf, estudiantes.getMatricula().get(1).getId(), Tamaño_Campos.TAM_ID_MODULO);
                escribir_Cadenas(raf, estudiantes.getMatricula().get(1).getDenominacion(), Tamaño_Campos.TAM_DENOMINACION_MODULO);
                raf.writeInt(estudiantes.getMatricula().get(1).getHoras());
            }
            //System.out.println(raf.length()); Para ver cuantos bytes ocupa estudiantes.dat. Si esa cantidad es igual al Nº Registros(26) * Tamaño de cada Fichero(159), es correcto el proceso.

        } catch (IOException e) {
            System.out.println("No se ha podido traspasar los datos al fichero aleatorio");
        }
    }
    static void main(String[] args) {
        Fusionar_Ficheros ff = new Fusionar_Ficheros();
        Estudiantado xml = ff.leerFicherosXML();
        Estudiantado json = ff.leerFicherosJson();
        TreeMap<Integer,Estudiante> treeMap = new TreeMap<>();

        if(xml != null && json != null){ //Nos aseguramos que el objeto xml y json no están vacíos y lo mostramos por consola
            System.out.println("Hay " + xml.getEstudiante().size() + " elementos en el archivo xml");
            System.out.println("Hay " + json.getEstudiante().size()+ " elementos en el archivo json" + "\n");

            treeMap = ff.fusionar_Ficheros (xml,json);
            System.out.println("\n" + "Se han añadido " + treeMap.size() + " estudiantes en total");//Muestra el numero de estudiantes que hay en TreeMap creado
            System.out.println(treeMap.keySet());//Muestra todas las claves(idMatriculas en este caso) de TreeMap, ordenados de menor a mayor.
            //ff.fichero_Aleatorio (treeMap); Para ejecutar el metodo y mostrar el raf.length
        }else{
            System.out.println("Los ficheros XML y Json están vacíos o no son válidos.");//Mensaje de error si esta vacío el objeto o no se valida como debe ser.
        }
    }
}
/* OTRA FORMA DE HACER LA FUSIÓN, COMPROBANDO LOS REPETIDOS A TRAVES DE LOS IDMATRICULA, MOSTRANDOLOS POR PANTALLA Y AÑADIENDO LOS NO REPETIDOS AL TREEMAP.
for (Estudiante estudiantesJson: estudiantadoJson.getEstudiante()){
    if (!estudianteTreeMap.containsKey(estudiantesJson.getIdMatricula())){ Pregunta si una clave ya esta en el mapa, true si existe y false sino.
        estudianteTreeMap.put(estudiantesJson.getIdMatricula(),estudiantesJson);
    }else{
        System.out.println("Estudiante repetido, no se ha añadido el estudiante: " + estudiantesJson.getIdMatricula());
    }
} return estudianteTreeMap;*/