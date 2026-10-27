import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public class Main {
    static void main(String[] args) {
        LecturaFusion lectura = new LecturaFusion();

        try{//Lo envolvemos en el try para capturar la excepcion.
            Clientes clientesXML = lectura.leerXML("clientes.xml");
            System.out.println(clientesXML);
            lectura.leerJson("pedidos.json");
        } catch (JAXBException e) {
            System.err.println("No se ha podido leer el fichero XML " + e.getMessage());
        } catch (IOException e) {
            System.err.println("No se ha podido leer el fichero Json " + e.getMessage());
        }
    }
}
