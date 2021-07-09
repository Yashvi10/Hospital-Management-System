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

    manageProfile(){}

    manageProfile(Connection conn){
        this.conn=conn;
    }


    //This method updates user record
    String updateProfile( int userid, String firstName,String lastName,String address,String phone ){

        String response="";
        try {
            String queryUserTable = "update patientTable set firstName=?,LastName=?,address=?,phone=? where userid=? ";
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
