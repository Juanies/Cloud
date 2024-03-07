package sebas.juan.demo.helpers.Usuarios;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sebas.juan.demo.helpers.Usuarios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sebas.juan.demo.helpers.Token.Token;
import sebas.juan.demo.helpers.Usuarios.*;




@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")  // Ajusta el origen según tu configuración
public class getFilesFromUserController {

    @Autowired
    private HttpServletRequest request;

    @CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/getfiles")
    public ArrayList<HashMap<String, Object>> getFiles() {
        // Usamos el Middleware para obtener el nombre de usuario
        String username = (String) request.getAttribute("username");

        if (username == null) {
            throw new RuntimeException("Nombre de usuario no encontrado");
        }

        ArrayList<HashMap<String, Object>> files = getFilesFromUserService.getFiles(username);
        System.out.println("Nombre de usuario: " + username);
        return files;
    }
}