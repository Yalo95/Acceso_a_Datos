public class Tamaño_Campos {

    //***Estudiante***
    public static final int TAM_IDMATRICULA = 4;//Colocamos el public para los dos programas puedan leerlos y el static para que se usen con el nombre de la clase sin crear objetos. Asi es más accesible.
    public static final int TAM_NOMBRE = 20; //Por convencion las constantes van en Mayusculas.
    public static final int TAM_TELEFONO = 20;
    public static final int TAM_EMAIL = 20;
    //***Direccion***
    public static final int TAM_CALLE = 20;
    public static final int TAM_NUMERO = 4;
    public static final int TAM_PISO = 4;
    public static final int TAM_LETRA = 5;
    public static final int TAM_CP = 4;
    //***Modulo***
    public static final int TAM_ID_MODULO = 5;
    public static final int TAM_DENOMINACION_MODULO = 20;
    public static final int TAM_HORAS_MODULO = 4;

    public static final int TAM_ESTUDIANTE = TAM_IDMATRICULA + TAM_NOMBRE + TAM_TELEFONO + TAM_EMAIL;
    public static final int TAM_DIRECCION = TAM_CALLE + TAM_NUMERO + TAM_PISO + TAM_LETRA + TAM_CP;
    public static final int TAM_MODULO = TAM_ID_MODULO + TAM_DENOMINACION_MODULO + TAM_HORAS_MODULO;

    public static final int TAM_REGISTRO = TAM_ESTUDIANTE + TAM_DIRECCION + TAM_MODULO * 2;
}
