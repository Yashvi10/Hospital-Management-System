package Service;

import Model.User;

import java.sql.*;

public class ManageProfile {

    Statement statement =null;
    ResultSet resultSet=null;
    Connection conn;
    int checkRecord=0;

    ManageProfile(){}

    public ManageProfile(Connection conn){
        this.conn=conn;
    }

    //This method updates patient's record
    public String updateProfile(User user){

        String response="";
        try {
            checkRecord=user.getcheckUser();

            if( checkRecord>0) {
                String queryUserTable = "update patientTable set firstName=?,LastName=?,address=?,phone=? where userid=? ";
                PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
                updateStmt.setString(1, user.getfirstName());
                updateStmt.setString(2, user.getlastName());
                updateStmt.setString(3, user.getaddress());
                updateStmt.setString(4, user.getphone());
                updateStmt.setInt(5, checkRecord);
                updateStmt.executeUpdate();
                response = "Record Updated";
            }

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        finally {

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return response;

    }

    //This method updates user password
    public String resetPassword(User user){

        String response="";
        try {
            checkRecord = user.getcheckUser();

            if (checkRecord > 0) {
                if (user.getPswd().equals(user.getconfirmPswd())) {

                    String queryUserTable = "update loginTable set  password=?  where userid=? ";
                    PreparedStatement updateStmt = conn.prepareStatement(queryUserTable);
                    updateStmt.setString(1, user.getPswd());
                    updateStmt.setInt(2, checkRecord);

                    updateStmt.executeUpdate();
                    response = "Password Updated";
                } else response = "Error: Passwords do not match";

            }
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        finally {

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return response;

    }
}
