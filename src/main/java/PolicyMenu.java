import Interface.FeatureMenu;
import Service.PolicyService;

import java.util.Scanner;

public class PolicyMenu extends PolicyService implements FeatureMenu {

  Scanner sc = new Scanner(System.in);

  @Override
  public void menu( ){

    System.out.println("******Policy Menu******");
    System.out.println("Press 1 for Policy\nPress 2 to Apply\nPress 3 for Claim\nPress 0 to Exit");
    int policyNo = sc.nextInt();
    switch(policyNo){
      case 1:
        System.out.println("******PER PERSON POLICY******");
        personCover();
        System.out.println("******PER FAMILY POLICY******");

    }
  }
}
