package Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
 *  Name of file: PolicyService.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the data persistence layer for the feature
 *  Description: This class handles the interaction with the database
 *
 */

public class PolicyService extends PolicyLogic{

  ResultSet resultSet;
  PreparedStatement insertAccounts;
  CustomConnection db=new CustomConnection();
  DatabaseService dbService =new DatabaseService(db.Connect());

  public PolicyService( ){}

  public List<List<String>> viewSinglePolicy(){
    return singlePolicy();
  }

  public List<List<String>> viewFamilyPolicy(){
    return familyPolicy();
  }

  public List<List<String>> viewAgedPolicy(){
    return agedPolicy();
  }

  public List<List<String>> validPolicy(int age, String status){
    return getPolicy(age, status);
  }

  public boolean buyPolicy(  List<String> info  ) {
    boolean response = false;
    double premium=getPremium(info.get(0),info.get(1));
    double cover=policyRate(info.get(1));
    try {
      int patientId = 0;
      String query = " select patientId from patientTable where patientId=" + info.get(2);
      resultSet = dbService.executeQuery(query);
      while (resultSet.next()) {
        patientId = resultSet.getInt("patientId");
      }
      if (patientId > 0) {
        String queryUserTable = " insert into policyTable( patientID,policyPlan,premium,rate) " +
                "values(?,?,?,?);";
        insertAccounts = dbService.conn.prepareStatement(queryUserTable);
        insertAccounts.setInt(1, patientId);
        insertAccounts.setString(2, info.get(1));
        insertAccounts.setDouble(3, premium);
        insertAccounts.setDouble(4, cover);
        insertAccounts.executeUpdate();
        response = true;

      }
    }
    catch(SQLException e){
      e.getMessage();
    }
    finally {
      try{
        dbService.closeDB();
      }
      catch (SQLException e){
        e.getMessage();
      }
      if (resultSet != null) {
        try {
          resultSet.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        resultSet = null;
      }
      if (db.con != null) {
        try {
          db.con.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        db.con = null;
      }
    }
    return response;
  }

  public boolean policyClaim(  List<String> info )  {
    boolean response = false;
      int patientId = 0;
      int policyNo=0;
      double premium=0.00;
      double rate=0.00;
    try {
      String query = " select policyNo, patientID, premium,rate from policyTable where patientId="
              + Integer.valueOf(info.get(1))+" and policyNo="+Integer.valueOf(info.get(2))+";";
      resultSet = dbService.executeQuery(query);
      while (resultSet.next()) {
        policyNo = resultSet.getInt("policyNo");
        patientId = resultSet.getInt("patientId");
        premium = resultSet.getDouble("premium");
        rate = resultSet.getDouble("rate");
      }

      if (patientId > 0) {
        String queryUserTable = " insert into policyClaim( policyNo , patientID ,claimDate ,ActualBill , claimAmount )" +
                "select ?,?, ?, ?,?   from dual  where not exists (select * from policyClaim where patientID='" +
                patientId + "' and policyNo='" + policyNo+
                "' and claimDate= " + info.get(0) + " and ActualBill=" + info.get(3) + " );";
        insertAccounts = dbService.conn.prepareStatement(queryUserTable);
        insertAccounts.setInt(1, policyNo);
        insertAccounts.setInt(2, patientId);
        insertAccounts.setString(3, info.get(0));
        insertAccounts.setDouble(4, Double.parseDouble(info.get(3)) );
        insertAccounts.setDouble(5, Double.parseDouble(info.get(3))*rate);
        insertAccounts.executeUpdate();
        response = true;
      }
    }
    catch(SQLException  e){
      e.getMessage();
    }
    finally {
      try{
        dbService.closeDB();
      }
      catch (SQLException e){
        e.getMessage();
      }
      if (resultSet != null) {
        try {
          resultSet.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        resultSet = null;
      }
      if (db.con != null) {
        try {
          db.con.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        db.con = null;
      }
    }

    return response;
  }

  public boolean validDate(String date){
    return validateDate(date);
  }

}
