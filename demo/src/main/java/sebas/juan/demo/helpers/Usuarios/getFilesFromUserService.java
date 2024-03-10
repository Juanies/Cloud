package sebas.juan.demo.helpers.Usuarios;

import java.sql.*;
import org.apache.catalina.filters.CorsFilter; // Import innecesario, probablemente accidental
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Service // Anotación indicando que esta clase es un componente de servicio en Spring
public class getFilesFromUserService {

    public static String getId(String username){
        String sql = "SELECT id FROM usuarios WHERE nombre = ?";
        Connection conn = utiles.connectDB();
        String res = null;

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { // Verificar si hay filas en el resultado
                    String id = resultSet.getString("id");
                    res = id;
                } else {
                    System.out.println("No se encontró ningún usuario con el nombre: " + username);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static ArrayList<HashMap<String, Object>> getFiles(String id) {
        String sql = "SELECT id, filename, original_filename, extension FROM user_file WHERE id = ?";
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
            System.out.println(e);
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ficheros2;
    }
}
