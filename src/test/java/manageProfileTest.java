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
    @Test
    public void returnRecord_Test(){
        Map<Integer, List<String>> expectedMap=new HashMap<>();
        List<String> userArray=new ArrayList<>();
        userArray.add(firstName);
        userArray.add(lastName);
        userArray.add(address);
        userArray.add(phone);
        expectedMap.put(userid,userArray);
        assertEquals(  expectedMap,  profileObj.returnRecord( email));
    }
    @Test
    public void updateProfileTest() {
        assertEquals(profileObj.updateProfile(1, "Rebecca", "Lane", "65 Avenue Park" ,"9023468976"  ), "Record Updated");
    }
}
