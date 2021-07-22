import Model.Accounts;
import Service.CustomConnection;
import Service.DatabaseService;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {

    CustomConnection db = new CustomConnection();
    Accounts account = new Accounts(" ", 0.00, " ", 0);
    AccountsMenu menus = new AccountsMenu(account);
    DatabaseService dbService = new DatabaseService(db.Connect());
    dbService.closeDB();
    menus.menu();


  }
}
