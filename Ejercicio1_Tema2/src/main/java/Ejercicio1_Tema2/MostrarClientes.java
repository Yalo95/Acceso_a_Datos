package Ejercicio1_Tema2;

import java.sql.*;

public class MostrarClientes extends Clientes{

    String ordenarByDni = "SELECT * FROM CLIENTES ORDER BY DNI";

    public void mostrarClientesDni() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             Statement stmt2 = connection.createStatement()) {
            //Mostrar los clientes ordenados por DNI
            ResultSet resultSet = stmt.executeQuery(ordenarByDni);
            System.out.println("Clientes ordenados por DNI: ");
            while(resultSet.next()){
                String dni = resultSet.getString("DNI");
                String apellidos = resultSet.getString("APELLIDOS");
                String cp = resultSet.getString("CP");
                System.out.println(dni + "\t" + apellidos + "\t" + cp + "\t");
            }
            //Comprobamos si se puede usar .getInt con cp
            ResultSet resultSet2 = stmt2.executeQuery(ordenarByDni);
            System.out.println("\n" + "Clientes ordenados por DNI y CP con .getInt: ");
            while(resultSet2.next()){
                String dni = resultSet2.getString("DNI");
                String apellidos = resultSet2.getString("APELLIDOS");
                int cp = resultSet2.getInt(3);
                System.out.println(dni + "\t" + apellidos + "\t" + cp);
            }
            System.out.println("\n" + "Conclusión: " + "Funcionar funciona con .getInt, pero no es lo más apropiado. " +
                    "Cuando el valor es null devuelve un 0, ya que no puede devolver null." + "\n" +
                    "El problema principal es que se pierde información y si el cp se lee como un número entero, " +
                    "se pierden los 0 que haya a la izquierda.");
        } catch (SQLException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }//DIAPOSITIVA 44
    public void dniOrdenInverso(){
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement()){

            int rsti = ResultSet.TYPE_SCROLL_INSENSITIVE;//ResultSet Bidireccional
            ResultSet resultSet = stmt.executeQuery(ordenarByDni);
            System.out.println("Clientes ordenados por DNI: ");
            while(resultSet.next()){
                String dni = resultSet.getString("DNI");
                String apellidos = resultSet.getString("APELLIDOS");
                String cp = resultSet.getString("CP");
                System.out.println(dni + "\t" + apellidos + "\t" + cp + "\t");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
