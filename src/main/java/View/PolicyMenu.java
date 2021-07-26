package View;

import Interface.FeatureMenu;
import Interface.IPrint;
import Service.CustomConnection;
import Service.PolicyService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*
 *  Name of file: PolicyMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the load page for the InsurancePolicy feature
 *  Description: This class is the wrapper for the service methods in the PolicyMenu.java service
 *
 */

public class PolicyMenu extends PolicyService implements FeatureMenu  , IPrint {

  String eligiblePlan;
  CustomConnection db= new CustomConnection();

  public PolicyMenu(){
  }

  @Override
  public void menu( ){
    Scanner sc = new Scanner(System.in);
    System.out.println("******Policy Menu******");
    int policyNo ;
    String choice;
    do {
      System.out.println("Press 1 to View Policy\nPress 2 to Apply\nPress 3 for Claim\nPress 0 to Exit");
      policyNo = sc.nextInt();
      switch (policyNo) {
        case 1:
          System.out.println("******INSURANCE POLICY******\nSingle Plan");
          viewAllPolicy();
          break;
        case 2:
          System.out.println("******BUY POLICY******");
          findPolicy();
          System.out.println("Your policy Number is: "+applyPolicy());
          break;
        case 3:
          printOutput(claimPolicy());
          break;
        default:
          break;
      }
    }while((policyNo!=1)&&(policyNo!=2)&&(policyNo!=3));

    menu( );
  }

  @Override
  public void printRecord(List<List<String>> rows) {
    for(List<String> record: rows){
      for(String col: record){
        System.out.print(col+" || ");
      }
      System.out.println( );
    }
  }

  @Override
  public void printOutput(boolean response) {
    if(response){
      System.out.println("Successful.");
    }
    else{
      System.out.println("Failed.");
    }

  }

  public void viewAllPolicy(){
    printRecord(viewSinglePolicy() );
    System.out.println("\nFamily Plan");
    printRecord(viewFamilyPolicy());
    System.out.println("\nAged Plan");
    printRecord(viewAgedPolicy());
    menu( );
  }

  public void findPolicy() {
    Scanner sc = new Scanner(System.in);
    int age;
    do {
      System.out.println("Enter your Age:");
      if (sc.hasNextInt()) {
        age = sc.nextInt();
        sc.nextLine();
      } else {
        age = -1;
        sc.next();
      }
    }
    while (age == -1);

    System.out.println("Enter Marital Status\nPress M for Married\nPress S for Single");
    String status = sc.nextLine();

    List<List<String>> value=validPolicy(age, status);
    List<String> index=value.get(3);
    for( String  item:index){
      eligiblePlan=item;
    }
    value.remove(3) ;
    System.out.println("*********Eligible Plan************");
    printRecord(value);

  }

  public int applyPolicy( ){
    List<String> info = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    System.out.println( );
    info.add( eligiblePlan )  ;
    String plan;
    do{
      System.out.println("For Policy Plan\nPress B for BRONZE\nPress S for SILVER\nPress G for GOLD");
      plan=sc.nextLine();
      if(plan.toUpperCase(Locale.ROOT).trim().equals("B")){
        info.add( "BRONZE");
      }
      else if(plan.toUpperCase(Locale.ROOT).trim().equals("S")){
        info.add( "SILVER");
      }
      else  {
        info.add( "GOLD");
      }
    }
    while((!plan.toUpperCase(Locale.ROOT).trim().equals("B"))
            &&(!plan.toUpperCase(Locale.ROOT).trim().equals("S"))&&(!plan.toUpperCase(Locale.ROOT).trim().equals("G")));

    System.out.println("Enter Patient ID:");
    Scanner scan=new Scanner(System.in);
    int id=scan.nextInt();
    info.add(String.valueOf(id)) ;
    return getPolicyNo(db,info);
  }

  public boolean claimPolicy( )   {
    List<String> info = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    System.out.println("\n*********Make Claim*********");
    String date ;
    boolean dateCheck ;
    do {
      System.out.println("Enter Date (dd/MM/yyyy):");
      date= sc.nextLine();
      dateCheck=validDate(date);
      if (dateCheck) {
        info.add(date);
      }
    }
    while(!dateCheck );

    System.out.println("Enter Patient ID:");
    info.add(sc.nextLine()) ;
    System.out.println("Enter Policy No:");
    info.add(sc.nextLine()) ;
    System.out.println("Enter Bill Amount");
    info.add( sc.nextLine())  ;
    return  policyClaim(db,info) ;
  }
}



