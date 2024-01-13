package sebas.juan.demo.helpers.Usuarios;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sebas.juan.demo.helpers.Interceptor.Middleware;
import java.util.ArrayList;
import java.util.HashMap;
import sebas.juan.demo.helpers.Usuarios.*;

@RestController
@RequestMapping("/api/files")
public class getFilesFromUserController {

    @GetMapping("/getfiles")
    public ArrayList<HashMap<String, Object>> getFiles(@CookieValue(value = "cookie", required = false) String valorCookie) {
        // Obtener el nombre de usuario del ThreadLocal
        String username = Middleware.getUserName();

        // Obtener otros datos necesarios según tus requisitos
        String token = LoginController.obtenerCookie(valorCookie);
        String id = Usuario.getid(username);

        // Obtener archivos utilizando el servicio
        ArrayList<HashMap<String, Object>> files = getFilesFromUserService.getFiles(id);

        // Imprimir el nombre de usuario y la lista de archivos
        System.out.println("Nombre de usuario: " + username);
        // Resto del código...

        // Retornar la lista de archivos
        return files;
    }
}