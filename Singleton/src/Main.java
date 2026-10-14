import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        ArrayList l = Misingleton.obtenerLista();

        System.out.println("Contenido de la lista");

        l.forEach(numero -> System.out.println(numero) );

        l.add(20);
        l.add(30);

        ArrayList z = Misingleton.obtenerLista();
        System.out.println("Imprimo la nueva lista");

        z.forEach( numero -> System.out.println(numero) );

        System.out.println("Contenido de la lista original");

        l.forEach( numero -> System.out.println(numero) );
    }
}
