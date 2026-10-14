public class Television extends Electrodomesticos{

    double resolucion = 20;
    boolean smartTV = true;

    public Television(double precioBase, double peso, String color, String consumoEnergetico, double resolucion, boolean smartTV) {
        super(precioBase, peso, color, consumoEnergetico);
        this.resolucion = resolucion;
        this.smartTV = smartTV;
    }

    public double getResolucion() {
        return resolucion;
    }

    public boolean isSmartTV() {
        return smartTV;
    }

    int precioFinal(){

        int resultado;

        return resultado;
    }
}
