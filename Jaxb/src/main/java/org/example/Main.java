package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static void main() {

        //Para Jaxb Escritura
        Persona p = new Persona("12345678","Julio",25);
        p.addTitulo("ESO");
        p.addTitulo("Bachillerato");

        try{
            JAXBContext contexto = JAXBContext.newInstance(p.getClass());
            Marshaller marshaller = contexto.createMarshaller(); //El Marshaller es el que transforma de objeto a fichero XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

            BufferedWriter bw = Files.newBufferedWriter(Paths.get("persona.xml"));

            marshaller.marshal(p,bw);

            bw.close();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
