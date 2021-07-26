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

  ResultSet resultSet = null;
  PreparedStatement insertAccounts;
  List<List<String>> dbRecord;
  boolean response = false;
  Connection conn;
  Statement statement;

  public ManageAccountService() {
  }

  //public ManageAccountService(CustomConnection conn) {
  //this.conn= conn.Connect();
  //}

  @Override
  public List<List<String>> getIncome( CustomConnection con  ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      conn=con.Connect();
      String query="select order_id, name,qty, final_bill from order_items;" ;
      statement=conn.createStatement();
      resultSet=  statement.executeQuery(query);
      while (resultSet.next()) {
        dbRow = new ArrayList<>();
        dbRow.add(String.valueOf(resultSet.getInt("order_id")));
        dbRow.add(resultSet.getString("name"));
        dbRow.add(String.valueOf(resultSet.getInt("qty")));
        dbRow.add(String.valueOf(resultSet.getDouble("final_bill")));
        dbRecord.add(dbRow);
      }
    }
    catch (SQLException  e) {
      e.getMessage();
    }
    finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        resultSet = null;
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }
    }

    return dbRecord;
  }

  //@Override
  public List<List<String>> getExpenses(CustomConnection con ) {
    List<String> dbRow;
    dbRecord = new ArrayList<>();
    try {
      conn=con.Connect();
      String query="select   accId , account_date, category ,  pay_name,  amount  from  ExpenseAccounts " ;
      statement=conn.createStatement();
      resultSet=  statement.executeQuery(query);
      while (resultSet.next()) {
        dbRow = new ArrayList<>();
        dbRow.add(String.valueOf(resultSet.getInt("accId")));
        dbRow.add( resultSet.getString("account_date")) ;
        dbRow.add(resultSet.getString("category"));
        dbRow.add(resultSet.getString("pay_name"));
        dbRow.add(String.valueOf(resultSet.getDouble("amount")));
        dbRecord.add(dbRow);
      }
    }
    catch (SQLException  e) {
      e.getMessage();
    }
    finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        resultSet = null;
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }

    }

    return dbRecord;
  }

  @Override
  public boolean addExpense(CustomConnection con ,Accounts accounts ) {
    try {
      conn=con.Connect();
      String expenseType ;
      if(accounts.getExpenseType()==1){
        expenseType="Maintenance";
      }
      else if(accounts.getExpenseType()==2){
        expenseType="Supplies";
      }
      else {
        expenseType="Salary";
      }
      String queryUserTable = " insert into ExpenseAccounts(account_date, category,pay_name,amount ) " +
              "select ?,?, ?, ?  from dual  where not exists (select * from ExpenseAccounts where account_date='"+
              accounts.getDate()+"' and category='"+expenseType+"' and pay_name='"+accounts.getPayName()+
              "' and amount= "+accounts.getAmount()+" );";

      insertAccounts =  conn.prepareStatement(queryUserTable);
      insertAccounts.setString(1, accounts.getDate() );
      insertAccounts.setString(2, expenseType);
      insertAccounts.setString(3, accounts.getPayName());
      insertAccounts.setDouble(4, accounts.getAmount());
      insertAccounts.executeUpdate();
      response=true;
    }
    catch (SQLException   e) {
      System.out.println(  e.getMessage());
    }
    finally{
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }
    }

    return response;
  }
}
