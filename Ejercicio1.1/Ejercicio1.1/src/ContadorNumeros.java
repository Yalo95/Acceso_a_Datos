/* Creamos la clase ContadorNumeros para crear el Array de numeros con 20 posiciones
y rellenarlas con el bucle. Se hace aleatoriamente usando la libreria Math.random, pasando esos decimales
a enteros. Se multiplica por 21(Maximo - minimo que se quiere obtener + 1) y se suma luego el minimo que es
20 para que el resultado este entre 20 y 40 */

public class ContadorNumeros {

    int[] rellenarNumeros (){

        int [] arrayNumeros = new int[20];
        for (int i = 0; i < arrayNumeros.length; i++){
            arrayNumeros[i] = (int) (Math.random() * 21) + 20;
        }
        return arrayNumeros;
    }
}
