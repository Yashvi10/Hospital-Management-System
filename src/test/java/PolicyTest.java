import Service.PolicyLogic;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: PolicyTest.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the test class for the InsurancePolicy feature
 *  Description: This class tests the functionality of the business logic for the feature
 *
 */

public class PolicyTest {

  PolicyLogic logic=new PolicyLogic();
  String[] policyPlan={"BRONZE", "SILVER", "GOLD"  };
  String[] singlePlan={"428.16", "982.00", "3091.00"  };
  List<List<String>> expected= new ArrayList<>();

  @Test
  public void singlePolicyTest(){
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(singlePlan[i]);
      expected.add(list);
    }
    list=new ArrayList<>();
    list.add("2");
    expected.add(list);
    assertEquals(expected,logic.singlePolicy());
  }

  @Test
  public void getPremiumTest(){
    List<List<String>> expected= new ArrayList<>();
    List<String> list;
    assertEquals(982.00,logic.getPremium("2","SILVER"));

  }

  @Test
  public void getPolicyTest(){
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(singlePlan[i]);
      expected.add(list);
    }
    list=new ArrayList<>();
    list.add("2");
    expected.add(list);
    assertEquals(expected,logic.getPolicy(50,"S"));

  }
}
