package sebas.juan.demo.helpers.Usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sebas.juan.demo.helpers.Interceptor.Middleware;

@Configuration
public class Interceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(middleware())
                .addPathPatterns("/api/**"); // Ajusta el patrón según tus necesidades
    }

    @Bean
    public Middleware middleware() {
        return new Middleware();
    }
}
