import Interface.FeatureMenu;
import Interface.IPrint;
import Model.Accounts;
import Service.ManageAccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class is a presentation layer. It implement the FeatureMenu and is responsible for
 *               showing different menus related to Accounts module. It uses the IPrint interface to present its
 *               output to the userIt implements the single responsibility principle by extending the
 *               ManageAccountService class.
 * */

public class AccountsMenu extends ManageAccountService implements FeatureMenu, IPrint {

  Scanner sc = new Scanner(System.in);

  Accounts account;
  AccountsMenu(Accounts account ){
    this.account=account;
  }

  @Override
  public void menu( ) {
    int menuOption=0;
    int accType =0;
    List<List<String>> rows;

    System.out.println("******Accounts Menu******");
    //System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
    //accType = sc.nextLine();
    //System.out.println("Press 1: View Expenses\nPress 2: Add Expenses\nPress 3: Delete Expenses ");

    do {
      System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
      accType = sc.nextInt();
      System.out.println("Press 1: View Expenses\nPress 2: Add Expenses\nPress 3: Delete Expenses ");
      menuOption= sc.nextInt();
      //accType = AccountType();
      switch (accType) {
        case 1:
          //ViewIncome ();
          rows= new ArrayList<>();
          rows=getIncome();
          printRecord(rows);
          break;
        case 2:
          //menuOption = MenuOption();
          switch(menuOption) {
            case 1:
              //ViewExpense();
              rows= new ArrayList<>();
              rows= getExpenses();
              printRecord(rows);
              break;
            case 2:
              //SaveExpense(account);
              printOutput(addExpense(account));
              break;
            case 3:
              //Delete(account);
              printOutput(DeleteRecord(account));
              break;
            default:
              break;
          }
        case 0:
        default:
          break;
      }

    } while (accType != 0) ;

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

  @Override
  public void printRecord(List<List<String>> rows) {
    for(List<String> record: rows){
      for(String col: record){
        System.out.print(col+" ");
      }
      System.out.println( );
    }
  }

}
