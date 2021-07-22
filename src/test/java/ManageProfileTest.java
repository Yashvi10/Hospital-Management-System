import Model.User;
import Service.CustomConnection;
import Service.DatabaseService;
import Service.ManageProfile;
import Service.UserManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManageProfileTest {

    private static final String firstName="Elaine";
    private static final String lastName="MacDonald";
    private static final String address="50 Hamshore Street";
    private static final String phone="903458712";
    private static final String email="elaine@gmail.com";
    private static final String confirmEmail="elaine@gmail.com";
    private static final String pswd="macdonald";
    private static final String confirmPswd= "macdonald";
    private static final String role = "Patient";

    CustomConnection db=new CustomConnection();
    DatabaseService dbService=new DatabaseService(db.Connect());
    User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,pswd,confirmPswd );//,register  );
    UserManagement user =new UserManagement(dbService );
    ManageProfile profileObj=new ManageProfile(dbService);

    @Test
    public void updateProfileTest() {
        assertEquals(profileObj.updateProfile (myUser), "Record Updated");
    }

    @Test
    public void updatePasswordSame_Test () {
        assertEquals(profileObj.resetPassword(myUser), "Password Updated");
    }

}
