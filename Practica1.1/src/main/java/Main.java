import com.google.gson.JsonParseException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    static List<Integer> pedirEntrada (long numeroRegistros){//Para validar el formato de las posiciones en el teclado
        Scanner scanner = ScannerGlobal.getInstance().getScanner();

        while (true){
            System.out.println("Introduce una posición, un rango (2-5) o varias posiciones (2,4,6); entre 1 y " + numeroRegistros);
            String entrada = scanner.nextLine().trim();
            List<Integer> listaPosiciones = new ArrayList<>();

            try {//Usamos matches, que comprueba que aquello que le pasemos siga un patron.
                if (entrada.matches("\\d+")) { //Para cuando es un digito solo con la d, pero en este caso como pueden ser mas de uno se le añade + para indicarlo
                    listaPosiciones.add(Integer.parseInt(entrada));
                } else if (entrada.matches("\\d+-\\d+")) {//Este sería un o mas digitos, con - entre medias de ambos.
                    String[] entradaArray = entrada.split("-");
                    Integer inicio = Integer.parseInt(entradaArray[0]);
                    Integer fin = Integer.parseInt(entradaArray[1]);

                    if (inicio > fin){
                        System.out.println("El inicio no puede ser mayor que el final");
                        continue;
                    }
                    if (inicio < 1 || fin > numeroRegistros) {
                        System.out.println("El rango debe estar entre 1 y " + numeroRegistros + ".");
                        continue;
                    }
                    for (int i = inicio; i <= fin; i++) {
                            listaPosiciones.add(i);
                        }
                    } else if (entrada.matches("\\d+(,\\d+)+")) {//Lo que hay entre parentesis repetido una o mas veces.
                        String[] eArray = entrada.split(",");
                        for (String entradas : eArray) {
                            listaPosiciones.add(Integer.parseInt(entradas));
                        }
                    } else {
                        System.out.println("El formato introducido no es válido.");
                        continue;
                    }
            }catch (NumberFormatException e){// Numeros que no caben en un int
                System.out.println("El número introducido es demasiado grande.");
                continue;
            }

            boolean todasValidas = true; //Para validar correctamente la entrada
            for (int posicion: listaPosiciones){
                if (posicion < 1 || posicion > numeroRegistros) {
                    System.out.println("La posición " + posicion + " no existe. Debe estar entre 1 y " + numeroRegistros + ".");
                    todasValidas = false;
                    break;
                }
            }
            if (todasValidas){
                return listaPosiciones;
            }
        }
    }
    static void main(String[] args) {
        LecturaFusion lectura = new LecturaFusion();
        EscrituraXML escrituraXML = new EscrituraXML();
        FicheroAleatorio fa = new FicheroAleatorio();
        String rutaDat = "clientes_pedidos.dat";

        try{//Lo envolvemos en el try para capturar la excepcion.
            Clientes clientesXML = lectura.leerXML("clientes.xml");

            lectura.leerJson("pedidos.json", clientesXML);

            escrituraXML.escribirXML(clientesXML,"clientes_fusionados.xml");

            fa.escribirFichero(clientesXML,rutaDat);
            long numeroRegistros = fa.contarRegistros(rutaDat);

            List<Integer> posiciones = pedirEntrada(numeroRegistros);
            for(int posicion: posiciones){
                fa.leer_Registros(rutaDat,posicion -1); //-1 porque el registro empieza en la posicion 0 pero el usuario marca el principio en 1 por pantalla.
            }

        } catch (JAXBException e) {
            System.err.println("Error de lectura o escritura del fichero " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de lectura o escritura del fichero " + e.getMessage());
        }catch(JsonParseException | IllegalStateException e){
            System.err.println("El fichero de pedidos no tiene el formato esperado " + e.getMessage());
        }catch(NoSuchElementException e){
            System.err.println("Se ha cerrado la entrada del teclado ");
        }
    }
}
