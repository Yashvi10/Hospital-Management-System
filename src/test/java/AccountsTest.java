import Model.Accounts;
import Service.CustomConnection;
import Service.DatabaseService;
import Service.ManageAccountService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountsTest {

  Accounts account =new Accounts( "Killiam Rents",124.00,"06/01/2020",2);
  CustomConnection db = new CustomConnection();
  DatabaseService dbService=new DatabaseService(db.Connect());
  ManageAccountService accountObj=new ManageAccountService(dbService);

   @Test
    public void getIncomeTest() {
    assertEquals( 6, accountObj.getIncome().size());
  }

   @Test
    public void addExpenseTest() {

     assertTrue( accountObj.addExpense(account));
  }

  @Test
  public void closeDBTest() throws SQLException {

    assertTrue( dbService.closeDB( ));
  }



}

