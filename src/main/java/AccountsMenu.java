import Interface.FeatureMenu;
import Interface.IPrint;
import Model.Accounts;
import Service.CustomConnection;
import Service.DatabaseService;
import Service.ManageAccountService;

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

  CustomConnection conn = new CustomConnection();
  DatabaseService databaseService = new DatabaseService(conn.Connect());
  ManageAccountService manageAccountService = new ManageAccountService(databaseService);

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
          rows=manageAccountService.getIncome();
          printRecord(rows);
          break;
        case 2:
          do{
            System.out.println("Press 1: View Expenses\nPress 2: Add Expenses\nPress 3: Delete Expenses\nPress 0: Exit" );
            menuOption= sc.nextInt();
            switch(menuOption) {
              case 1:
                rows= manageAccountService.getExpenses();
                printRecord(rows);
                break;
              case 2:
                userInput();
                printOutput(manageAccountService.addExpense(account));
                break;
              case 3:System.out.println("Delete Expenses");
                printOutput(manageAccountService.DeleteRecord(account));
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

  public void userInput( ) {
    System.out.println("Provide Expense Payment Details\nEnter Payment Date: ");
    account.setDate(sc.nextLine());
    do{
      System.out.println("Enter Payment purpose\nPress 1 for Maintenance\nPress 2 for Supplies\nPress 3 for Salary" );
      account.setExpenseType(sc.nextInt());
    }
    while((sc.nextInt()!=1) &&(sc.nextInt()!=2)&&(sc.nextInt()!=3));
    System.out.println("Enter Receiver Name: ");
    account.setPayName(sc.nextLine());
    System.out.println("Enter Amount: ");
    account.setAmount(sc.nextDouble());
  }

}
