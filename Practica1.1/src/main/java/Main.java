import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        LecturaFusion lectura = new LecturaFusion();

        try{//Lo envolvemos en el try para capturar la excepcion.
            Clientes clientesXML = lectura.leerXML("clientes.xml");
            lectura.leerJson("pedidos.json", clientesXML);
            System.out.println(clientesXML);
        } catch (JAXBException e) {
            System.err.println("No se ha podido leer el fichero XML " + e.getMessage());
        } catch (IOException e) {
            System.err.println("No se ha podido leer el fichero Json " + e.getMessage());
        }
    }
}
