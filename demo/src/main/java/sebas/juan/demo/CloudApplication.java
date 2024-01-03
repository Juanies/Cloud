package sebas.juan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RestController;
import sebas.juan.demo.helpers.Usuarios.getFilesFromUserService;
import sebas.juan.demo.helpers.Usuarios.utiles;
import sebas.juan.demo.helpers.Token.*;



@SpringBootApplication
@RestController
@Controller
public class CloudApplication {
  public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
    utiles.connectDB();
  }
}
