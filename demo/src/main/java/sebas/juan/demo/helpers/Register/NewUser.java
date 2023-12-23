package sebas.juan.demo.helpers.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import sebas.juan.demo.helpers.Usuarios.utiles;

public class NewUser {

    public static boolean insertNewUser(String name, String password, String mail, String phone) {
        System.out.println("New user Activated");

        name = name.trim();
        password = password.trim();
        mail = mail.trim();
        phone = phone.trim();

        int countName = 0;
        int countMail = 0;

        try {
            // Conectar a la base de datos
            Connection conn = utiles.connectDB();

            // Verificar si el nombre de usuario ya existe
            countName = checkExistingUser(conn, name, "nombre");

            // Verificar si el correo electrónico ya existe
            countMail = checkExistingUser(conn, mail, "mail");

            // Mostrar mensajes si el usuario o correo ya existen
            if (countMail > 0) System.out.println("User already with this mail");
            if (countName > 0) System.out.println("User already with this Name");
            if (countMail > 0 && countName > 0) System.out.println("User already with this Email and User");

            // Insertar nuevo usuario si no existe
            if (countMail == 0 || countName == 0) {
                insertUser(conn, name, password, mail, phone);
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

    private static int checkExistingUser(Connection conn, String value, String columnName) throws SQLException {
        int count = 0;

        String checkUserQuery = "SELECT COUNT(*) FROM usuarios WHERE " + columnName + " = ?";

        try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery)) {
            checkUserStatement.setString(1, value);

            try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private static void insertUser(Connection conn, String name, String password, String mail, String phone) throws SQLException {
        // Insertar nuevo usuario si no existe
        String insertUserQuery = "INSERT INTO usuarios (tel, nombre, contraseña, mail) VALUES (?, ?, ?, ?)";

        try (PreparedStatement insertUserStatement = conn.prepareStatement(insertUserQuery)) {
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
    }
}
