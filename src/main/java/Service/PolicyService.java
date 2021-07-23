package Service;

import java.util.ArrayList;
import java.util.List;

public class PolicyService {

  String[] policyPlan={"BRONZE", "SILVER", "GOLD", "PLATINUM" ,"DIAMOND"  };
  String[] singlePlan={"155", "320", "900", "3000" ,"5500"  };
  String[] familyPlan={"665", "1376", "3870", "7300" ,"11000"  };

  public PolicyService(){}

  public String coverageRate(String plan){
    String rate="";
    switch(plan){
      case "BRONZE":
        rate="50%" ;
        break;
      case "SILVER":
        rate="54%" ;
        break;
      case "GOLD":
        rate="68.5%" ;
        break;
      case "PLATINUM":
        rate="73%" ;
        break;
      case "DIAMOND":
        rate="85%" ;
        break;
      default:
        break;
    }
    return rate;
  }

  public List<List<String>> personCover(){
    List<List<String>> list=new ArrayList<>();
    for(List<String> row:personPlan( )){
      row.add(coverageRate(row.get(0)));
      list.add(row);
    }
    return list;
  }

  public List<List<String>> familyCover(){
    List<List<String>> list=new ArrayList<>();
    for(List<String> row:familyPlan( )){
      row.add(coverageRate(row.get(0)));
      list.add(row);
    }
    return list;
  }

  public List<List<String>> personPlan( ){
    List<List<String>> itemlist=new ArrayList<List<String>>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(singlePlan[i]);

      itemlist.add(list);
    }
    return itemlist;
  }

  public List<List<String>> familyPlan( ){
    List<List<String>> itemlist=new ArrayList<List<String>>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(familyPlan[i]);
      itemlist.add(list);
    }
    return itemlist;
  }

  public double proratedFee(double annualFee, int month) {
    return annualFee / month;
  }
}
