package sebas.juan.demo.helpers.Usuarios;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final UploadFileService uploadFileService;

    public FileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestAttribute("miCookieValor") String valorCookie,
            @RequestParam("files") List<MultipartFile> files) {
        uploadFileService.uploadFiles(files, getFilesFromUserService.getId(valorCookie));
        return ResponseEntity.ok("Archivos subidos y procesados con éxito.");
    }
}
