package Ejercicio1_Tema2;

import java.sql.*;

public class Clientes {
    //DRIVER JDBC
    String url = "jdbc:mariadb://localhost/demo";
    String user = "root";
    String password = "root";

    //SENTENCIAS SQL
    String sql = "CREATE TABLE CLIENTES (\n" +
            "DNI CHAR(9) NOT NULL,\n" +
            "APELLIDOS VARCHAR(32) NOT NULL,\n" +
            "CP CHAR(5),\n" +
            "PRIMARY KEY (DNI)\n" +
            ");";
    String insertarDatos = "INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES\n" +
            "('78901234X','NADALES','44126'),\n" +
            "('89012345E','HOJAS', null),\n" +
            "('56789012B','SAMPER','29730'),\n" +
            "('09876543K','GONZALO',null);";

    String modificarRojas = "UPDATE CLIENTES SET APELLIDOS='ROJAS' WHERE APELLIDOS='HOJAS'";
    String borrarSamper = "DELETE FROM CLIENTES WHERE APELLIDOS= 'SAMPER'";

    public void crearTabla() {

        //Aquí creamos la conexión con la base de datos y ejecutamos la sentencia SQL para crear la tabla CLIENTES
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Error al cargar el driver de MariaDB: " + e.getMessage());
            System.exit(1);
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
           //Ejercicio 1 --- Creamos la tabla CLIENTES
            connection.createStatement().execute(sql);

            //Ejercicio 2 --- Insertamos los datos en la tabla CLIENTES
            connection.createStatement().executeUpdate(insertarDatos);
        } catch (SQLException e){
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }

    }
    public void modificarApellido(){//Modificamos el apellido HOJAS por ROJAS

        try(Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/demo", "root", "root")) {

            connection.createStatement().executeUpdate(modificarRojas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrarSamper(){//Borramos el cliente con apellido SAMPER

        try(Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/demo", "root", "root")) {
            connection.createStatement().executeUpdate(borrarSamper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
