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
    Connection con ;
    PreparedStatement insertUserTable;
    int checkRecord=0 ;
    boolean check=false;

    public UserManagement(){}

    public int loadRecord( String email ) {
        int userid=0;
        try {
            String query=" Select * from loginTable where username='" + email.trim() + "';";
            statement=con.createStatement();
            resultSet=  statement.executeQuery(query);
            System.out.println( );
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

    public boolean registerLogin(CustomConnection db,User user,String role){
        try {
            con=db.Connect();
            checkRecord=  loadRecord( user.getEmail() );

            if( checkRecord==0) {
                check = ((checkCredentials(user.getEmail(), user.getconfirmEmail(),
                        user.getPswd(), user.getconfirmPswd())));
                if (check) {
                    String queryUserTable = " insert into loginTable( username,password,designation ) values( ?,? ,?)";
                    insertUserTable = con.prepareStatement(queryUserTable);
                    insertUserTable.setString(1, user.getEmail());
                    insertUserTable.setString(2, encryptPassword(user.getPswd()));
                    insertUserTable.setString(3, role);
                    insertUserTable.executeUpdate();


                }
            }

        }
        catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException e)
                { e.getMessage();}
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException e) { e.getMessage();}
                statement = null;
            }

            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException e) { e.getMessage();}
                con = null;
            }
        }

        return check;
    }

    @Override
    public boolean registerPatient(CustomConnection db, User user ) {
        resultSet = null;
        try {
            con=db.Connect();
            checkRecord= loadRecord( user.getEmail() );
            if(( checkRecord>=0)&&(( user.getEmail().equals(user.getconfirmEmail( ) ))
                    &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into patientTable(firstName, LastName, address, phone , loginId) " +
                        "values( ?,?,?,?,? )";
                insertUserTable = con.prepareStatement(queryUserTable);
                insertUserTable.setString(1, user.getfirstName() );
                insertUserTable.setString(2, user.getlastName() );
                insertUserTable.setString(3, user.getaddress() );
                insertUserTable.setString(4, user.getphone() );
                insertUserTable.setInt(5, checkRecord);
                insertUserTable.executeUpdate();
                check=true;
            }

        }
        catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException sqlEx) {
                    sqlEx.getMessage();
                }
                con = null;
            }
        }

        return check;
    }

    @Override
    public boolean registerStaff(CustomConnection db,String role,User user ) {
        try {
            con=db.Connect();
            checkRecord=loadRecord( user.getEmail() );
            if(( checkRecord>=0)&&(( user.getEmail().equals(user.getconfirmEmail( ) ))
                    &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into hospitalStaff(firstName, LastName, address, phone, designation,loginID) " +
                        " values( ?,?,?,?,? ,?)";
                insertUserTable = con.prepareStatement(queryUserTable);
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
            System.out.println( e.getMessage());
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException sqlEx) {
                    sqlEx.getMessage();
                }
                con = null;
            }
        }

        return check;
    }

    public String loginUser( CustomConnection db,String username, String password){
        con=db.Connect();
        String qUser="";
        try{
            String query="Select  designation from loginTable where trim(username) ='"
                    + username.trim() + "'  and trim(password)='"+encryptPassword(password.trim())+"'; ";
            statement=con.createStatement();
            resultSet=  statement.executeQuery(query);
            while (resultSet.next()){
                qUser=resultSet.getString("designation");
            }
        }
        catch (SQLException  e) {
            System.out.println( e.getMessage());
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

            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException sqlEx) {
                    sqlEx.getMessage();
                }
                con = null;
            }
        }

        return qUser;
    }

    public boolean updateProfile(CustomConnection db,User user){
        boolean response=false;
        try {

            con=db.Connect();
            checkRecord=loadRecord( user.getEmail() );
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

    public boolean resetPassword(CustomConnection db,User user){
        boolean response=false;
        try {
            con=db.Connect();
            checkRecord=loadRecord( user.getEmail() );
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

}
