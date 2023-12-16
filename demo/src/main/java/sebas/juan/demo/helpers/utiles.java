package sebas.juan.demo.helpers;

import java.sql.*;

public class utiles {

    public static Connection connectDB() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/cloud";
            String usuario = "root";
            String contraseña = "";

            //Host: butxakeeezwxbk4bnx3q-mysql.services.clever-cloud.com
            //Database name: butxakeeezwxbk4bnx3q
            //User: ubje4pfqqyibmggx
            //Password: vuaUepVHp7lFF6ChO01e
           // Port: 3306

            conexion = DriverManager.getConnection(url, usuario, contraseña);

            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conexion;
    }


    public static void insertNewUser(String name, String password, String mail, String phone, boolean isVerify) {
        try {
            Connection conn = connectDB();
            Statement st = conn.createStatement();

            String sql = "INSERT INTO usuarios (tel, nombre, contraseña, mail, email_is_verify) VALUES (?, ?, ?, ?,?)";

            try (PreparedStatement preparedStatement = connectDB().prepareStatement(sql)) {

                preparedStatement.setString(1, phone);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, mail);
                preparedStatement.setBoolean(5, isVerify);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Usuario insertado correctamente.");
                } else {
                    System.out.println("No se pudo insertar el usuario.");
                }
            }
            conn.close();
            System.out.println("Registro insertado");
        } catch (Exception e) {
            System.err.println("Hay un problema! ");
            System.err.println(e.getMessage());
        }
    }

}
