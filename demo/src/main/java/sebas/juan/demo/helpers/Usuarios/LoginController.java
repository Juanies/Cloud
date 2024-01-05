package sebas.juan.demo.helpers.Usuarios;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.jsonwebtoken.lang.Arrays;
import sebas.juan.demo.helpers.Token.Token;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.system.SystemProperties;
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
import javax.servlet.http.Cookie;



@RestController
@RequestMapping("/api/user")
public class LoginController {
    @PostMapping("userlogin")
    public static ResponseEntity<String> userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean resultado = Usuario.login(username, password);
        return ResponseEntity.ok("Archivo subido y procesado con éxito.");
    }



    @RequestMapping("/crearCookie")
    public static String crearCookie(HttpServletResponse response, String username, String password) {
        
        String resutlado =   "Cookie no creada correctamente";
        Cookie miCookie = new Cookie("cookie", Token.webToken(username, password) );
        if (Token.validateToken(Token.webToken(username, password))) {
            response.addCookie(miCookie);
            System.out.println("Cookie  creada correctamente");

        }
        return resutlado;
    }

    @RequestMapping("/obtenerCookie")
    public static String obtenerCookie(@CookieValue(value = "cookie") String valorCookie) {
        return valorCookie;
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/user/userlogin")
                    .allowedOrigins("http://localhost:5173") // Add your frontend URL
                    .allowedMethods("GET", "POST")
                    .allowCredentials(true);
        }
    }

}
