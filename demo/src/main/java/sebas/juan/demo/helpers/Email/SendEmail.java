package sebas.juan.demo.helpers.Email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.resend.*;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import java.sql.*;
import sebas.juan.demo.helpers.Usuarios.utiles;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {


    public static void generateToken(int id, String mail) {
        Resend resend = new Resend("re_Qeapk38B_C7yGE151nTe3a9fcAWZSusC3");

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder().from("onboarding@resend.dev")
                .to(mail).subject("Hello World")
                .html("<p>Congrats on sending your <strong>first email</strong>!</p>").build();

        try {
           // SendEmailResponse data = resend.emails().send(sendEmailRequest);
            System.out.println("Correo enviado con exito");
        } catch (Exception e) {
            System.out.println("Error al enviar el correo");
            e.printStackTrace();
        }


        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-dd-MM-yyyy");
        String time = localDateTime.format(formatter);

        LocalDateTime localDateexpires = localDateTime.plusDays(1);
        String expires = localDateexpires.format(formatter);


        System.out.println(time);
        System.out.println(expires);

        int numeroLetras = 12;
        StringBuilder token = new StringBuilder(numeroLetras);

        for (int i = 0; i <= numeroLetras; i++) {
            int nuevoCaracter = (int) (Math.random() * (122 - 65 + 1)) + 65;

            if ((nuevoCaracter >= '0' && nuevoCaracter <= '9')
                    || (nuevoCaracter >= 'a' && nuevoCaracter <= 'z')
                    || (nuevoCaracter >= 'A' && nuevoCaracter <= 'Z')) {
                token.append(nuevoCaracter);
                if (i % 2 == 0) {
                    char nuevaLetra = (char) nuevoCaracter;
                    token.append(nuevaLetra);
                }

            } else {
                i--;
            }

        }

        String tokenGenerated = token.toString();
        Connection conn = utiles.connectDB();
        String sql = "INSERT INTO user_email() VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, tokenGenerated);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, expires);
            preparedStatement.executeUpdate();

            System.out.println("Subido a la base de datos");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión en el bloque finally
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }
}
