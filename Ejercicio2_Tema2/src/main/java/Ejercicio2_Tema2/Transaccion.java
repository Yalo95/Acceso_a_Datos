package Ejercicio2_Tema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaccion {
    //DRIVER JDBC
    String url = "jdbc:mariadb://localhost/pruebaBD";
    String user = "root";
    String password = "root";

    public void conTransaccion(){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            try(PreparedStatement psInsert = connection.prepareStatement
                    ("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES (?,?,?)")) {
                connection.setAutoCommit(false);
                psInsert.setString(
                        1, "54320198V");
                psInsert.setString(
                        2, "CARVAJAL");
                psInsert.setString(
                        3, "10109");
                psInsert.executeUpdate();
                psInsert.setString(
                        1, "76543210S");
                psInsert.setString(
                        2, "MARQUEZ");
                psInsert.setString(
                        3, "46987");
                psInsert.executeUpdate();
                psInsert.setString(
                        1, "54320198V");
                psInsert.setString(
                        2, "MOLINA");
                psInsert.setString(
                        3, "35153");
                psInsert.executeUpdate();
                connection.commit();
            } catch (SQLException e){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }e.printStackTrace();
            } finally { connection.setAutoCommit(true);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void sinTransacciones(){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            try(PreparedStatement psInsert = connection.prepareStatement
                    ("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES (?,?,?)")) {
                psInsert.setString(
                        1, "54320198V");
                psInsert.setString(
                        2, "CARVAJAL");
                psInsert.setString(
                        3, "10109");
                psInsert.executeUpdate();
                psInsert.setString(
                        1, "76543210S");
                psInsert.setString(
                        2, "MARQUEZ");
                psInsert.setString(
                        3, "46987");
                psInsert.executeUpdate();
                psInsert.setString(
                        1, "54320198V");
                psInsert.setString(
                        2, "MOLINA");
                psInsert.setString(
                        3, "35153");
                psInsert.executeUpdate();
            } catch (SQLException e){
                System.err.println("Error en una insercion: " + e.getMessage() + " Las inserciones anteriores ya se han " +
                        "guardado en la BD porque no se usa transacción");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
