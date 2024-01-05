package sebas.juan.demo.helpers.Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sebas.juan.demo.helpers.Token.Token;
import org.springframework.context.annotation.Configuration;

@RestController
@RequestMapping("/api/files")
public class getFilesFromUserController {

    @GetMapping("/getfiles")
    public static ArrayList<HashMap<String, Object>> getFiles(@CookieValue(value = "cookie") String valorCookie) {
        String token = LoginController.obtenerCookie(valorCookie);
        String username = Token.getName(token);
        String id = Usuario.getid(username);
        return getFilesFromUserService.getFiles(id);
    }


    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/files/getfiles")
                    .allowedOrigins("http://localhost:5173") // Agrega la URL de tu frontend
                    .allowedMethods("GET")
                    .allowCredentials(true);
        }
    }
}
