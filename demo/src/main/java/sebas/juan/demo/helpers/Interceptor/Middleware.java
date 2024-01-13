package sebas.juan.demo.helpers.Interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Middleware implements ClientHttpRequestInterceptor {

    private static final ThreadLocal<String> usernameThreadLocal = new ThreadLocal<>();

    public static void setUserName(String username) {
        usernameThreadLocal.set(username);
    }

    public static String getUserName() {
        return usernameThreadLocal.get();
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        // Obtener el nombre de usuario de la cookie (este es solo un ejemplo, ajusta según tus necesidades)
        String username = extractUsernameFromCookie(request.getHeaders());

        // Almacenar el nombre de usuario en el ThreadLocal
        setUserName(username);

        // Continuar con la ejecución de la solicitud original
        return execution.execute(request, body);
    }

    // Método de ejemplo para obtener el nombre de usuario de la cookie
    private String extractUsernameFromCookie(HttpHeaders headers) {
        List<String> cookies = headers.get("Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                // Aquí debes implementar la lógica específica para extraer el nombre de usuario de la cookie
                // Por ejemplo, usando bibliotecas de manejo de cookies o patrones de búsqueda
                // Este es solo un ejemplo básico
                if (cookie.contains("username=")) {
                    return extractValueFromCookie(cookie, "username");
                }
            }
        }
        return null;
    }

    // Método de ejemplo para extraer el valor de una cookie específica
    private String extractValueFromCookie(String cookie, String key) {
        String[] cookieParts = cookie.split("; ");
        for (String part : cookieParts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2 && keyValue[0].equals(key)) {
                return keyValue[1];
            }
        }
        return null;
    }
}