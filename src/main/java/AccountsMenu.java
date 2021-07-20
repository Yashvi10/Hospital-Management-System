import Interface.FeatureMenu;
import Service.AccountSelectionService;
import Service.ManageAccountService;

import java.util.Scanner;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class implement the FeatureMenu and is responsible for showing
 *                   different menus related to Accounts module. It implements the single responsibility
 *                   principle by extending the AccountSelectionService class.
 * */

public class AccountsMenu extends AccountSelectionService  implements FeatureMenu {

  Scanner sc = new Scanner(System.in);
  Scanner scanner = new Scanner(System.in);

  @Override
  public void menu() {
    int menuOption;
    int accType = 0;
    System.out.println("******Accounts Menu******");
    do {
      accType = AccountType();
      menuOption = MenuOption();
        switch (accType) {
        case 1:
          switch(menuOption) {
            case 1:
              ViewExpense();
              break;
            case 2:
              ViewIncome();
              break;
            case 3:
            case 4:
            case 5:
            default:
              System.out.println("Invalid choice.");
              break;
          }
        case 2:
          switch(menuOption) {
            case 1:
              ViewIncome();
              break;
            case 2:
              ViewIncome();
              break;
            default:
              System.out.println("Invalid choice.");
              break;
          }

          //     System.out.println("Press 1: ViewAll Records\nPress 2: View Period Records\nPress 3: Add New Record" +
          //   "\nPress 4: Delete Record\nPress 5: Change Account Type\nPress 0: Exit");
          //     selection = sc.nextInt();

      }

    } while (accType != 0) ;

  }

}
