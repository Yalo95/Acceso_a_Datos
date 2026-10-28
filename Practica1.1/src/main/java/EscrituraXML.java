import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;


public class EscrituraXML {
    public void escribirXML (Clientes clientes, String rutaSalida) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Clientes.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        File clientesXML = new File(rutaSalida);
        marshaller.marshal(clientes,clientesXML);
    }
}
