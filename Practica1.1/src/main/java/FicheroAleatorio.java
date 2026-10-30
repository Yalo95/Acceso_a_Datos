import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FicheroAleatorio {

    //CLIENTE
    public static final int TAM_DNI = 10;
    public static final int TAM_NOMBRE = 30;
    public static final int TAM_EMAIL = 40;
    //PEDIDO
    public static final int TAM_IDPEDIDO = 5;
    public static final int TAM_FECHA = 10;
    public static final int TAM_TOTAL= 8; //Los double ocupan 8 byte
    //ARTICULO
    public static final int TAM_PRODUCTOID = 5;
    public static final int TAM_CANTIDAD = 4; //Los int ocupan 4 byte
    public static final int TAM_PRECIOUNITARIO = 8;
    //TOTAL
    public static final int TAM_CLIENTE = TAM_DNI + TAM_NOMBRE + TAM_EMAIL;
    public static final int TAM_PEDIDO = TAM_IDPEDIDO + TAM_FECHA + TAM_TOTAL;
    public static final int TAM_ARTICULO = TAM_PRODUCTOID + TAM_CANTIDAD+ TAM_PRECIOUNITARIO;

    public static final int TAM_TOTAL_REGISTRO= TAM_CLIENTE + TAM_PEDIDO + TAM_ARTICULO;


    public void crearFicheroAleatorio (RandomAccessFile raf, String texto, int tamaño_campo) throws IOException {

        byte [] textoBytes = texto.getBytes(StandardCharsets.UTF_8);//Obtenemos los bytes del texto y los guardamos en formato UTF-8 en el array
        byte [] camposBytes = Arrays.copyOf(textoBytes,tamaño_campo);//Fusionamos el array de bytes con el tamaño limite que le otorgamos

        raf.write(camposBytes); //Rellenamos el array de Bytes
    }

    public void escribirFichero(Clientes clientes, String ruta) throws IOException {

        try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){

            raf.setLength(0);

            for (Cliente cliente: clientes.getClientes()){//Escribimos en el archivo ya creado la información que queremos. Usamos bucles anidados para ir recorriendo cada capa.
                for (Pedido pedido: cliente.getPedidos()){
                    for(Articulo articulo: pedido.getArticulos()){
                        crearFicheroAleatorio(raf,cliente.getDni(),TAM_DNI);
                        crearFicheroAleatorio(raf,cliente.getNombre(),TAM_NOMBRE);
                        crearFicheroAleatorio(raf,cliente.getEmail(),TAM_EMAIL);
                        crearFicheroAleatorio(raf,pedido.getId(),TAM_IDPEDIDO);
                        crearFicheroAleatorio(raf,pedido.getFecha(), TAM_FECHA);
                        raf.writeDouble(pedido.getTotal());
                        crearFicheroAleatorio(raf,articulo.getProductoId(),TAM_PRODUCTOID);
                        raf.writeInt(articulo.getCantidad());
                        raf.writeDouble(articulo.getPrecioUnitario());
                    }
                }
            }
        }
    }
    public String leerFicheroAleatorio (RandomAccessFile raf, int tamaño_campo) throws  IOException {

        byte [] bytes = new byte[tamaño_campo];//Creamos un array con la limitacion de cada campo.
        raf.readFully(bytes); //Rellenamos el array de Bytes

        String fichero_Aleatorio = new String(bytes, StandardCharsets.UTF_8);//Convertimos el Array a texto y le interpretamos en formato utf-8

        return fichero_Aleatorio.trim();//Para quitar los espacios y los 0 de relleno puestos anteriormente
    }
    public void leer_Registros (String ruta, long posicion) throws IOException {

        try(RandomAccessFile raf = new RandomAccessFile(ruta,"r")){

            raf.seek(posicion * TAM_TOTAL_REGISTRO);

            String dni = leerFicheroAleatorio(raf,TAM_DNI);
            String nombre =leerFicheroAleatorio(raf,TAM_NOMBRE);
            String email =leerFicheroAleatorio(raf,TAM_EMAIL);
            String idpedido =leerFicheroAleatorio(raf,TAM_IDPEDIDO);
            String fecha =leerFicheroAleatorio(raf,TAM_FECHA);
            double total =raf.readDouble();
            String productoId =leerFicheroAleatorio(raf,TAM_PRODUCTOID);
            int cantidad =raf.readInt();
            double precioUnitario =raf.readDouble();

            System.out.println("{ Dni: " + dni + "\n" +
                    "Nombre: " + nombre + "\n" +
                    "Email: " + email + "\n" +
                    "Id Pedido: " + idpedido + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Total: " + total + "\n" +
                    "Producto Id: " + productoId + "\n" +
                    "Cantidad: " + cantidad + "\n" +
                    "Precio Unitario: " + precioUnitario + "\n" + " }" + "\n");
        }
    }
    public long contarRegistros (String ruta) throws IOException {//Metodo para contar los registros del archivo, necesario para pasarle los rangos que se quieran a traves del scanner.
        try(RandomAccessFile raf = new RandomAccessFile(ruta, "r")){
            long numeroRegistros = raf.length() / TAM_TOTAL_REGISTRO;
            return numeroRegistros;
        }
    }
}
