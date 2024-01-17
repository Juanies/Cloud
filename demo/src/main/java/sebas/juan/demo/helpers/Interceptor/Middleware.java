package sebas.juan.demo.helpers.Interceptor;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sebas.juan.demo.helpers.Token.Token;

public class Middleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cookie".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (Token.validateToken(token)) {
                        String username = Token.getName(token);

                        // Almacena el nombre de usuario en el atributo de la solicitud
                        request.setAttribute("username", username);

                        return true; // Continúa con la ejecución del controlador
                    }
                }
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autenticado");
        return false;
    }




    private String obtenerUsernameDesdeToken(String token) {
        return Token.getName(token);
    }

}
