package Service;

import Model.Accounts;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class highlights the Income and expense operation
 *                         to be performed
 * */

public class AccountSelectionService extends ManageAccountService {

  Scanner sc = new Scanner(System.in);
  int accType;
 List<List<String>> rows;

  public int AccountType() {
    System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
    if (sc.hasNextInt()) {
      accType = sc.nextInt();
      sc.nextLine();
    }
    else {
      accType = -1;
      sc.next();
    }
    return accType;
  }

  public int MenuOption() {
    System.out.println("Press 1: ViewAll Records\nPress 2: Add New Record\nPress 3: Delete Record\nPress 0: Exit");
    if (sc.hasNextInt()) {
      accType = sc.nextInt();
      sc.nextLine();
    }
    else {
      accType = -1;
      sc.next();
    }
    return accType;

  }

  public void ViewExpense(){
    rows= new ArrayList<>();
    rows=getExpenses();
  }

  public void ViewIncome( ){
    rows= new ArrayList<>();
    rows=getIncome();

 }

  public void SaveIncome(Accounts account ) {
   putIncome(account);
  }
}
