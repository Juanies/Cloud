package sebas.juan.demo.helpers.Usuarios;

public class Usuario {
    
    private String name;
    private String password;
    private String tel;
    private String mail;
    private boolean email_is_verify;
    private int files;

    public Usuario(String name, String password, String tel, String mail, boolean email_is_verify,int files) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.mail = mail;
        this.email_is_verify = email_is_verify;
        this.files = files;
    }

// Subir fichero
// Borrar fichero
// Editar email
// editar contraseña
// editar telefonmo



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isEmail_is_verify() {
        return email_is_verify;
    }

    public void setEmail_is_verify(boolean email_is_verify) {
        this.email_is_verify = email_is_verify;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }



}
