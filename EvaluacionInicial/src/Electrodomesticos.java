public class Electrodomesticos {

    double precioBase = 100;
    String [] color = {"Blanco", "Negro", "Rojo","Azul", "Gris"};
    String [] consumoEnergetico = {"A","B","C","D","E","F"};
    double peso = 5;


    public Electrodomesticos(double precioBase, String[] color, String[] consumoEnergetico, double peso) {
        this.precioBase = precioBase;
        this.color = color;
        this.consumoEnergetico = consumoEnergetico;
        this.peso = peso;
    }

    public Electrodomesticos(double precioBase, double peso, String color, String consumoEnergetico, double precioBase1, double peso1) {
        this.precioBase = precioBase1;
        this.peso = peso1;
    }

    public Electrodomesticos(double precioBase, double peso, String color, String consumoEnergetico) {
    }


    public double getPrecioBase() {
        return precioBase;
    }

    public String[] getColor() {
        return color;
    }

    public String[] getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public double getPeso() {
        return peso;
    }

    char comprobarConsumoEnergetico(String [] consumoEnergetico){

        char letra = 0;

        for (char i = 0; i > consumoEnergetico.length; i++){
            letra = i;
        }
        if (letra == getConsumoEnergetico()){

        } else {
            getConsumoEnergetico();
        }

        return letra;
    }
    String comprobarColor(String [] color){

        String colorNuevo;
        for (String i = 0; i > color.length; i++){
            colorNuevo = color.length(i);
        }
        return " ";
    }
    double precioFinal(final double precioBase){

        double resultado = 0;


        return resultado;
    }

    class Lavadora {

        final double precioBase;
        final String color;
        final String consumoEnergetico;
        final double peso;
        final int carga;

        public Lavadora(int carga, double precioBase, String color, String consumoEnergetico, double peso) {
            this.precioBase = precioBase;
            this.color = color;
            this.consumoEnergetico = consumoEnergetico;
            this.peso = peso;
            this.carga = 5;
        }
    }
}
