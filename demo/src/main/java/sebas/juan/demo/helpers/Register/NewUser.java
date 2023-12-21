package sebas.juan.demo.helpers.Register;
import java.sql.*;

import java.io.*;
import org.springframework.stereotype.Service;
import sebas.juan.demo.helpers.Usuarios.utiles;

@Service
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
            // Establecer la conexión con la base de datos
            Connection conn = utiles.connectDB();

            // Verificar si el nombre del  usuario ya existe
            String checkUserQuery = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
            try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery)) {
                checkUserStatement.setString(1, name);

                try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                    if (resultSet.next()) {
                        countName = resultSet.getInt(1);
                    }
                }
            }
            // Verificar si el correo del  usuario ya existe
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

                // Cerrar la conexión con la base de datos
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
