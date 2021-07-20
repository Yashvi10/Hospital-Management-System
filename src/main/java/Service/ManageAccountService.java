package Service;

import Interface.IAccount;
import Model.Accounts;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: ManageAccountService.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages the connection to the accounts
 *  Description: This class accesses the database to view accounts,
 *               insert and delete record
 */

public class ManageAccountService implements IAccount {

  Statement statement = null;
  ResultSet resultSet = null;
  Connection conn;
  PreparedStatement insertAccounts;
  List<List<String>> dbRecord;

  ManageAccountService() {
  }

  public ManageAccountService(Connection conn) {
    this.conn = conn;
  }

  @Override
  public List<List<String>> getExpenses( ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      statement = conn.createStatement();
      resultSet = statement.executeQuery("order_id, name,qty, final_bill from order_items" );
      while (resultSet.next()) {
        dbRow = new ArrayList<>();
        dbRow.add(String.valueOf(resultSet.getInt("order_id")));
        dbRow.add(resultSet.getString("name"));
        dbRow.add(String.valueOf(resultSet.getInt("qty")));
        dbRow.add(String.valueOf(resultSet.getDouble("final_bill")));
        dbRecord.add(dbRow);
      }
    }
    catch (SQLException e) {
      e.getMessage();
    }

    return dbRecord;
  }

  @Override
  public List<List<String>> getIncome( ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      statement = conn.createStatement();
      resultSet = statement.executeQuery("select   accId , account_date, account_type , category ,  pay_name, " +
              "amount  from  accounts ");
      while (resultSet.next()) {
        dbRow = new ArrayList<>();
        dbRow.add(String.valueOf(resultSet.getInt("accId")));
        dbRow.add(String.valueOf(resultSet.getDate("account_date")));
        dbRow.add(resultSet.getString("account_type"));
        dbRow.add(resultSet.getString("category"));
        dbRow.add(resultSet.getString("pay_name"));
        dbRow.add(String.valueOf(resultSet.getDouble("amount")));
        dbRecord.add(dbRow);
      }
    }
    catch (SQLException e) {
      e.getMessage();
    }

    return dbRecord;
  }

  @Override
  public void putIncome(Accounts accounts) {
    String response = "";
    try {
      String queryUserTable = " insert into accounts( account_date,account_type , category ,  pay_name, " +
              " amount,status )   values( ?,?,?,?,? ,?)";
      insertAccounts = conn.prepareStatement(queryUserTable);
      insertAccounts.setDate(1, accounts.getDate());
      if (accounts.getAccountType() == 1) {
        insertAccounts.setString(2, "Income");
      } else if (accounts.getAccountType() == 2) {
        insertAccounts.setString(2, "Expense");
      }

      if (accounts.getExpenseType() == 1) {
        insertAccounts.setString(3, "Maintenance");
      } else if (accounts.getExpenseType() == 2) {
        insertAccounts.setString(3, "Supplies");
      } else if (accounts.getExpenseType() == 3) {
        insertAccounts.setString(3, "Salary");
      }

      insertAccounts.setString(4, accounts.getPayName());
      insertAccounts.setDouble(5, accounts.getAmount());
      insertAccounts.setString(6, "A");
      insertAccounts.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
  }

  @Override
  public String DeleteRecord(Accounts accounts) {
    String response = "";
    try {
      String queryUserTable = "update accounts set status='D' where account_date=? and account_type=? " +
              "  and pay_name=? and amount=? ";
      insertAccounts = conn.prepareStatement(queryUserTable);
      insertAccounts.setDate(1, accounts.getDate());
      if (accounts.getAccountType() == 1) {
        insertAccounts.setString(2, "Income");
      } else if (accounts.getAccountType() == 2) {
        insertAccounts.setString(2, "Expense");
      }

      insertAccounts.setDouble(3, accounts.getAmount());
      insertAccounts.executeUpdate();
      response = "Accounts Updated";
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return response;
  }
}
