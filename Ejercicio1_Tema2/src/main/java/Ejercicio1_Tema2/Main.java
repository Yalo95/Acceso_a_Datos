package Ejercicio1_Tema2;

public class Main {
    static void main(String[] args) {

        Clientes clientes = new Clientes();
        MostrarClientes mc = new MostrarClientes();
        //clientes.crearTabla();
        clientes.modificarApellido();
        clientes.borrarSamper();

        //Mostrar clientes ordenados por DNI
        mc.mostrarClientesDni();

    }
}
