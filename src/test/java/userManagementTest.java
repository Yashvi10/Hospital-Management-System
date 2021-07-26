import Service.ManageProfile;
import View.UserManagementPage;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Name of file: userManagementTest.java
 *  Author:  Abimbola Babalola
 *  Purpose: Testing
 *  Description: This class tests the business logic for the userManagement Features
 */

public class userManagementTest {

  ManageProfile profile=new ManageProfile();
  UserManagementPage user=new UserManagementPage();

  @Test
  public void validateEmailTest_True(){
    assertTrue(user.validateEmail("ronnie@dal.ca"))  ;
  }

  @Test
  public void validateEmailTest_False(){
    assertFalse(user.validateEmail("abimbola@dal@ca.com"))  ;
  }

  @Test
  public void encryptPassword_Test() throws NoSuchAlgorithmException {
    assertEquals("5f4dcc3b5aa765d61d8327deb882cf99",profile.encryptPassword("password"))  ;
  }

  @Test
  public void checkCredentialsTest_True() {
    assertTrue( profile.checkCredentials("abimbola@dal.ca","abimbola@dal.ca","password",
            "password"))  ;
  }

  @Test
  public void checkCredentialsTest_mismatchEmail()  {
    assertFalse( profile.checkCredentials("abimbola@dal.ca","abimbola"  ,"password",
            "password"))  ;
  }

  @Test
  public void checkCredentialsTest_mismatchPassword()  {
    assertFalse( profile.checkCredentials("abimbola@dal.ca","abimbola@dal.ca"  ,"password",
            "passwrod"))  ;
  }

}
