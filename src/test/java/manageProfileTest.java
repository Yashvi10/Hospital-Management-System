import org.junit.Test  ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class manageProfileTest {
    DbConnection db=new DbConnection();
    manageProfile profileObj=new manageProfile(db.Connect());
    private static final int userid=1;
    private static final String firstName="Margaret";
    private static final String lastName="Parker";
    private static final String address="123 South Park";
    private static final String phone="9024012987";
    private static final String email="abimbola@gmail.com";
    private static final String pswd="Summer"  ;


    @Test
    public void updateProfileTest() {
        assertEquals(profileObj.updateProfile(1, "Rebecca", "Lane", "65 Avenue Park" ,"9023468976"  ), "Record Updated");
    }

    @Test
    public void updatePasswordSame_Test () {
        assertEquals(profileObj.resetPassword(1,  email, pswd,pswd  ), "Password Updated");
    }

    @Test
    public void updatePasswordDiff_Test () {
        assertEquals(profileObj.resetPassword(1,  email, pswd, "Winter" ),  "Error: Passwords do not match");
    }
}
