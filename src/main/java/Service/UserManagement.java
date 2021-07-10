package Service;

import Interface.IRegistration;
import Model.User;

import java.sql.*;

public class UserManagement extends ManageProfile implements IRegistration {

    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;
    PreparedStatement insertUserTable;
    String response;
    int chkUser=1;
    int checkRecord=0 ;
    int userid ;

    public UserManagement(Connection conn){
        this.conn= conn;
    }

    /*User management:
    a. Registration
    b. Login
    c. Reset Password
    d. Profile Management*/

    //This method checks and returns an existing user's record from database
    @Override
    public int loadRecord(User users  ){
        userid=0;

        try {

            statement =  conn.createStatement();
            resultSet = statement.executeQuery(" Select * from loginTable where username='" + users.getEmail().trim() + "';");

            while (resultSet.next()) {

                userid = resultSet.getInt("userid");
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
        return userid;

    }

    public String registerLogin(User user){
        response="" ;
        try {
            checkRecord=user.getcheckUser();
            if( checkRecord==0) {
                String queryUserTable = " insert into loginTable( username,password ) values( ?,? )";

                insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, user.getEmail() );
                insertUserTable.setString(2, user.getPswd() );
                insertUserTable.executeUpdate();
                response="Login added";
            }
            else response="Username already exists";


        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());

        }
        finally {

            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException sqlEx) { }
                resultSet = null;
            }

            if (statement != null) {
                try { statement.close(); } catch (SQLException sqlEx) { } // ignore
                statement = null;
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { }
                conn = null;
            }
        }
        return response;
    }

    @Override
    public String registerPatient(User user ) {
        resultSet = null;
        response=null;

        try {
            checkRecord=user.getcheckUser();

            if ( checkRecord>=0) {
                if ( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) ))) {
                    String queryUserTable = " insert into patientTable(userid,firstName,LastName,address,phone  ) values( ?,?,?,?,? )";

                    insertUserTable = conn.prepareStatement(queryUserTable);
                    insertUserTable.setInt(1, user.getUserid() );
                    insertUserTable.setString(2, user.getfirstName() );
                    insertUserTable.setString(3, user.getlastName() );
                    insertUserTable.setString(4, user.getaddress() );
                    insertUserTable.setString(5, user.getphone() );
                    insertUserTable.executeUpdate();
                    response="Patient record added";
                }
                else response="Confirm Email/Password" ;

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
        }
        return response;
    }
    @Override
    public String registerStaff(String role,User user ) {
        resultSet = null;
        response=null;

        try {
            checkRecord=user.getcheckUser();

            if(( checkRecord>=0)&&
                    ( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into hospitalStaff(userid,firstName,LastName,address,phone,designation  ) values( ?,?,?,?,? ,?)";

                insertUserTable = conn.prepareStatement(queryUserTable);
//                insertUserTable.setInt(1, checkRecord );
                insertUserTable.setInt(1, user.getUserid() );
                insertUserTable.setString(2, user.getfirstName() );
                insertUserTable.setString(3, user.getlastName() );
                insertUserTable.setString(4, user.getaddress() );
                insertUserTable.setString(5, user.getphone() );
                insertUserTable.setString(6, role);
                insertUserTable.executeUpdate();
                response="Staff record added";
            }
            else response="Confirm Email/Password" ;

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        finally {

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
        }
        return response;
    }

    public boolean loginUser( User user){
        boolean boolResponse=false;
        String qUser="";
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select username,password from loginTable where trim(username) ='"
                    + user.getEmail().trim() + "'  and trim(password)='"+user.getPswd()+"'; ");
            while (resultSet.next())
                qUser=resultSet.getString("username");

            if(!qUser.equals( "")  )
                boolResponse=true;

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());

        }
        finally {

            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException sqlEx) {sqlEx.getMessage(); }
                resultSet = null;
            }

            if (statement != null) {
                try { statement.close(); } catch (SQLException sqlEx) {sqlEx.getMessage(); }
                statement = null;
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                conn = null;
            }
        }
        return boolResponse;
    }

    public Integer getLastUserId() {
        Integer result = 0;
        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT Max(userid) FROM CSCI5308_8_DEVINT.loginTable;");

            while (resultSet.next()) {

                result = resultSet.getInt("max(userid)");
            }

        } catch (SQLException e) {
            System.out.println("Record does not exist");
        }
        return result;
    }

}
