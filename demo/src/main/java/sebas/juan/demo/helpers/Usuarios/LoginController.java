
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
    public static ResponseEntity<String> userLogin(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
        boolean resultado = Usuario.login(username, password);
        if (resultado) {
            crearCookie(response, username, password);
            return ResponseEntity.ok("Conexión exitosa." + username);
        } else {
            return ResponseEntity.badRequest().body("Error: Credenciales inválidas.");
        }
    }

    @RequestMapping("/crearCookie")
    public static String crearCookie(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (Usuario.login(username, password)) {
            Cookie miCookie = new Cookie("cookie", Token.webToken(username, password));
            miCookie.setMaxAge(60000*60            );
            response.addCookie(miCookie);
            System.out.println("Cookie creada correctamente para el usuario: " + username);
            return "Cookie creada correctamente para el usuario: " + username;
        } else {
            return "Error: No se pudo crear la cookie. Credenciales inválidas.";
        }
    }

    @RequestMapping("/obtenerCookie")
    public static String obtenerCookie(@CookieValue(value = "cookie", required = false) String valorCookie) {
        return valorCookie != null ? valorCookie : "Cookie no encontrada";
    }
}
