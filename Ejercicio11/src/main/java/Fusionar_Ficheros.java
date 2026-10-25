import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


import java.io.File;

public class Fusionar_Ficheros {

    public Estudiantado leerFicherosXML (){

        Estudiantado estudiantado = null; //Para poder retornarlo fuera del try
        try {

            JAXBContext context = JAXBContext.newInstance(Estudiantado.class);//Se pone el .class para que pueda leer la clase del ElementoRaiz y analizar su estructura
            Unmarshaller unmarshaller = context.createUnmarshaller();//Creamos el unmarshall que lee de xml a objetos Java.
            estudiantado = (Estudiantado) unmarshaller.unmarshal(new File("C:\\Users\\Work\\Desktop\\DAM\\2DAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio11\\estudiantes.xml"));
            //Y aqui creamos el objeto Estudiantado con todos los datos del archivo xml dentro.

            System.out.println("El XML se ha podido leer de forma correcta");

        } catch (JAXBException e) {
            System.out.println("El XML no se ha podido crear: " + e.getLinkedException());
        }
        return estudiantado;
    }
    public void leerFicherosJson (){

    }
    public void fusionar_Ficheros (){

    }
    static void main(String[] args) {
        Fusionar_Ficheros fusionarFicheros = new Fusionar_Ficheros();

        System.out.println(fusionarFicheros.leerFicherosXML());

    }
}
