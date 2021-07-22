import Model.Accounts;
import Service.CustomConnection;
import Service.DatabaseService;
import Service.ManageAccountService;

public class Main {

  public static void main(String[] args){

    Accounts account =new Accounts( "Killiam Rents",124.00,"06/01/2020",2);
    AccountsMenu menus=new AccountsMenu(account);

     CustomConnection db = new CustomConnection();
    DatabaseService dbService=new DatabaseService(db.Connect());
    ManageAccountService accountObj=new ManageAccountService(dbService);

    menus.menu();

  }
}
