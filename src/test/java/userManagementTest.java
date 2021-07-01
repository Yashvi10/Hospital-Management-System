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

    userManagement user=new userManagement(firstName,lastName,address,phone,email,
            confirmEmail,pswd,confirmPswd,role );

    @Test
    public void setFirstName_Test(){

        user.setFirstName(firstName);
        assertEquals(  "Margaret", user.getFirstName( ));
    }

    @Test
    public void setLastName_Test(){

        user.setLastName(lastName);
        assertEquals(  "Parker", user.getLastName( ));
    }
    @Test
    public void setAddress_Test(){

        user.setAddress(address);
        assertEquals(  "123 South Park", user.getAddress( ));
    }
    @Test
    public void setPhone_Test(){

        user.setPhone(phone);
        assertEquals(  "9024012987", user.getPhone( ));
    }

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
    public void setRole_Test(){

        user.setRole(role);
        assertEquals(  "Patient", user.getRole( ));
    }

    @Test
    public void RegisterUserTest(){
        assertEquals("User added",user.RegisterUser( ));
    }

    @Test
    public void RegisterUserCredTest(){
        assertEquals("User password created",user.RegisterUserCred( ));
    }

    @Test
    public void loginUserTest_True(){
        assertEquals("User password created",user.RegisterUserCred( ));
        assertTrue(user.loginUser(email, pswd), "Login successful");
    }

    @Test
    public void loginUserTest_False(){
        String email="parker@yahoo.com";
        assertEquals("User password created",user.RegisterUserCred( ));
        assertFalse (user.loginUser(email, pswd), "Login failed");
    }


}
