package sebas.juan.demo.helpers.Interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import sebas.juan.demo.helpers.Token.Token;
import sebas.juan.demo.helpers.Usuarios.getFilesFromUserService;

public class Middleware extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("miCookie")) {
                    String valorCookie = cookie.getValue();
                    if (Token.valid(valorCookie)) {
                        request.setAttribute("miCookieValor", Token.getSubject(valorCookie));
                        return true;
                    }
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
