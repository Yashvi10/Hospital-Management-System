import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.PreparedStatement;

public class manageProfile {
    Statement statement =null;
    ResultSet resultSet=null;
    Connection conn;

    manageProfile(Connection conn){
        this.conn=conn;
    }


    //This method returns an existing user's record from database
    Map<Integer, List<String>> returnRecord( String email ){
        List<String> userArray=new ArrayList<>();
        Map<Integer, List<String>> userInfo=new HashMap<>();
        int userid=0;

        try {

            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(" Select * from usertable where email='" + email.trim() + "';");

             while (resultSet.next()) {

                userid = resultSet.getInt("userid");
                userArray.add( resultSet.getString("firstName"));
                userArray.add(  resultSet.getString("LastName"));
                userArray.add(  resultSet.getString("address"));
                userArray.add( resultSet.getString("phone"));
            }

            if (userArray == null) {
            } else {
                for (String user : userArray)
                    System.out.println(user);

                userInfo.put(userid, userArray);
            }

        }
        catch (SQLException e) {
            System.out.println("Record does not exist");}

        finally {

            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException sqlEx) {sqlEx.getMessage(); }
                resultSet = null;
            }

            if (statement != null) {
                try { statement.close(); } catch (SQLException sqlEx) {sqlEx.getMessage(); }
                statement = null;
            }
            if (this.conn != null) {
                try { this.conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                this.conn = null;
            }
        }
        return userInfo;

    }

    //This method updates user record
    String updateProfile( int userid, String firstName,String lastName,String address,String phone ){

        String response="";
        try {
            String queryUserTable = "update usertable set firstName=?,LastName=?,address=?,phone=? where userid=? ";
            PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
            updateStmt.setString(1, firstName);
            updateStmt.setString(2, lastName);
            updateStmt.setString(3, address);
            updateStmt.setString(4, phone);
            updateStmt.setInt(5, userid);
            updateStmt.executeUpdate();
            response = "Record Updated";

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
        }

        return response;

    }

    //This method updates user password
    String resetPassword( int userid, String username,String pswd, String confirmPswd  ){

        String response="";
        try {
            if(pswd.equals(confirmPswd)) {

                String queryUserTable = "update loginTable set username=?,password=?  where userid=? ";
                PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
                updateStmt.setString(1, username);
                updateStmt.setString(2, pswd);
                updateStmt.setInt(3, userid);

                updateStmt.executeUpdate();
                response = "Password Updated";
            }
            else  response="Error: Passwords do not match";

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
        }

        return response;

    }
}
