package Service;

import Model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  Name of file: ManageProfile.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages a user's profile
 *  Description: This class updates a saved profile and resets a user's password
 */

public class ManageProfile {

  Connection conn;
  ResultSet resultSet;
  DatabaseService dbService;
  int checkRecord=0;

  public ManageProfile(){}

  public ManageProfile(DatabaseService dbService) {
    this.dbService= dbService;
  }

  public int loadRecord(String email) {
    int userid=0;
    try {
      String query=" Select * from loginTable where username='" + email.trim() + "';";
      resultSet=  dbService.executeQuery(query);
      System.out.println("");
      while (resultSet.next()) {
        userid = resultSet.getInt("userid");
      }
    }
    catch (SQLException | ClassNotFoundException e) {
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
    }

    return userid;
  }

  public boolean updateProfile(User user){
    boolean response=false;
    try {

      checkRecord=loadRecord(user.getEmail());
      if( checkRecord>0) {
        String queryUserTable = "update patientTable set firstName=?,LastName=?,address=?,phone=? where userid=? ";
        PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
        updateStmt.setString(1, user.getfirstName());
        updateStmt.setString(2, user.getlastName());
        updateStmt.setString(3, user.getaddress());
        updateStmt.setString(4, user.getphone());
        updateStmt.setInt(5, checkRecord);
        updateStmt.executeUpdate();
        response = true;
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

    return response;
  }

  public boolean resetPassword(User user){
    boolean response=false;
    try {
       checkRecord=loadRecord(user.getEmail());
      if (checkRecord > 0) {
        if (user.getPswd().equals(user.getconfirmPswd())) {
            String queryUserTable = "update loginTable set  password=?  where userid=? ";
            PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
            updateStmt.setString(1, user.getPswd());
            updateStmt.setInt(2, checkRecord);
            updateStmt.executeUpdate();
            response = true;
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

    return response;
  }

  public boolean validateEmail(String email){
    String regex= "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    return email.matches(regex);
  }

  public String encryptPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md=MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] passByte=md.digest();
    StringBuilder string=new StringBuilder();
    for(int i=0; i<passByte.length;i++){
      string.append(Integer.toString((passByte[i]&0xff)+0x100, 16).substring(1));
    }

    return string.toString();
  }

  public boolean checkCredentials(String email, String confirmEmail, String password, String confirmPassword){
    boolean check=false;
    if ( (email.equals(confirmEmail)) &&(password.equals(confirmPassword)))  {
      check= true;
    }
    return check;
  }
}
