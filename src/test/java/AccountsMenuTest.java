import Model.Accounts;
import Service.CustomConnection;
import Service.DatabaseService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  Name of file: AccountsMenuTest.java
 *  Author:  Abimbola Babalola
 *  Purpose: Testing
 *  Description: This class tests the format of date used in the Accounts table
 */

public class AccountsMenuTest {
  CustomConnection db = new CustomConnection();
  Accounts account = new Accounts(" ", 0.00, " ", 0);
  AccountsMenu menu = new AccountsMenu(account);
  DatabaseService dbService = new DatabaseService(db.Connect());

  @Test
  public void validateDateTest_True(){
    String dateFormat ="30/09/2019";
    assertTrue(menu.validateDate(dateFormat ));
    dateFormat ="6/9/2019";
    assertTrue(menu.validateDate(dateFormat ));
  }

  @Test
  public void validateDateTest_False(){
    String dateFormat ="06/30/2019";
    assertFalse(menu.validateDate(dateFormat ));
  }
  
}
