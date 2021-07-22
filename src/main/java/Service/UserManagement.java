package Service;

import Interface.IRegistration;
import Model.User;
import java.sql.*;

/*
 *  Name of file: UserManagement.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class is for accessing the database
 *  Description: This class accesses the database to register user
 *               and login into the database
 */

public class UserManagement extends ManageProfile implements IRegistration {

  Statement statement ;
  ResultSet resultSet ;
  Connection conn ;
  PreparedStatement insertUserTable;
  int checkRecord=0 ;
  DatabaseService dbService;
  boolean check=false;

  public UserManagement(DatabaseService dbService ){
    this.dbService= dbService;
  }

  public boolean registerLogin(User user,String role){

    try {
      checkRecord=loadRecord(user.getEmail());
      if( checkRecord==0) {
        if(validateEmail(user.getEmail())) {
          String queryUserTable = " insert into loginTable( username,password,designation ) values( ?,? ,?)";
          insertUserTable = dbService.conn.prepareStatement(queryUserTable);
          insertUserTable.setString(1, user.getEmail());
          insertUserTable.setString(2, user.getPswd());
          insertUserTable.setString(3, role);
          insertUserTable.executeUpdate();
          check = true;
        }
      }
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        }
        catch (SQLException sqlEx)
        { }
        resultSet = null;
      }

      if (statement != null) {
        try {
          statement.close();
        }
        catch (SQLException sqlEx) { }
          statement = null;
      }

      if (dbService.conn != null) {
        try {
          dbService.conn.close();
        }
        catch (SQLException sqlEx) { }
        dbService.conn = null;
      }
    }

    return check;
  }

  @Override
  public boolean registerPatient(User user ) {
    resultSet = null;
    try {
      checkRecord=loadRecord(user.getEmail());
      if ( checkRecord>=0) {
        if (checkCredentials(user.getEmail(),user.getconfirmEmail( ) ,user.getPswd(),user.getconfirmPswd( )) ) {
          String queryUserTable = " insert into patientTable(firstName, LastName, address, phone , loginId) " +
        "values( ?,?,?,?,? )";
          insertUserTable = dbService.conn.prepareStatement(queryUserTable);
          insertUserTable.setString(1, user.getfirstName() );
          insertUserTable.setString(2, user.getlastName() );
          insertUserTable.setString(3, user.getaddress() );
          insertUserTable.setString(4, user.getphone() );
          insertUserTable.setInt(5, checkRecord);
          insertUserTable.executeUpdate();
          check=true;
        }
      }
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    finally {
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }
    }

    return check;
  }

  @Override
  public boolean registerStaff(String role,User user ) {
    try {
      checkRecord=loadRecord(user.getEmail());
      if(( checkRecord>=0)&&(( user.getEmail().equals(user.getconfirmEmail( ) ))
      &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
        String queryUserTable = " insert into hospitalStaff(firstName, LastName, address, phone, designation,loginID) " +
      " values( ?,?,?,?,? ,?)";
        insertUserTable = dbService.conn.prepareStatement(queryUserTable);
        insertUserTable.setString(1, user.getfirstName() );
        insertUserTable.setString(2, user.getlastName() );
        insertUserTable.setString(3, user.getaddress() );
        insertUserTable.setString(4, user.getphone() );
        insertUserTable.setString(5, role);
        insertUserTable.setInt(6, checkRecord);
        insertUserTable.executeUpdate();
        check=true;
      }
    }
    catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    finally {
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }
    }

    return check;
  }

  public boolean loginUser( String username, String password){
    boolean boolResponse=false;
    String qUser="";
    try{
      String query="Select username,password from loginTable where trim(username) ='"
              + username.trim() + "'  and trim(password)='"+password.trim()+"'; ";
      resultSet=  dbService.executeQuery(query);
      while (resultSet.next()){
        qUser=resultSet.getString("username");
      }

      if(!qUser.equals( "")  ) {
        boolResponse=true;
      }
    }
    catch (SQLException | ClassNotFoundException e) {
      System.out.println("SQLException: " + e.getMessage());
    }
    finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        resultSet = null;
      }

      if (statement != null) {
        try {
          statement.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        statement = null;
      }

      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException sqlEx) {
          sqlEx.getMessage();
        }
        conn = null;
      }
    }

    return boolResponse;
  }

  public Integer getLastUserId() {
    Integer result = 0;
    try {
      String query="SELECT Max(userid) FROM CSCI5308_8_DEVINT.loginTable;";
      resultSet=  dbService.executeQuery(query);
      while (resultSet.next()) {
        result = resultSet.getInt("max(userid)");
      }
    }
    catch (SQLException | ClassNotFoundException e) {
      e.getMessage();
    }

    return result;
  }
}
