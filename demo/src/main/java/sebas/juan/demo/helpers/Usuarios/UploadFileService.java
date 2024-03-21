// UploadFileService.java
package sebas.juan.demo.helpers.Usuarios;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadFileService {
    public static void uploadFiles(List<MultipartFile> files, int id2) {
        try (Connection conn = utiles.connectDB()) {
            for (MultipartFile file : files) {
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");

                String newFileName = localDateTime.format(formatter);
                File newFile = new File("F:\\Cloud\\usersFiles\\" + newFileName);

                System.out.println("File has been saved successfully.");
                System.out.println(file);
                System.out.println(newFile.getAbsolutePath());

                file.transferTo(newFile.toPath());
                System.out.println("File has been renamed successfully.");

                String sql = "INSERT INTO user_file (id, filename, original_filename) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id2);
                    preparedStatement.setString(2, file.getOriginalFilename());
                    preparedStatement.setString(3, newFileName); 

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("Fichero reflejado en la base de datos correctamente");
                    } else {
                        System.out.println("No se pudo reflejar el fichero en la base de datos correctamente.");
                    }
                } catch (SQLException e) {
                    System.out.println("No se pudo reflejar el fichero en la base de datos correctamente.");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("uiwu");
        }
    }
}
