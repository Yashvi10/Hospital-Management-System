import org.junit.Test  ;
import static org.junit.jupiter.api.Assertions.*;

public class userManagementTest {
    private static final String firstName="Margaret";
    private static final String lastName="Parker";
    private static final String address="123 South Park";
    private static final String phone="9024012987";
    private static final String email="abimbola@gmail.com";
    private static final String confirmEmail="abimbola@gmail.com";
    private static final String pswd="Summer2021";
    private static final String confirmPswd=  "Summer2021";
    private static final String role="Patient";

     userManagement user=new userManagement(email,pswd);



    @Test
    public void setEmail_Test(){

        user.setEmail(email);
        assertEquals(  "abimbola@gmail.com", user.getEmail( ));
    }

    @Test
    public void setPswd_Test(){

        user.setPswd(pswd);
        assertEquals(  "Summer2021", user.getPswd( ));
    }

    @Test
    public void RegisterUserTest(){
        assertEquals("User added",user.RegisterUser(  firstName,  lastName,  address,  phone,   confirmEmail, confirmPswd,  role ));

    }

    @Test
    public void RegisterUserCredTest(){
        assertEquals("User password created",user.RegisterUserCred( role));
    }

    @Test
    public void loginUserTest_True(){

        assertTrue(user.loginUser(email, pswd), "Login successful");
    }

     @Test
    public void loginUserTest_False(){
        String email="parker@yahoo.com";
         assertFalse (user.loginUser(email, pswd), "Login failed");
    }


}
