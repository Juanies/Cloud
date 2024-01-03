package sebas.juan.demo.helpers.Requests;

public class RequestUser {
  private String username;
  private String password;
  private String email;
  private String phone;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public RequestUser(String username, String password, String email, String phone) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
