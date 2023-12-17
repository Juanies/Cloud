package sebas.juan.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sebas.juan.demo.helpers.Usuarios.Usuario;
import sebas.juan.demo.helpers.Usuarios.utiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sebas.juan.demo.helpers.*;

@SpringBootApplication
@RestController
@Controller

public class CloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
        
        utiles.connectDB();
        utiles.insertNewUser("manolo", null, null, null, false);
        
        Usuario usuario1 = new Usuario(7, null, null, null, null, false, 0) ;
        usuario1.uploadFile("hola.txt");
	}

    

    @RequestMapping("/")
    public String index() {
        return "index";
    }




}
