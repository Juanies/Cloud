package sebas.juan.demo.helpers.Usuarios;

import java.sql.*;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

public class Usuario {

    private int id;
    private String name;
    private String password;
    private String tel;
    private String mail;
    private boolean email_is_verify;
    private int files;


    public Usuario(String name, String password, String tel, String mail) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.mail = mail;
    }

    public static void changeNameFile(String randomName, String newName, int id) {
        // Busco el nombre del fichero
        String sqlRename = "UPDATE user_file SET filename = ? WHERE filename = ? AND id = ?";

        Connection connection = utiles.connectDB();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRename);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, randomName);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Se actualizó el filename exitosamente en el mismo registro.");
            } else {
                System.out.println("No se encontró ninguna fila para actualizar con el original_filename proporcionado.");
            }
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static String getid(String username){
        String sql = "SELECT id FROM usuarios WHERE nombre = ?";
        String id = null;
        Connection connection = utiles.connectDB();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(0, username);
            try{
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getString("id");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return id;
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
                if (resultSet.next()) {
                    // Comprueba si el nombre de usuario y la contraseña coinciden
                    if (usuario.equals(resultSet.getString("nombre")) && contraseña.equals(resultSet.getString("contraseña"))) {
                        login = true;
                        System.out.println("Acces Confirmed");
                    } else {
                        login = false;
                        System.out.println("Acces Denied ");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return login;
    }


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
