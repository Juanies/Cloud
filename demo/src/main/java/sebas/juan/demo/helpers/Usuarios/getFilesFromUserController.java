package sebas.juan.demo.helpers.Usuarios;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")

public class getFilesFromUserController {

    @GetMapping("/getfiles")
    public ArrayList<HashMap<String, Object>> getFiles(HttpServletRequest request) {
        String valorCookie = (String) request.getAttribute("miCookieValor");
        return getFilesFromUserService.getFiles(getFilesFromUserService.getId(valorCookie));
    }
}
