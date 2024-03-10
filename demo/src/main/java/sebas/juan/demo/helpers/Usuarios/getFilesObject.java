package sebas.juan.demo.helpers.Usuarios;

public class getFilesObject {
    private String id;
    private String filename;
    private String originalFileName;
    private String extension;


    public getFilesObject(String id, String filename, String originalFileName, String extension) {
        this.id = id;
        this.filename = filename;
        this.originalFileName = originalFileName;
        this.extension = extension;
    }




    public String getId() {
        return id;
    }




    public String getFilename() {
        return filename;
    }




    public String getOriginalFileName() {
        return originalFileName;
    }




    public String getExtension() {
        return extension;
    }

}
