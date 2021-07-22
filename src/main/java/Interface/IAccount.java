package Interface;

import Model.Accounts;

import java.util.List;

public interface IAccount {
  boolean addExpense(Accounts accounts );
  List<List<String>> getExpenses( );
  List<List<String>> getIncome( );
}
