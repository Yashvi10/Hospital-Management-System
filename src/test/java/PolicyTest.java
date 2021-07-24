import Service.PolicyLogic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolicyTest {

  PolicyLogic logic=new PolicyLogic();
  String[] policyPlan={"BRONZE", "SILVER", "GOLD"  };
  String[] singlePlan={"428.16", "982.00", "3091.00"  };

  @Test
  public void singlePolicyTest(){
    List<List<String>> expected= new ArrayList<>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(singlePlan[i]);
      expected.add(list);
    }
    assertEquals(expected,logic.singlePolicy());

  }

 /* @Test
  public void validateDateTest_True(){
    String plan ="30/09/2019";
    assertEquals(logic.coverageRate(dateFormat ));
    dateFormat ="6/9/2019";
    assertTrue(menu.validateDate(dateFormat ));
  }*/
}
