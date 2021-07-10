import org.junit.Test  ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class manageProfileTest {


   /* private static final int userid=1;
    private static final String firstName="Margaret";
    private static final String lastName="Parker";
    private static final String address="123 South Park";
    private static final String phone="9024012987";
    private static final String email="abimbola@gmail.com";
    private static final String pswd="Summer"  ;*/

    //update test data
    private static final String firstName="Elaine";
    private static final String lastName="MacDonald";
    private static final String address="50 Hamshore Street";
    private static final String phone="903458712";
    private static final String email="elaine@gmail.com";
    private static final String confirmEmail="elaine@gmail.com";
    private static final String pswd="macdonald";
    private static final String confirmPswd= "macdonald";

    DbConnection db=new DbConnection();
    IRegistration register=new userManagement(db.Connect());
    User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,pswd,confirmPswd,register  );
    userManagement user =new userManagement(db.Connect());
    manageProfile profileObj=new manageProfile(db.Connect());

    @Test
    public void updateProfileTest() {
        assertEquals(profileObj.updateProfile (myUser), "Record Updated");
    }

    @Test
    public void updatePasswordSame_Test () {
        assertEquals(profileObj.resetPassword(myUser), "Password Updated");
    }

    @Test
    public void updatePasswordDiff_Test () {
        assertEquals(profileObj.resetPassword(myUser ),  "Error: Passwords do not match");
    }
}
