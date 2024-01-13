package sebas.juan.demo.helpers.Usuarios;


import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;
@Service
public class getFilesFromUserService {

    public static ArrayList<HashMap<String, Object>> getFiles(String id) {
        String sql = "SELECT id FROM usuarios WHERE nombre_usuario = ? AND id = ?\"";
        Connection conn = utiles.connectDB();
        ArrayList<HashMap<String, Object>> ficheros2 = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String fileId = resultSet.getString("id");
                    String fileName = resultSet.getString("filename");
                    String originalFileName = resultSet.getString("original_filename");
                    String extension = resultSet.getString("extension");


                    // Crear un nuevo HashMap para cada archivo y agregarlo a la lista
                    HashMap<String, Object> ficheroMap = new HashMap<>();
                    ficheroMap.put("id", fileId);
                    ficheroMap.put("filename", fileName);
                    ficheroMap.put("original_filename", originalFileName);
                    ficheroMap.put("extension", extension);

                    ficheros2.add(ficheroMap);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL COGER LOS FICHEROS");
        }
        return ficheros2;
    }
}