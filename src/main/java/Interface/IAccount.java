package Interface;

import Model.Accounts;

import java.sql.Date;
import java.util.List;

public interface IAccount {
  //void Income(Accounts accounts);
  //void Expense(Accounts accounts);
  // List<List<String>> ViewAccounts(Accounts account,Date startDate, Date endDate );
  String InsertRecord(Accounts accounts);
  String DeleteRecord(Accounts accounts);
}
