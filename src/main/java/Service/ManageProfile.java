package Service;

import Model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/*
 *  Name of file: ManageProfile.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages basic operations for UserManagement.java
 *  Description: This class updates a saved profile, resets a user's password, encrypts a password string
 *              and validates email
 */

public class ManageProfile {

    Connection conn;
    ResultSet resultSet;
    Statement statement;
    int checkRecord=0;

    public ManageProfile(){}

    public ManageProfile(Connection conn) {
        this.conn= conn;
    }

    public int loadRecord(String email ) {
        int userid=0;
        try {
            String query=" Select * from loginTable where username='" + email.trim() + "';";
            statement=conn.createStatement();
            resultSet=  statement.executeQuery(query);
            System.out.println("");
            while (resultSet.next()) {
                userid = resultSet.getInt("userid");
            }

        }
        catch (SQLException   e) {
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

            checkRecord=loadRecord(user.getEmail() );
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
            checkRecord=loadRecord(user.getEmail() );
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
        for (byte b : passByte) {
            string.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
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
