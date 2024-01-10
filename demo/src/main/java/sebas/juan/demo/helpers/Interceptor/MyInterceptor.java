package sebas.juan.demo.helpers.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lógica a ejecutar antes de manejar la solicitud principal
        // Puedes acceder a request, response y otras clases según tus necesidades
        return true; // Si devuelve true, la solicitud continúa, de lo contrario, se detiene.
    }
}