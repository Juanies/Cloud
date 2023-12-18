package sebas.juan.demo.helpers.Email;

import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

public class VerifyEmail {
    public static void main(String[] args) {
        Resend resend = new Resend("re_123456789");

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>").to("delivered@resend.dev")
                .subject("it works!").html("<strong>hello world</strong>").build();

        try {
            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
