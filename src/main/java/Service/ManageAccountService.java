package Service;

import Model.Accounts;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: ManageAccountService.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages the connection to the accounts
 *  Description: This class accesses the database to view accounts,
 *               insert and delete record
 */

public class ManageAccountService {

  Statement statement =null;
  ResultSet resultSet=null;
  Connection conn;
  PreparedStatement insertAccounts;

  ManageAccountService(){}

  public ManageAccountService(Connection conn){
    this.conn=conn;
  }

  public List<List<String> > ViewAccounts(Date startDate,Date endDate ){
    List<String> dbRow;
    List<List<String> >  dbRecord=new ArrayList< >();
    try{
      statement =  conn.createStatement();
      resultSet = statement.executeQuery("select   accId , account_date, account_type , category ,  pay_name, " +
    "amount  from  accounts where account_date between "+startDate +" and "+endDate);
      while (resultSet.next()) {
        dbRow=new ArrayList<>();
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
      System.out.println("Record does not exist");
    }

    return dbRecord;
  }

  public String InsertRecord(Accounts accounts){
    String response="";
    try {
      String queryUserTable = " insert into accounts( account_date,account_type , category ,  pay_name, " +
    " amount,status )   values( ?,?,?,?,? ,?)";
      insertAccounts = conn.prepareStatement(queryUserTable);
      insertAccounts.setDate(1, accounts.getDate() );
      insertAccounts.setString(2, accounts.getAccountType() );
      insertAccounts.setString(3, accounts.getCategory() );
      insertAccounts.setString(4, accounts.getPayName() );
      insertAccounts.setDouble(5, accounts.getAmount() );
      insertAccounts.setString(6, "A" );
      insertAccounts.executeUpdate();
      response="Accounts Updated";
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    return response;
  }

  public String DeleteRecord(Accounts accounts){
    String response="";
    try {
      String queryUserTable = "update accounts set status='D' where account_date=? and account_type=? " +
      "  and pay_name=? and amount=? " ;
      insertAccounts = conn.prepareStatement(queryUserTable);
      insertAccounts.setDate(1, accounts.getDate() );
      insertAccounts.setString(2, accounts.getAccountType() );
      insertAccounts.setString(3, accounts.getCategory() );
      insertAccounts.setString(4, accounts.getPayName() );
      insertAccounts.setDouble(5, accounts.getAmount() );
      insertAccounts.executeUpdate();
      response="Accounts Updated";
    }
    catch (SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
    }
    return response;
  }

}
