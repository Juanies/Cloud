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

    

    
    public static boolean insertNewUser(String name, String password, String mail, String phone) {
        name = name.trim();
        password = password.trim();
        mail = mail.trim();
        phone = phone.trim();
        int countName = 0;
        int countMail = 0;
        try {
            Connection conn = connectDB();

            String checkUserQuery = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
            try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery)) {
                checkUserStatement.setString(1, name);

                try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                    if (resultSet.next()) {
                        countName = resultSet.getInt(1);
                    }
                }
            }

            String checkMailQuery = "SELECT COUNT(*) FROM usuarios WHERE mail = ?";

            try (PreparedStatement checkUserStatement = conn.prepareStatement(checkMailQuery)) {
                checkUserStatement.setString(1, mail);

                try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                    if (resultSet.next()) {
                        countMail = resultSet.getInt(1);
                    }
                }
            }


            if(countMail > 0) System.out.println("User already with this mail");
            if(countName > 0) System.out.println("User already with this Name");
            if (countMail > 0 && countName > 0) System.out.println("User already with this Email and User");

            if (countMail == 0 && countName == 0) {
                // Insertar nuevo usuario si no existe
                String insertUserQuery =
                        "INSERT INTO usuarios (tel, nombre, contraseña, mail) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertUserStatement =
                        conn.prepareStatement(insertUserQuery)) {
                    insertUserStatement.setString(1, phone);
                    insertUserStatement.setString(2, name);
                    insertUserStatement.setString(3, password);
                    insertUserStatement.setString(4, mail);

                    int rowsAffected = insertUserStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Usuario insertado correctamente.");
                    } else {
                        System.out.println("No se pudo insertar el usuario.");
                    }
                }
                conn.close();
                System.out.println("Registro insertado");
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Hay un problema!");
            System.err.println(e.getMessage());
        }


        return false;

    }
}


