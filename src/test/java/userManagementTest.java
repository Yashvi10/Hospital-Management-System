import org.junit.Test  ;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userManagementTest {
    private static final String firstName="Rebecca";
    private static final String lastName="Lane";
    private static final String address="65 Avenue Park";
    private static final String phone="9023468976";
    private static final String email="abimbola@gmail.com";
    private static final String confirmEmail="abimbola@gmail.com";
    private static final String pswd="Summer2021";
    private static final String confirmPswd=  "Summer2021";
    private static final String role="Patient";
    private static final int userid=1;

    DbConnection db=new DbConnection();
    IRegistration register=new userManagement(db.Connect());
    User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,pswd,confirmPswd,register );
     userManagement user =new userManagement(db.Connect());

    @Test
    public void loadRecord_Test(){
        Map<Integer, List<String>> expectedMap=new HashMap<>();
        List<String> userArray=new ArrayList<>();
        userArray.add(firstName);
        userArray.add(lastName);
        userArray.add(address);
        userArray.add(phone);
        expectedMap.put(userid,userArray);
        assertEquals(  expectedMap,  user.loadRecord( myUser ));
    }

   /* @Test
    public void RegisterUserTest(){
        assertEquals("User added",user.RegisterUser(  firstName,  lastName,  address,  phone,   confirmEmail, confirmPswd,  role ));

    }*/

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
