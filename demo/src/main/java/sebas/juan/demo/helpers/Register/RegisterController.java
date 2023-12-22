package sebas.juan.demo.helpers.Register;

import java.time.format.SignStyle;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import sebas.juan.demo.helpers.Requests.RequestUser;
import sebas.juan.demo.helpers.Usuarios.UploadFileService;
import sebas.juan.demo.helpers.Usuarios.utiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user")
public class RegisterController {

    @PostMapping("/register")
    public ResponseEntity<String> handleFileUpload(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("mail") String mail,@RequestParam("phone") String phone) {
        RequestUser user = new RequestUser(username, password, mail, phone);
        UserService.SignUp(user);
        NewUser.insertNewUser(username, password, mail, phone);
        return ResponseEntity.ok("Archivo subido y procesado con éxito.");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/user/register")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
