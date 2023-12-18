package sebas.juan.demo.helpers.Usuarios;

import java.sql.*;


public class utiles {

    public static Connection connectDB() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url =
                    "jdbc:mysql://butxakeeezwxbk4bnx3q-mysql.services.clever-cloud.com:3306/butxakeeezwxbk4bnx3q";
            String user = "ubje4pfqqyibmggx";
            String password = "vuaUepVHp7lFF6ChO01e";

            conexion = DriverManager.getConnection(url, user, password);

            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Problema con la conexion a la base de datos");
            e.printStackTrace();
        }

        return conexion;
    }

    public static void insertNewUser(String name, String password, String mail, String phone, boolean isVerify) {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = connectDB();

            // Verificar si el usuario ya existe
            String checkUserQuery = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
            try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery)) {
                checkUserStatement.setString(1, name);

                try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            System.out.println("Usuario ya estaba creado");
                            return; // Salir del método si el usuario ya existe
                        }
                    }
                }
            }

            // Insertar nuevo usuario si no existe
            String insertUserQuery =
                    "INSERT INTO usuarios (tel, nombre, contraseña, mail, email_is_verify) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertUserStatement = conn.prepareStatement(insertUserQuery)) {
                insertUserStatement.setString(1, phone);
                insertUserStatement.setString(2, name);
                insertUserStatement.setString(3, password);
                insertUserStatement.setString(4, mail);
                insertUserStatement.setBoolean(5, isVerify);

                int rowsAffected = insertUserStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Usuario insertado correctamente.");
                } else {
                    System.out.println("No se pudo insertar el usuario.");
                }
            }

            // Cerrar la conexión con la base de datos
            conn.close();
            System.out.println("Registro insertado");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Hay un problema!");
            System.err.println(e.getMessage());
        }
    }



}
