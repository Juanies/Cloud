package sebas.juan.demo.helpers.Usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final UploadFileService uploadFileService;

    public FileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") int id, @RequestParam("originalFileName") String originalFileName) {
        uploadFileService.uploadFile(file, id, originalFileName);
        return ResponseEntity.ok("Archivo subido y procesado con éxito.");
    }
}
