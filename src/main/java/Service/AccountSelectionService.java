package Service;

import Model.Accounts;

import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class highlights the Income and expense operation
 *                         to be performed such as insertion, deletion and viewing records
 * */

public class AccountSelectionService extends ManageAccountService {


  int accType;
  List<List<String>> rows;

  /*public int AccountType() {
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
    if (sc.hasNextInt()) {
      accType = sc.nextInt();
      sc.nextLine();
    }
    else {
      accType = -1;
      sc.next();
    }
    return accType;

  }*/

  public void ViewExpense(){
    rows= new ArrayList<>();
    rows=getExpenses();
  }

  public void ViewIncome( ){
    rows= new ArrayList<>();
    rows=getIncome();

 }

  public void SaveExpense(Accounts account) {
    addExpense(account);
  }

  public void Delete(Accounts account) {
    DeleteRecord(account);
  }
}
