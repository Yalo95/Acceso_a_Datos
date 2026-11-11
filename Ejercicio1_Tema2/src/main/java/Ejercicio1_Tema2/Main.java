package Ejercicio1_Tema2;

public class Main {
    static void main(String[] args) {

        Clientes clientes = new Clientes();
        MostrarClientes mc = new MostrarClientes();
        //CREACION DE TABLA Y DIFERENTES MODIFICACIONES

      /*  clientes.crearTabla();
        clientes.modificarApellido();
        clientes.borrarSamper();

      */

        //MOSTRAR CLIENTES ORDENADOS POR DNI
        mc.mostrarClientesDni();
        //MOSTRAR CLIENTES ORDENADOS POR DNI PERO DE FORMA INVERSA
        mc.dniOrdenInverso();
        //MOSTRAR DNI DE CLIENTES
        mc.mostrarDni();
    }
}
