import Interface.IRegistration;
import Model.User;
import Service.UserManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class userManagementTest {

    // Sample Nurse data
    private static final String firstName="Kathy";
    private static final String lastName="Raine";
    private static final String address="5672 Spring Garden road";
    private static final String phone="9029886545";
    private static final String email="kathy@iwk.com";
    private static final String confirmEmail="kathy@iwk.com";
    private static final String pswd="kathy";
    private static final String confirmPswd=  "kathy";
    private static final String role="Nurse";
    private static final int userid=1;

    DbConnection db=new DbConnection();
    IRegistration register=new UserManagement(db.Connect());
    User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,pswd,confirmPswd,register );
    UserManagement user =new UserManagement(db.Connect());

    @Test
    public void loadRecord_Test(){
        User userobj = new User();
        userobj.setEmail("ronnie@iwk.com");
        assertEquals(3 ,user.loadRecord(userobj ));
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
        myUser.setUserid(user.getLastUserId());
        user = new UserManagement(db.Connect());
        assertEquals("Patient record added" ,user.registerPatient(myUser ));

    }


    @Test
    public void registerStaff_Test(){
        String role="Doctor";
        myUser.setUserid(user.getLastUserId());
        assertEquals("Staff record added" ,user.registerStaff(role,myUser ));

    }

    @Test
    public void loginUserTest_True(){

        assertTrue(user.loginUser(myUser), "Login successful");
    }

    @Test
    public void loginUserTest_False(){
        User userobj = new User();
        userobj.setEmail("null@dal.ca");
        userobj.setPswd("hello");
        assertFalse (user.loginUser(userobj), "Login failed");
    }

}
