package sebas.juan.demo.helpers.Register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sebas.juan.demo.helpers.Requests.RequestUser;
import sebas.juan.demo.helpers.Usuarios.utiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    public static ResponseEntity<HashMap<String, Object>> SignUp(@RequestBody RequestUser user) {
        System.out.println("SingUP activo");
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
            valid = Pattern
                    .compile("(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
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
                    NewUser.insertNewUser(username, password, email, phone);


        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
