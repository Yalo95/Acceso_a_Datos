import java.sql.*;
import java.util.Scanner;

public class ConexionBd {
    static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/prueba_programacion";
        String username = "root";
        String password = "";


        //RESULTADOS LEGIBLES

        /*String sql = "SELECT * FROM clientes"; //Aqui elegimos la tabla que queramos ver, a traves de consultas.

        try {
            Connection conexion = DriverManager.getConnection(url,username,password); //Para conectar con la Base de Datos

            Statement statement = conexion.createStatement(); //Este metodo mete el query
            ResultSet rs1 = statement.executeQuery(sql); //Este metodo ejecuta la query
            while (rs1.next()){ //Este bucle recorre toda la tabla sin saber cuando acaba.
                System.out.println(rs1.getString(1) + "\t" + rs1.getString(2)+ "\t"
                        + rs1.getString(3)+ "\t" + rs1.getString(4)+ "\t"
                + rs1.getString(5)+ "\t" + rs1.getString(6)+ "\t"
                + rs1.getString(7)+ "\t" + rs1.getString(8));
                //Muestra los datos que queramos. Dentro del parentesis
                // ponemos el nombre o el numero de columnas que queramos ver
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        /*String sql2 = "INSERT INTO clientes (id_cliente,nombre,apellidos,telefono) VALUES (?,?,?,?)";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nuevo id: ");
        String nuevoId = scanner.nextLine();
        System.out.println("Introduce el nombre del nuevo cliente: ");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Introduce el apellido del nuevo cliente: ");
        String nuevoApellido = scanner.nextLine();
        System.out.println("Introduce el telefono del nuevo cliente: ");
        String nuevoTelefono = scanner.nextLine();

        try {
            Connection conexion = DriverManager.getConnection(url,username,password);

            PreparedStatement query_preparada = conexion.prepareStatement(sql2);


            query_preparada.setString(1,nuevoId);//Cada primer numero cambia la interrogacion de la query
                                                                // de su posicion por el valor siguiente
            query_preparada.setString(2,nuevoNombre);
            query_preparada.setString(3,nuevoApellido);
            query_preparada.setString(4,nuevoTelefono);

            int filas_afectadas = query_preparada.executeUpdate(); //Para ejecutar el query
            System.out.println(filas_afectadas);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        //DELETE
        String sql3 = "DELETE FROM clientes WHERE id_cliente = ?";

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nuevo id: ");
        String nuevoId = scanner.nextLine();
        System.out.println("Introduce el nombre del nuevo cliente: ");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Introduce el apellido del nuevo cliente: ");
        String nuevoApellido = scanner.nextLine();
        System.out.println("Introduce el telefono del nuevo cliente: ");
        String nuevoTelefono = scanner.nextLine();*/

        try {
            Connection conexion = DriverManager.getConnection(url,username,password);

            PreparedStatement query_preparada = conexion.prepareStatement(sql3);


            query_preparada.setString(1,"30");//Cada primer numero cambia la interrogacion de la query
                                                                // de su posicion por el valor siguiente
            /*query_preparada.setString(2,"Facundo");
            query_preparada.setString(3,"González");
            query_preparada.setString(4,"982178345");*/

            int filas_afectadas = query_preparada.executeUpdate(); //Para ejecutar el query
            System.out.println(filas_afectadas);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //UPDATE

        String sql4 = "UPDATE clientes SET nombre = ?, apellidos = ?, telefono = ? WHERE id_cliente = ?";

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nuevo id: ");
        String nuevoId = scanner.nextLine();
        System.out.println("Introduce el nombre del nuevo cliente: ");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Introduce el apellido del nuevo cliente: ");
        String nuevoApellido = scanner.nextLine();
        System.out.println("Introduce el telefono del nuevo cliente: ");
        String nuevoTelefono = scanner.nextLine();*/

        try {
            Connection conexion = DriverManager.getConnection(url,username,password);

            PreparedStatement query_preparada = conexion.prepareStatement(sql4);


            query_preparada.setString(1,"Macarena");//Cada primer numero cambia la interrogacion de la query
            // de su posicion por el valor siguiente
            query_preparada.setString(2,"Hernández");
            query_preparada.setString(3,"923456744");
            query_preparada.setString(4,"21");

            int filas_afectadas = query_preparada.executeUpdate(); //Para ejecutar el query
            System.out.println("Filas actualizadas: " + filas_afectadas);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
