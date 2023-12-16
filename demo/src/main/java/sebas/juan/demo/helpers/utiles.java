package sebas.juan.demo.helpers;

import java.sql.*;
import java.io.*;

public class utiles {

    public static Connection connectDB() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/cloud";
            String usuario = "root";
            String contraseña = "";

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
            registerFile(name);
            conn.close();
            System.out.println("Registro insertado");
        } catch (Exception e) {
            System.err.println("Hay un problema! ");
            System.err.println(e.getMessage());
        }
    }


    public static void registerFile(String userName) {
        String url = "fileUser/";
        File directorio = new File(url + userName);
        System.out.println(directorio.getPath());

        
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }



}
