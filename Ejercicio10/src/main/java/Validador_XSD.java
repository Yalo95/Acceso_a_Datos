import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class Validador_XSD {
    //Aquí realizamos el unmrshaller para leer el texto xml y pasarlo a objeto, habiendolo validaddo con la estructura XSD previamente
    public Individuals unmarshaller (){

        Individuals individuals = null; //Para poder retornarlo fuera del try
        try {
            //El schemaFactory nos sirve para leer archivos de reglas y el XMLConstants le dice que tipo de reglas va a leer, archivos XSD en este caso.
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("C:\\Users\\Work\\Desktop\\DAM\\2DAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio10\\full_person.xsd"));
            //Aqui leemos el archivo xsd y lo guardamos como un objeto Schema.

            JAXBContext context = JAXBContext.newInstance(Individuals.class);//Se pone el .class para que pueda leer la clase del ElementoRaiz y analizar su estructura
            Unmarshaller unmarshaller = context.createUnmarshaller();//Creamos el unmarshall que lee de xml a objetos Java.
            unmarshaller.setSchema(schema);//Esta línea es la que valida la estructura del xml con la del xsd. Aqui se efectua la validación.
            individuals = (Individuals) unmarshaller.unmarshal(new File("C:\\Users\\Work\\Desktop\\DAM\\2DAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio10\\person.xml"));
            //Y aqui creamos el objeto Individuals con todos los datos del archivo xml dentro.

            System.out.println("El XML es válido según el XSD");

        } catch (JAXBException e) {
            System.out.println("El XML no es válido: " + e.getLinkedException()); //El xml no cumple con el xsd
        }catch (SAXException e) {
            System.out.println("No se pudo cargar el esquema XSD: " + e.getMessage());//No se pudo cargar el xsd o no existe.
        }
        return individuals;
    }
    //Aqui realizamos el marshalling, cogiendo un objeto Individuals(en este caso) y lo escribe como xml.
    public void marshaller (Individuals individuals){
        try{
            JAXBContext context = JAXBContext.newInstance(Individuals.class);
            Marshaller marshaller = context.createMarshaller(); //Creamos el marshal que escribira de Java a XML.
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Con esta línea el xml se escribe con cada etiqueta en su línea y bien indentada. Sino saldrí en una sola linea sin saltos ni sangrías.
            //Le damos la estructura leíbe para los humanos.
            File nuevo_fichero = new File("person_nuevo.xml");//Indicamos el nombre y la ruta del nuevo archivo creado.
            marshaller.marshal(individuals,nuevo_fichero);//Convierte el objeto en XML y lo escribe en el archivo. SI existe, lo sobreescribe.

            System.out.println("Se ha creado el archivo: " + nuevo_fichero);

        } catch (JAXBException e) {
            System.out.println("No se pudo crear el nuevo archivo");
        }
    }
    static void main(String[] args) {

        Validador_XSD validador = new Validador_XSD();
        Individuals individuos = validador.unmarshaller();

        if(individuos != null){ //Nos aseguramos que el objeto no está vacío y lo mostramos por consola
            System.out.println(individuos);
            validador.marshaller(individuos);
        }else{
            System.out.println("El método Unmarshalling falló.");//Mensaje de error si esta vacío el objeto o no se valida como debe ser.
        }
    }
}
