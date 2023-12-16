package sebas.juan.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sebas.juan.demo.helpers.Usuarios.Usuario;

@SpringBootApplication
@RestController
public class CloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	

	

		Usuario usuario = new Usuario(				"Manolo",
				"123",
				"6213832",
				"@gmail.com",
				true,
				21);
		usuario.uploadFile();
	}

	@GetMapping
	public List<Usuario> hello(){
		return List.of(
			new Usuario(
				"Mariam",
				"123",
				"6213832",
				"@gmail.com",
				true,
				21)
			);
	}

}
