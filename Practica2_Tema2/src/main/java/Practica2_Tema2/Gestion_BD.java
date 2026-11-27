package Practica2_Tema2;

import Practica2_Tema2.conexion.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Gestion_BD {
    Conexion conexion = new Conexion();

    public void vaciarTablas(){
        String queryDelete =  "ALTER TABLE ? DELETE FOREIGN KEY ?;";


        try (Connection connection = DriverManager.getConnection(conexion.getUrl(), conexion.getUser(), conexion.getPassword())){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void defaultCategorias(){
        try (Connection connection = DriverManager.getConnection(conexion.getUrl(), conexion.getUser(), conexion.getPassword())){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ejecutarScript (String file){

        try (Connection connection = DriverManager.getConnection(conexion.getUrl(), conexion.getUser(), conexion.getPassword())){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
