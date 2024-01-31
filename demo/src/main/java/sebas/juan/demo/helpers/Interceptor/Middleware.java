package sebas.juan.demo.helpers.Interceptor;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sebas.juan.demo.helpers.Token.Token;
import sebas.juan.demo.helpers.Usuarios.*;
import sebas.juan.demo.helpers.Token.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Middleware implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String rawCookie = request.getHeader("Cookie");
        System.out.println(rawCookie);
        return false;
    }



	    @GetMapping("/read-spring-cookie")
	    public String readCookie(@CookieValue(name = "cookie") String cookieName) {
	        return String.format("value of the cookie with name user-id is: %s", cookieName);
	    }

}
