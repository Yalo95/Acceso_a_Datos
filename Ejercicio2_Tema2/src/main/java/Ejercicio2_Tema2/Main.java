package Ejercicio2_Tema2;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    static void main() {
        Transaccion transaccion = new Transaccion();
        LlamadaFuncion llamadaFuncion = new LlamadaFuncion();
        Lotes lotes = new Lotes();


        //EJERCICIO 6: CON TRANSACCIÓN Y SIN TRANSACCIÓN
        transaccion.conTransaccion();
        System.out.println("Falla la tercera inserción porque es el mismo Dni que el primero y " +
                "ya se encuentra en la base de datos. Por lo tanto no se añade ningún dato nuevo y el rollback deshace las inserciones ejecutadas" +
                "quedando limpia la BD.");
        transaccion.sinTransacciones();
        System.out.println("Aquí se insertan los datos que ya se han ejecutado e insertado y no estaban repetidos." +
                "Una vez falla la tercera insercion, no se puede deshacer las dos primeras inserciones");

        //EJERCICIO 7: LLAMADA A LA FUNCION
       Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el dni de la persona que quiere mostrar: ");
        String dni = sc.nextLine();

        llamadaFuncion.llamada(dni);

        //EJERCICIO 8: INVESTIGACION

        /* EXPLICACIÓN

        * En funcion del tipo, updateXXX(1,null) y updateNull(1) pueden actuar de diversa forma.
        * Si es de tipo objeto, su segundo parámetro es un objeto, que sí puede ser null. En estos casos, updateXXX(1, null) y updateNull(1)
        * hacen lo mismo: dejan la columna a NULL en la base de datos.
        *
        * En cambio, si es de tipo primitivo Su segundo parámetro es un primitivo, que en Java no puede valer null.
        * Por eso updateInt(1, null) no compila, y para poner a NULL una columna numérica o booleana la única opción directa es updateNull(1).
        *
        * En definitiva, updateNull es la forma genérica de asignar NULL a cualquier columna, sea del tipo que sea.
        * updateXXX(…, null) solo sirve para los tipos que son objetos.
        *
        */
        //EJERCICIO 9: LOTES
        lotes.comprobacion();


    }
}

