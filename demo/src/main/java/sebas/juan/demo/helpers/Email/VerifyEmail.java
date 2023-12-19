package sebas.juan.demo.helpers.Email;

import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

public class VerifyEmail {
    public static void SendEmail(String mail, int id) {
        Resend resend = new Resend("re_Qeapk38B_C7yGE151nTe3a9fcAWZSusC3");

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder().from("onboarding@resend.dev")
                .to(mail).subject("Hello World")
                .html("<p>Congrats on sending your <strong>first email</strong>!</p>").build();

        try {
            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            System.out.println("Correo enviado con exito");
        } catch (Exception e) {
            System.out.println("Error al enviar el correo");
            e.printStackTrace();
        }

    }

    public static StringBuilder generateToken() {
        int numeroLetras = 12;
        StringBuilder s = new StringBuilder(numeroLetras);

        for (int i = 0; i <= numeroLetras; i++) {
            // 97 A 122
            int nuevoCaracter = (int) (Math.random() * (122 - 65 + 1)) + 65;


            if ((nuevoCaracter >= '0' && nuevoCaracter <= '9')
                    || (nuevoCaracter >= 'a' && nuevoCaracter <= 'z')
                    || (nuevoCaracter >= 'A' && nuevoCaracter <= 'Z')) {
                s.append(nuevoCaracter);
                if (i % 2 == 0) {
                    char nuevaLetra = (char) nuevoCaracter;
                    s.append(nuevaLetra);
                }

            } else {
                i--;
            }



        }
        System.out.println(s);

        return s;
    }

}
