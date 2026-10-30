import com.google.gson.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LecturaFusion {

    public Clientes leerXML(String ruta) throws JAXBException { //Propagamos la excepcion al main o a quien llame al metodo.
        //Leemos clientes.xml haciendo uso de unmarshaller y lo pasamos a objeto
        JAXBContext context = JAXBContext.newInstance(Clientes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Clientes clientesXML = (Clientes) unmarshaller.unmarshal(new File(ruta)); //Creamos el objeto Java de Clientes

        return clientesXML;
    }

    public void leerJson(String ruta, Clientes clientes) throws IOException { //Propagamos la excepcion al main o a quien llame al metodo.
        //Leemos pedidos.json
        Path rutaPath = Paths.get(ruta);
        Map<String, Cliente > clientesDNI = new HashMap<>();//Porque al buscar por DNI no necesitamos un TreeMap para buscar de manera ordenada.
        for (Cliente cliente: clientes.getClientes()){
            clientesDNI.put(cliente.getDni(), cliente);
        }
        try (
                Reader r = Files.newBufferedReader(rutaPath, StandardCharsets.UTF_8)) {//Leemos el fichero json

            JsonElement pJson_Raiz = JsonParser.parseReader(r); //Localizamos el elemento raiz del fichero jason.
            JsonObject pJson_Object = pJson_Raiz.getAsJsonObject();//Convertimos el elemento raiz a Objeto
            JsonObject pJson_Object2 = pJson_Object.get("pedidos").getAsJsonObject();//Cogemos y guardamos el primer nivel de ese objeto, pedidos en el json.

            JsonArray pJson = pJson_Object2.get("pedido").getAsJsonArray();//Cogemos y guardamos en forma de Array el segundo nivel del objeto, pedido(con sus hijos)

            for (JsonElement pJ : pJson) {//Recorremos el array pJson como elemento
                JsonObject pedidosJson_Object = pJ.getAsJsonObject();//Pasamos ese elemento con los datos del array a Objeto.
                Pedido pedidosJson = new Pedido();

                pedidosJson.setId(pedidosJson_Object.get("id").getAsString());//Incorporamos en el objeto de pedidos la informacion del objeto, guardandolo en función del tipo de dato que tenga.
                pedidosJson.setFecha(pedidosJson_Object.get("fecha").getAsString());
                pedidosJson.setTotal(pedidosJson_Object.get("total").getAsDouble());
                pedidosJson.setArticulos(leerArticulos(pedidosJson_Object.get("articulos")));

                String dni = pedidosJson_Object.get("dni").getAsString();//Cogemos el dni del Objeto de los pedidos json, que si tiene el dni, y lo guardamos como String.
                Cliente cliente = clientesDNI.get(dni);
                if (cliente != null) {
                    cliente.añadirPedido(pedidosJson);//Para enlazar el pedido con el dni del cliente
                }else {
                    System.err.println("El pedido con id: " + pedidosJson.getId() + " no corresponde a ningún cliente");
                }
            }
        }
    }
    private List<Articulo> leerArticulos(JsonElement jsonArticulos) {

        List<Articulo> articulos_Json = new ArrayList<>(); //Creamos una lista vacia de articulos para poder retornarla despues con los datos

        if(jsonArticulos.isJsonArray()){//Comprobamos si es articulo es Array
            JsonArray jsonArray = jsonArticulos.getAsJsonArray();//Convertimos en Array para poder recorrer el archivo
            for (JsonElement articulo: jsonArray){//Recorremos el archivo
                JsonObject aJson_Object = articulo.getAsJsonObject();//Lo pasamos a objeto y bajamos al primer nivel donde está artículos
                JsonObject aJson_Object2 = aJson_Object.get("articulo").getAsJsonObject();//Nos situamos en el nivel dos del objeto, donde si está articulo y la informacion
                Articulo articulosJson = new Articulo();//Creamos un Objeto articulo que se va rellenando con cada iteracion del bucle

                articulosJson.setProductoId(aJson_Object2.get("productoId").getAsString());
                articulosJson.setDescripcion(aJson_Object2.get("descripcion").getAsString());//Guardamos en ese objeto Articulo la información leible
                articulosJson.setCantidad(aJson_Object2.get("cantidad").getAsInt());
                articulosJson.setPrecioUnitario(aJson_Object2.get("precioUnitario").getAsDouble());

                articulos_Json.add(articulosJson);//Añadimos a la lista creada el resultado del objeto creado y rellenado.
            }
        } else if (jsonArticulos.isJsonObject()) {//Comprobamos si el articulo es Objeto
            JsonObject ajsonObject = jsonArticulos.getAsJsonObject();//Importante para decirle a Java que lo que tratamos es un Objeto
            JsonArray aJson_Array = ajsonObject.get("articulo").getAsJsonArray();//Lo convertimos en un array pero ya bajando al segundo nivel, donde queremos "articulo"
            for (JsonElement articulo: aJson_Array){
                JsonObject aJson_Object = articulo.getAsJsonObject();//Lo volvemos a pasar ese array como objeto
                Articulo articulosJson = new Articulo();

                articulosJson.setProductoId(aJson_Object.get("productoId").getAsString());
                articulosJson.setDescripcion(aJson_Object.get("descripcion").getAsString());
                articulosJson.setCantidad(aJson_Object.get("cantidad").getAsInt());
                articulosJson.setPrecioUnitario(aJson_Object.get("precioUnitario").getAsDouble());

                articulos_Json.add(articulosJson);
            }
        }
        return articulos_Json;
    }
}