package sebas.juan.demo.helpers.Usuarios;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.jsonwebtoken.lang.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.IllegalStateException;
import java.net.*;
@RestController
@RequestMapping("/api/user")
public class LoginController {
    @PostMapping("userlogin")
    public ResponseEntity<String> userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Usuario.login(username, password);
        return ResponseEntity.ok("Archivo subido y procesado con éxito.");
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/user/userlogin")
                    .allowedOrigins("http://localhost:5173") // Add your frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);
        }
    }
}
