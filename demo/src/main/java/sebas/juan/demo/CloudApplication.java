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
import sebas.juan.demo.helpers.Email.VerifyEmail;
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

    VerifyEmail.generateToken();
    // utiles.insertNewUser("manolo", null, null, null, false);
      
    // Usuario usuario1 = new Usuario(7, null, null, null, null, false, 0) ;
    // usuario1.uploadFile("hola.txt");
	}

  @PostMapping("/sign-up")
  public ResponseEntity<HashMap<String, Object>> SignUp(@RequestBody RequestUser user) {
    var msg = new HashMap<String, Object>();
    var errors = new ArrayList<String>();

    String username = user.getUsername();
    String password = user.getPassword();
    String email = user.getEmail();
    String phone = user.getPhone();

    if (username == null || username.length() == 0)
      errors.add("name is required");
    if (username == null || password.length() == 0)
      errors.add("password is required");
    if ((email == null || email.length() == 0) && (phone == null || phone.length() == 0))
      errors.add("email or phone is required");

    boolean valid;

    if (email != null) {
      valid = Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
      if (!valid)
        errors.add("email is not valid");
    } else
      email = "";

    if (phone != null) {
      valid = Pattern.compile("(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
          .matcher(phone).matches();
      if (!valid)
        errors.add("phone is not valid");
    } else
      phone = "";

    msg.put("errors", errors);
    if (errors.size() > 0) {
      msg.put("msg", "unsuccessful");
      return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    var result = utiles.insertNewUser(username, password, email, phone, false);
    msg.put("msg", result ? "success" : "unsuccessful");

    return new ResponseEntity<>(msg, HttpStatus.OK);

  }
}
