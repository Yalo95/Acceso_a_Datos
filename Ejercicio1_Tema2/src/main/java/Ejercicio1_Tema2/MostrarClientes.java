package Ejercicio1_Tema2;

import java.sql.*;
import java.util.Scanner;

public class MostrarClientes extends Clientes{

    String ordenarByDni = "SELECT * FROM CLIENTES ORDER BY DNI";
    String pedirDni = "SELECT DNI FROM CLIENTES";
    String solicitarDNI = "SELECT * FROM CLIENTES WHERE DNI =?";

    public void mostrarClientesDni() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             Statement stmt2 = connection.createStatement()) {
            //Mostrar los clientes ordenados por DNI
            ResultSet resultSet = stmt.executeQuery(ordenarByDni);
            System.out.println("\n" + "---------- EJERCICIO 3 ----------" + "\n");
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
    }//ORDENAR MEDIANTE DNI PERO DE FORMA INVERSA
    public void dniOrdenInverso(){

        int scrolleable = ResultSet.TYPE_SCROLL_INSENSITIVE;//ResultSet para recorrer bidireccionalmente
        int concurrencia = ResultSet.CONCUR_READ_ONLY;//Para crear la concurrencia y pasarlo al createStatement

        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement(scrolleable, concurrencia)){

            ResultSet resultSet = stmt.executeQuery(ordenarByDni);
            System.out.println("\n" + "---------- EJERCICIO 4 ----------" + "\n");
            System.out.println("Clientes ordenados de forma inversa por DNI: ");
            resultSet.afterLast();//Situa al cursor por detras de la ultima fila.
            while(resultSet.previous()){ //Desplaza el cursor a la fila anterior
                String dni = resultSet.getString("DNI");
                String apellidos = resultSet.getString("APELLIDOS");
                String cp = resultSet.getString("CP");
                System.out.println(dni + "\t" + apellidos + "\t" + cp + "\t");
            }
            //Pregunta Resuelta
            System.out.println("\n" + "Usamos el método .last() de ResultSet para posicionar el puntero en la última fila. " + "\n" +
                    "Después utilizamos el método .getRow() de ResulSet para indicarnos la fila en la que esta el puntero" +
                    " y saber así cuantas filas se obtiene de la consulta sin recorrer toda la tabla.");
            resultSet.last();
            System.out.println("\n" + "Número de filas obtenidas: " + resultSet.getRow());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    public void mostrarDni(){

        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(solicitarDNI);
            Scanner scanner = new Scanner(System.in)){

            ResultSet resultSet1 = stmt.executeQuery(pedirDni);
            System.out.println("\n" + "---------- EJERCICIO 5 ----------" + "\n");
            System.out.println("DNI de cada uno de nuestros clientes: " + "\n");
            while(resultSet1.next()){
                String dni = resultSet1.getString("DNI");
                System.out.println(dni);
            }
            //Solicitud del dni
            System.out.println("\n" + "Introduzca el dni: ");
            String dniSolicitado = scanner.nextLine();
            preparedStatement.setString(1, dniSolicitado);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            if (resultSet2.next()){
                String dni = resultSet2.getString("DNI");
                String apellidos = resultSet2.getString("APELLIDOS");
                String cp = resultSet2.getString("CP");
                System.out.println(dni + "\t" + apellidos + "\t" + cp + "\t");
            }else{
                System.out.println("El dni introducido no existe en la base de datos");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
