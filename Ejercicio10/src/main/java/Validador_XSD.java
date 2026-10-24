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

    public Individuals unmarshaller (){

        Individuals individuals = null;
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("C:\\Users\\Work\\Desktop\\DAM\\2DAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio10\\full_person.xsd"));

            JAXBContext context = JAXBContext.newInstance(Individuals.class);//Se pone el .class para que pueda leer la clase del ElementoRaiz
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            individuals = (Individuals) unmarshaller.unmarshal(new File("C:\\Users\\Work\\Desktop\\DAM\\2DAM\\Acceso a Datos\\Acceso_a_Datos\\Ejercicio10\\person.xml"));

            System.out.println("El XML es válido según el XSD");

        } catch (JAXBException e) {
            System.out.println("El XML no es válido: " + e.getLinkedException());
        }catch (SAXException e) {
            System.out.println("No se pudo cargar el esquema: " + e.getMessage());
        }
        return individuals;
    }

    public void marshaller (Individuals individuals){
        try{
            JAXBContext context = JAXBContext.newInstance(Individuals.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File nuevo_fichero = new File("person_nuevo.xml");
            marshaller.marshal(individuals,nuevo_fichero);
            System.out.println("Se ha creado el archivo: " + nuevo_fichero);
        } catch (JAXBException e) {
            System.out.println("No se puedo crear el nuevo archivo");;
        }
    }
    static void main(String[] args) {

        Validador_XSD validador = new Validador_XSD();
        Individuals individuos = validador.unmarshaller();

        if(individuos != null){
            System.out.println(individuos);
            validador.marshaller(individuos);
        }else{
            System.out.println("El método Unmarshalling falló.");
        }

    }
}
