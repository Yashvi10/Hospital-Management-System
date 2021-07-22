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

  public AccountsMenu(Accounts account ){
    this.account=account;
  }

  @Override
  public void menu( ) {
    int menuOption ;
    int accType  ;
    List<List<String>> rows;

    System.out.println("******Accounts Menu******");
    do {
      System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
      accType = sc.nextInt();
      switch (accType) {
        case 1:
          rows= new ArrayList<>();
          rows=getIncome();
          printRecord(rows);
          break;
        case 2:
          do{
          System.out.println("Press 1: View Expenses\nPress 2: Add Expenses\nPress 3: Delete Expenses\nPress 0: Exit" );
          menuOption= sc.nextInt();
          switch(menuOption) {
            case 1:
              rows= getExpenses();
              printRecord(rows);
              break;
            case 2:
              printOutput(addExpense(account));
              break;
            case 3:System.out.println("Delete Expenses");
              printOutput(DeleteRecord(account));
              break;
            case 0:
            default:
              break;}
          }
          while(menuOption!=0);

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
