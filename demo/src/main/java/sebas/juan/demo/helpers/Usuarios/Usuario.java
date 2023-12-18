package sebas.juan.demo.helpers.Usuarios;

import java.beans.Statement;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.*;
import java.io.*;
import org.springframework.stereotype.Service;

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



    // Subir fichero
    // Iniciar sesion
    // Cerrar sesion
    // Borrar fichero
    // Editar email
    // editar contraseña
    // editar telefonmo

    // //puedo borrar esto?
    // public void uploadFile(String originalFileName) {
    //     LocalDateTime localDateTime = LocalDateTime.now();
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");
    //     String newFileName = localDateTime.format(formatter);

    //     System.out.println("Original file name: " + originalFileName);

    //     File oldFile = new File("F:/cloud/Cloud/SubidaFichero/" + originalFileName);
    //     File newFile = new File("F:/cloud/Cloud/usersFiles/" + newFileName);

    //     System.out.println("Relative path of original file: " + oldFile.getPath());

    //     try {
    //         if (oldFile.exists()) {
    //             if (oldFile.renameTo(newFile)) {
    //                 System.out.println("File has been renamed successfully.");
    //                 try {
    //                     Connection conn = utiles.connectDB();

    //                     String sql = "INSERT INTO user_file () VALUES (?, ?, ?)";

    //                     try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

    //                         preparedStatement.setInt(1, getId());
    //                         preparedStatement.setString(2, originalFileName);
    //                         preparedStatement.setString(3, newFileName);

    //                         int filasAfectadas = preparedStatement.executeUpdate();

    //                         if (filasAfectadas > 0) {
    //                             files++;
    //                             System.out.println("Fichero reflejado en la base de datos correctamente");
    //                         } else {
    //                             System.out.println("No se puedo reflejar el fichero en la base de datos correctamente.");
    //                         }
    //                     }
    //                 } catch (SQLDataException e) {
    //                     e.printStackTrace();
    //                 }

    //             } else {
    //                 System.out.println("Error: Could not rename the file.");
    //             }
    //         } else {
    //             System.out.println("Error: The original file does not exist.");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }



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
