package Ejercicio1_Tema2;

import java.sql.*;

public class MostrarClientes extends Clientes{

    String ordenarByDni = "SELECT * FROM CLIENTES ORDER BY DNI";

    public void mostrarClientesDni() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            //Mostrar los clientes ordenados por DNI
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(ordenarByDni);
            System.out.println("Clientes ordenados por DNI: " + resultSet);
        } catch (SQLException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
