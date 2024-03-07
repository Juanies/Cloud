package sebas.juan.demo.helpers.Usuarios;
import sebas.juan.demo.helpers.Token.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/api/user")
public class LoginController {
    @PostMapping("/userlogin")
    public ResponseEntity<String> userLogin(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
        boolean resultado = Usuario.login(username, password);
        if (resultado) {
            crearCookie(response, username, password);
            return ResponseEntity.ok("Conexión exitosa. " + username);
        } else {
            return ResponseEntity.badRequest().body("Error: Credenciales inválidas.");
        }
    }

    public String crearCookie(HttpServletResponse response, String username, String password) {
        String token = Token.webToken(username, password);

        Cookie miCookie = new Cookie("miCookie", token);
        miCookie.setMaxAge(3600);
        miCookie.setPath("/");

        response.addCookie(miCookie);
        System.out.println("Cookie creada correctamente para el usuario: " + username);
        return "Cookie creada correctamente para el usuario: " + username;
    }


    @RequestMapping("/obtenerCookie")
    public static String obtenerCookie(@CookieValue(value = "cookie", required = false) String valorCookie) {
        return Token.getName(valorCookie);
    }
}