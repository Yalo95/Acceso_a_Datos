package Ejercicio2_Tema2;

import java.sql.*;

public class Lotes {
    String url = "jdbc:mariadb://localhost/pruebaBD";
    String user = "root";
    String password = "root";


    public void comprobacion(){
        try(Connection connection = DriverManager.getConnection(url, user, password)){

            boolean soportaLotes = connection.getMetaData().supportsBatchUpdates();
            System.out.println(soportaLotes);
            soportaLotes = false; //Comentando esta linea podemos comprobar si se ejecuta por lotes
            System.out.println(soportaLotes);
            String[][] datosClientes={ {"13579135G","MOYA",null},
                    {"24680246G","GARCES","25865"}, {"96407418R","VENTURA","10273"}};
            try (PreparedStatement psInsert = connection.prepareStatement("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES (?,?,?)")){
                connection.setAutoCommit(false);
                for (int i=0;i<datosClientes.length;i++) {

                    for (int j=0;j<datosClientes[i].length;j++) {
                        psInsert.setString(j+1,datosClientes[i][j]);
                    }
                    if (soportaLotes == true){
                        psInsert.addBatch();
                        System.out.println("Se ejecuta por lotes");
                    }else{
                        System.out.println("Ejecutamos sentencia a sentencia");
                        psInsert.executeUpdate();
                    }
                }
                psInsert.executeBatch();
                connection.commit();
            } catch (SQLException e){
                System.out.println("No se ha podido realizar la comprobación: " + e.getMessage());
                connection.rollback();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
