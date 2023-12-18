// UploadFileService.java
package sebas.juan.demo.helpers.Usuarios;

import java.io.File;

import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.io.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    public void uploadFile(MultipartFile file, int id, String originalFileName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");
        String newFileName = localDateTime.format(formatter);

        System.out.println("Original file name: " + originalFileName);

        // Construir el objeto File con la nueva ruta y nombre
        File newFile = new File("/cloud/Cloud/usersFiles/" + newFileName);

        // Guardar el archivo con el nombre original en la carpeta temporal

        System.out.println("File has been saved successfully.");
        System.out.println(file);
        try {
            file.transferTo(newFile.toPath());

            System.out.println("File has been renamed successfully.");
            Connection conn = utiles.connectDB();

            String sql = "INSERT INTO user_file () VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, originalFileName);
                preparedStatement.setString(3, newFileName);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Fichero reflejado en la base de datos correctamente");
                } else {
                    System.out.println(
                            "No se puedo reflejar el fichero en la base de datos correctamente.");
                }

            } catch (SQLDataException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
