package sebas.juan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.FloatArraySerializer;
import sebas.juan.demo.helpers.*;
@SpringBootApplication
public class CloudApplication {

	public static void main(String[] args) {
		utiles.connectDB();
				utiles.insertNewUser("miguel", "!22", "22", "2", false);

	}

}

