import Service.ManageProfile;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

public class userManagementTest {

  ManageProfile profile=new ManageProfile();

  @Test
  public void validateEmailTest_True(){
    assertTrue(profile.validateEmail("ronnie@dal.ca"))  ;
  }

  @Test
  public void validateEmailTest_False(){
    assertFalse(profile.validateEmail("abimbola@dal@ca.com"))  ;
  }

  @Test
  public void encryptPassword_Test() throws NoSuchAlgorithmException {
    assertEquals("5f4dcc3b5aa765d61d8327deb882cf99",profile.encryptPassword("password"))  ;
  }

  @Test
  public void checkCredentialsTest_True() throws NoSuchAlgorithmException {
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
