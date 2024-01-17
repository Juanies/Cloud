package sebas.juan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sebas.juan.demo.helpers.Usuarios.utiles;

@SpringBootApplication
public class CloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
        utiles.connectDB();
    }
}
