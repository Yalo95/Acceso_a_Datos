import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        TreeMap<Integer, Estudiante> estudianteTreeMap = new TreeMap<>();//Se usa TreeMap porque es la manera más eficiente para no repetir claves y tener ordenados los idMatricula.
        for (Estudiante estudiantesXml: estudiantadoXML.getEstudiante()){//Recorremos la lista del estudianteXML.
            estudianteTreeMap.put(estudiantesXml.getIdMatricula(),estudiantesXml);//Lo guardamos cada uno en el TreeMap, con idmatricula como key y el resto del estudiante como valor.
        }
        for (Estudiante estudiantesJson: estudiantadoJson.getEstudiante()){
            estudianteTreeMap.putIfAbsent(estudiantesJson.getIdMatricula(),estudiantesJson);//Con el putIfAbsent, los id de las matriculas que ya esten se ignoran y no se guardan.
        }
        return estudianteTreeMap;
    }
    //Solo me queda aqui pasar los datos creados a un fichero de acceso aleatorio. Luego hacer la clase lectura fichero.
    static void main(String[] args) {
        Fusionar_Ficheros fusionarFicheros = new Fusionar_Ficheros();
        Estudiantado xml = fusionarFicheros.leerFicherosXML();
        Estudiantado json = fusionarFicheros.leerFicherosJson();


        //System.out.println(fusionarFicheros.leerFicherosXML());
        //System.out.println(fusionarFicheros.leerFicherosJson());

        if(fusionarFicheros.fusionar_Ficheros(xml,json) != null){ //Nos aseguramos que el objeto no está vacío y lo mostramos por consola
           TreeMap treeMap = fusionarFicheros.fusionar_Ficheros (xml,json);
            System.out.println(treeMap.size());
            System.out.println(treeMap.keySet());
        }else{
            System.out.println("El método Unmarshalling falló.");//Mensaje de error si esta vacío el objeto o no se valida como debe ser.
        }

    }
}
