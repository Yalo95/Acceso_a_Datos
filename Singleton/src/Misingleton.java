import java.util.ArrayList;

public class Misingleton {

    static private ArrayList<Integer> lista;

    Misingleton(){}

    static ArrayList<Integer> obtenerLista(){
        if(lista == null){
            lista = new ArrayList<Integer>();
        }
        return lista;
    }
}
