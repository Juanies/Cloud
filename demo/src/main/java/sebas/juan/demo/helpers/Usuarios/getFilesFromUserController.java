package sebas.juan.demo.helpers.Usuarios;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
  import org.springframework.context.annotation.Configuration;

  
@RestController
@RequestMapping("/api/files")
public class getFilesFromUserController {
  
  @PostMapping("/getfiles?")
  public ArrayList<HashMap<String, Object>> getFiles() {
    return getFilesFromUserService.getFiles("7");
  }


  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/api/**")
                  .allowedOrigins("http://localhost:5174") // Add your frontend URL
                  .allowedMethods("GET", "POST", "PUT", "DELETE")
                  .allowCredentials(true);
      }
  }
}
