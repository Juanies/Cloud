package sebas.juan.demo.helpers.Register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
@RequestMapping("/api/user")
public class RegisterController {

    @PostMapping("/register")
    public ResponseEntity<String> handleFileUpload(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("mail") String mail,@RequestParam("phone") String phone) {
        NewUser.insertNewUser(username, password, mail, phone);

        return ResponseEntity.ok("Archivo subido y procesado con éxito.");
    }

    @Bean
    public WebMvcConfigurer registerCorsConfigurer() {
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
