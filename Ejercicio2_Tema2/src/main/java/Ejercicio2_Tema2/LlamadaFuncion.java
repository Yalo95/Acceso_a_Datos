package Ejercicio2_Tema2;

import java.sql.*;

//PARA CREAR LA FUNCION EN LA BASE DE DATOS
    /*DELIMITER $$
        CREATE OR REPLACE FUNCTION apellidos_dni (psDNI CHAR(9)) RETURNS VARCHAR(32)

        BEGIN DECLARE apellido VARCHAR(32) DEFAULT NULL;
        SELECT APELLIDOS INTO apellido FROM CLIENTES WHERE DNI=psDNI;  Ç
        RETURN apellido;

      END $$
      DELIMITER ; */
//Con esta funcion conseguimos que al pasarle por parametro un dni nos devuelva el apellido de la persona
//Si el dni no esta en la base de datos, nos devuelve un null.

public class LlamadaFuncion {

    String url = "jdbc:mariadb://localhost/pruebaBD";
    String user = "root";
    String password = "root";

    public void llamada(String dni){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            try (CallableStatement cs = connection.prepareCall("{? = call apellidos_dni (?)}")) {

                cs.setString(2,dni); // dni. Es 2 porque lee es el segundo ? del callable statemente
                                                    //el dentro del parentesis el que tiene que leer
                cs.registerOutParameter(1,java.sql.Types.VARCHAR); //Varchar es el tipo de dato de la funcion
                cs.execute();
                String resultado = cs.getString(1);//Es 1 porque debe leer el primer ?, el que devuelve la funcion
                if (resultado == null) {
                    System.out.println("No existe ningún cliente en la base de datos con ese dni");
                }else{
                    System.out.println(resultado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
