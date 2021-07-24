package Service;

import Interface.IDateValidation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
 *  Name of file: PolicyLogic.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the business logic layer for the feature
 *  Description: This class implements the workings of the feature. it handles the recommendation of
 *        policies, calculation of premium and claim rates
 *
 */

public class PolicyLogic  implements IDateValidation {

  String[] policyPlan={"BRONZE", "SILVER", "GOLD"  };
  String[] familyPlan={"665.00", "1376.65", "3870.98"  };
  String[] singlePlan={"428.16", "982.00", "3091.00"  };
  String[] agedPlan={"332.02", "654.00", "2899.00"  };

  public PolicyLogic( ){
  }

  @Override
  public boolean validateDate(String date){
    boolean dateCheck=false;
    if(!date.trim().equals("")){
      SimpleDateFormat format=new SimpleDateFormat( "dd/MM/yyyy");
      format.setLenient(false);
      try{
        Date recordDate=format.parse(date);
        dateCheck=true;
      }
      catch( ParseException e){
        e.getMessage();
      }
    }
    return dateCheck;
  }

  public List<List<String>> getPolicy(int age, String status){
    List<List<String>> policy = null;
     if (age<65){
       switch (status.toUpperCase().trim()){
         case "M":
           policy=familyPolicy();
           break;
         case "S":
           policy=singlePolicy();
           break;
         default:
           break;
       } 
     }
    else {
      policy=agedPolicy();
    }
    return policy;
  }

  public List<List<String>> agedPolicy( ){
    List<List<String>> itemlist= new ArrayList<>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(agedPlan[i]);
      itemlist.add(list);
    }
    list=new ArrayList<>();
    list.add("1");
    itemlist.add(list);
    return itemlist;
  }

  public List<List<String>> singlePolicy( ){
    List<List<String>> itemlist= new ArrayList<>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(singlePlan[i]);
      itemlist.add(list);
    }
    list=new ArrayList<>();
    list.add("2");
    itemlist.add(list);
    return itemlist;
  }

  public List<List<String>> familyPolicy( ){
    List<List<String>> itemlist= new ArrayList<>();
    List<String> list;
    for(int i=0; i<policyPlan.length;i++){
      list=new ArrayList<>();
      list.add(policyPlan[i]);
      list.add(familyPlan[i]);
      itemlist.add(list);
    }
    list=new ArrayList<>();
    list.add("3");
    itemlist.add(list);
    return itemlist;
  }
  
  public double policyRate(String plan){
    double cover = 0;
    switch(plan.toUpperCase(Locale.ROOT).trim()){
      case "BRONZE":
        cover=0.55;
        break;
      case "SILVER":
        cover=0.72;
        break;
      case "GOLD":
        cover=0.97;
        break;
      default:
        break; 
    }
    return  cover;

  }

  public double getPremium(String planid,String plan ){
    Double policyValue=0.00;
    if(planid.equals("1")){
      switch(plan.toUpperCase(Locale.ROOT).trim()){
        case "BRONZE":
          policyValue=Double.valueOf(agedPlan[0]);
          break;
        case "SILVER":
          policyValue=Double.valueOf(agedPlan[1]);
          break;
        case "GOLD":
          policyValue=Double.valueOf(agedPlan[2]);
          break;
        default:
          break;
      }
    }
    else if(planid.equals("2")){
      switch(plan.toUpperCase(Locale.ROOT).trim()){
        case "BRONZE":
          policyValue=Double.valueOf(singlePlan[0]);
          break;
        case "SILVER":
          policyValue=Double.valueOf(singlePlan[1]);
          break;
        case "GOLD":
          policyValue=Double.valueOf(singlePlan[2]);
          break;
        default:
          break;
      }
    }
    else{
      switch(plan.toUpperCase(Locale.ROOT).trim()){
        case "BRONZE":
          policyValue= Double.valueOf(familyPlan[0]);
          break;
        case "SILVER":
          policyValue=Double.valueOf(familyPlan[1]);
          break;
        case "GOLD":
          policyValue=Double.valueOf(familyPlan[2]);
          break;
        default:
          break;
      }
    }
    return policyValue;
  }
}
