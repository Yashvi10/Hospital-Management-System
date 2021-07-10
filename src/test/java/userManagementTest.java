import org.junit.Test  ;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userManagementTest {
     //  Sample Patient data 1
    /*private static final String firstName="Elaine";
    private static final String lastName="MacDonald";
    private static final String address="50 Hamshore Street";
    private static final String phone="903458712";
    private static final String email="elaine@gmail.com";
    private static final String confirmEmail="elaine@gmail.com";
    private static final String pswd="macdonald";
    private static final String confirmPswd= "macdonald";*/

  //  Sample Patient data 2
    /*private static final String firstName="Rebecca";
    private static final String lastName="Lane";
    private static final String address="123 South Avenue";
    private static final String phone="9026541289";
    private static final String email="rebecca@gmail.com";
    private static final String confirmEmail="rebecca@gmail.com";
    private static final String pswd="Summer";
    private static final String confirmPswd= "Summer";
    private static final int userid=2;*/


     // Sample Doctor data
     private static final String firstName="Ronnie";
    private static final String lastName="George";
    private static final String address="32 Robbie Street";
    private static final String phone="9020082786";
    private static final String email="ronnie@iwk.com";
    private static final String confirmEmail="ronnie@iwk.com";
    private static final String pswd="winter";
    private static final String confirmPswd= "winter";


    // Sample Nurse data
   /* private static final String firstName="Kathy";
    private static final String lastName="Raine";
    private static final String address="5672 Spring Garden road";
    private static final String phone="9029886545";
    private static final String email="kathy@iwk.com";
    private static final String confirmEmail="kathy@iwk.com";
    private static final String pswd="kathy";
    private static final String confirmPswd=  "kathy";
    private static final String role="Nurse";
    private static final int userid=2; */

    DbConnection db=new DbConnection();
    IRegistration register=new userManagement(db.Connect());
    User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,pswd,confirmPswd,register );
     userManagement user =new userManagement(db.Connect());

    @Test
    public void loadRecord_Test(){
       /* Map<Integer, List<String>> expectedMap=new HashMap<>();
        List<String> userArray=new ArrayList<>();
        userArray.add(firstName);
        userArray.add(lastName);
        userArray.add(address);
        userArray.add(phone);
        expectedMap.put(userid,userArray);
        assertEquals(  expectedMap,  user.loadRecord( myUser ));*/
        assertEquals(1 ,user.loadRecord(myUser ));
    }


    @Test
    public void registerNewLogin_Test(){
        assertEquals("Login added" ,user.registerLogin(myUser ));

    }
    @Test
    public void ExistingLogin_Test(){
        assertEquals("Username already exists" ,user.registerLogin(myUser ));

    }

    @Test
    public void registerPatient(){
        assertEquals("Patient record added" ,user.registerPatient(myUser ));

    }


    @Test
    public void registerStaff_Test(){
        String role="Doctor";
        assertEquals("Staff record added" ,user.registerStaff(role,myUser ));

    }

   /*  @Test
    public void loginUserTest_True(){

        assertTrue(user.loginUser(email, pswd), "Login successful");
    }

     @Test
    public void loginUserTest_False(){
        String email="parker@yahoo.com";
         assertFalse (user.loginUser(email, pswd), "Login failed");
    }*/


}
