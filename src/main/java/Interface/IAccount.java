package Interface;

import Model.Accounts;
import java.sql.Date;
import java.util.List;

public interface IAccount {
  String DeleteRecord(Accounts accounts);
  void putIncome(Accounts accounts );
  List<List<String>> getExpenses( );
  List<List<String>> getIncome( );
}
