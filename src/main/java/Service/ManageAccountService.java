package Service;

import Interface.IAccount;
import Model.Accounts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: ManageAccountService.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages the connection to the accounts
 *  Description: This class accesses the database to view accounts,
 *               insert and delete record
 */

public class ManageAccountService  implements IAccount {

  Statement statement = null;
  ResultSet resultSet = null;
  DatabaseService dbService;
  PreparedStatement insertAccounts;
  List<List<String>> dbRecord;
  boolean response = false;
  Connection conn;

  public ManageAccountService() {
  }

  public ManageAccountService(DatabaseService dbService) {
    this.dbService = dbService;
    this.conn=dbService.conn;
  }

  @Override
  public List<List<String>> getIncome( ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      String query="select order_id, name,qty, final_bill from order_items;" ;
      resultSet=  dbService.executeQuery(query);
      while (resultSet.next()) {
        dbRow = new ArrayList<>();
        dbRow.add(String.valueOf(resultSet.getInt("order_id")));
        dbRow.add(resultSet.getString("name"));
        dbRow.add(String.valueOf(resultSet.getInt("qty")));
        dbRow.add(String.valueOf(resultSet.getDouble("final_bill")));
        dbRecord.add(dbRow);
      }
    }
    catch (SQLException | ClassNotFoundException e) {
      e.getMessage();
    }

    return dbRecord;
  }

  @Override
  public List<List<String>> getExpenses( ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      String query="select   accId , account_date, account_type , category ,  pay_name,  amount  from  accounts " ;
      resultSet=  dbService.executeQuery(query);
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
    catch (SQLException | ClassNotFoundException e) {
      e.getMessage();
    }

    return dbRecord;
  }

  @Override
  public boolean addExpense(Accounts accounts) {
    try {
      String queryUserTable = " insert into accounts( account_date,account_type , category ,  pay_name, " +
              " amount,status )   values( ?,?,?,?,? ,?)";
      insertAccounts =  dbService.conn.prepareStatement(queryUserTable);
      insertAccounts.setString(1, accounts.getDate() );
      insertAccounts.setString(2, "Expense");

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
      response=true;
    } catch (SQLException   e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return response;
  }

  @Override
  public boolean DeleteRecord(Accounts accounts) {
    try {
      String queryUserTable = "update accounts set status='D' where account_date=?  " +
              "  and pay_name=? and amount=? ";
      insertAccounts = dbService.conn.prepareStatement(queryUserTable);
      insertAccounts.setString(1, accounts.getDate());
      insertAccounts.setString(2, accounts.getPayName());
      insertAccounts.setDouble(3, accounts.getAmount());
      insertAccounts.executeUpdate();
      response = true;
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return response;
  }
}
