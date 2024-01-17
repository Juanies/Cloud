package sebas.juan.demo.helpers.Interceptor;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sebas.juan.demo.helpers.Token.Token;

public class Middleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        System.out.println("Cookies: " + Arrays.toString(cookies));

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autenticado");
        return false;
    }




    private String obtenerUsernameDesdeToken(String token) {
        return Token.getName(token);
    }

}
