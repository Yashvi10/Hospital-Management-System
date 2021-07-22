package Service;

public class LoginService {

  public boolean validateEmail(String email){
    String regex= "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    return email.matches(regex);
  }

}
