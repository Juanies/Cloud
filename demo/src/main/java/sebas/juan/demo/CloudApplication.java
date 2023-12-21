package sebas.juan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RestController;
import sebas.juan.demo.helpers.Usuarios.Usuario;
import sebas.juan.demo.helpers.Usuarios.utiles;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Pattern;
import sebas.juan.demo.helpers.Email.SendEmail;
import sebas.juan.demo.helpers.Requests.RequestUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@SpringBootApplication
@RestController
@Controller
public class CloudApplication {
  public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
    utiles.connectDB();
    Usuario.changeNameFile("hola.txt"    , "muchotexto" , 1);

  }
}
