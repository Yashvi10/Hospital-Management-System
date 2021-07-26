package Interface;

import Model.Accounts;
import Service.CustomConnection;

import java.util.List;

public interface IAccount {
  boolean addExpense(CustomConnection con ,Accounts accounts );
  List<List<String>> getExpenses(CustomConnection con  );
  List<List<String>> getIncome(CustomConnection conn );
}
