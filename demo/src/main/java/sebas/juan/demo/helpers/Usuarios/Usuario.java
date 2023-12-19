package sebas.juan.demo.helpers.Usuarios;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.*;

public class Usuario {

    private int id;
    private String name;
    private String password;
    private String tel;
    private String mail;
    private boolean email_is_verify;
    private int files;


    public Usuario(int id, String name, String password, String tel, String mail,
            boolean email_is_verify, int files) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.mail = mail;
        this.email_is_verify = email_is_verify;
        this.files = files;
    }


    public static boolean login(String usuario, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        Connection connection = utiles.connectDB();
        boolean login = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (login = resultSet.next()) {
                        System.out.println("Acces Confirmed");
                }else{
                    System.out.println("Acces Denied ");
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return login;
    }


    // Subir fichero
    // Iniciar sesion
    // Cerrar sesion
    // Borrar fichero
    // Editar email
    // editar contraseña
    // editar telefonmo

    // //puedo borrar esto?
    // public void uploadFile(String originalFileName) {
    // LocalDateTime localDateTime = LocalDateTime.now();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");
    // String newFileName = localDateTime.format(formatter);

    // System.out.println("Original file name: " + originalFileName);

    // File oldFile = new File("F:/cloud/Cloud/SubidaFichero/" + originalFileName);
    // File newFile = new File("F:/cloud/Cloud/usersFiles/" + newFileName);

    // System.out.println("Relative path of original file: " + oldFile.getPath());

    // try {
    // if (oldFile.exists()) {
    // if (oldFile.renameTo(newFile)) {
    // System.out.println("File has been renamed successfully.");
    // try {
    // Connection conn = utiles.connectDB();

    // String sql = "INSERT INTO user_file () VALUES (?, ?, ?)";

    // try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

    // preparedStatement.setInt(1, getId());
    // preparedStatement.setString(2, originalFileName);
    // preparedStatement.setString(3, newFileName);

    // int filasAfectadas = preparedStatement.executeUpdate();

    // if (filasAfectadas > 0) {
    // files++;
    // System.out.println("Fichero reflejado en la base de datos correctamente");
    // } else {
    // System.out.println("No se puedo reflejar el fichero en la base de datos correctamente.");
    // }
    // }
    // } catch (SQLDataException e) {
    // e.printStackTrace();
    // }

    // } else {
    // System.out.println("Error: Could not rename the file.");
    // }
    // } else {
    // System.out.println("Error: The original file does not exist.");
    // }
    // } catch (Exception e) {
    // System.out.println("Error: " + e.getMessage());
    // }



    // }


    // public void delFile() {

    // }

    // public void editMial() {

    // }

    // public void editPassword() {

    // }

    // public void editTel() {

    // }

    public void register() {

    }


    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", name=" + name + ", password=" + password + ", tel=" + tel
                + ", mail=" + mail + ", email_is_verify=" + email_is_verify + ", files=" + files
                + "]";
    }



}
