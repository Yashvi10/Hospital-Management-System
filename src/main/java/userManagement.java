import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class userManagement extends manageProfile implements  IRegistration {
    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;
    int userid=0;
    DbConnection db;
    String response;
    private String role;
    List<String>  userExist=new ArrayList<>();

     userManagement(Connection conn){
        this.conn= conn;
    }

    /*User management:
    a. Registration
    b. Login
    c. Reset Password
    d. Profile Management*/

    //This method checks and returns an existing user's record from database
    @Override
     public   List<String>  loadRecord(User users  ){
        List<String> userArray=new ArrayList<>();

        int userid=0;

        try {

            statement =  conn.createStatement();
            resultSet = statement.executeQuery(" Select * from patientTable where email='" + users.getEmail().trim() + "';");

            while (resultSet.next()) {

                userid = resultSet.getInt("userid");
                userArray.add(String.valueOf(userid) );
                userArray.add( resultSet.getString("firstName"));
                userArray.add(  resultSet.getString("LastName"));
                userArray.add(  resultSet.getString("address"));
                userArray.add( resultSet.getString("phone"));
                userArray.add( resultSet.getString("email"));
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
        return userArray;

    }

    @Override
    public String registerPatient(User user ) {
        resultSet = null;
         response=null;

        try {
            userExist=user.getcheckUser();

           if(( userExist.size()==0)&&
            ( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into patientTable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, user.getfirstName() );
                insertUserTable.setString(2, user.getlastName() );
                insertUserTable.setString(3, user.getaddress() );
                insertUserTable.setString(4, user.getphone() );
                insertUserTable.setString(5, user.getEmail().trim());
                insertUserTable.executeUpdate();
                response="Patient record added";
            }
            else response="Confirm Email/Password" ;

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
    @Override
    public String registerDoctor(User user ) {
        resultSet = null;
        response=null;

        try {
            userExist=user.getcheckUser();

            if(( userExist.size()==0)&&
                    ( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into patientTable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, user.getfirstName() );
                insertUserTable.setString(2, user.getlastName() );
                insertUserTable.setString(3, user.getaddress() );
                insertUserTable.setString(4, user.getphone() );
                insertUserTable.setString(5, user.getEmail().trim());
                insertUserTable.executeUpdate();
                response="Patient record added";
            }
            else response="Confirm Email/Password" ;

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
    @Override
    public String registerStaff(User user) {
        resultSet = null;
        response=null;

        try {
            userExist=user.getcheckUser();

            if(( userExist.size()==0)&&
                    ( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) )))){
                String queryUserTable = " insert into patientTable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, user.getfirstName() );
                insertUserTable.setString(2, user.getlastName() );
                insertUserTable.setString(3, user.getaddress() );
                insertUserTable.setString(4, user.getphone() );
                insertUserTable.setString(5, user.getEmail().trim());
                insertUserTable.executeUpdate();
                response="Patient record added";
            }
            else response="Confirm Email/Password" ;

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



    /*
     * This method inserts into the usercred
     */
    String RegisterUserCred(String role, User user ){

        response="" ;


        try {
            conn=db.Connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select userid from usertable where email like  '%"  + user.getconfirmEmail( ) + "%' ; ");
            while (resultSet.next())
                userid = resultSet.getInt("userid");

            resultSet = null;

            String queryUserCred = " insert into loginTable values(?,?,?,?)";

            PreparedStatement insertUserCred = conn.prepareStatement(queryUserCred);
            insertUserCred.setInt(1, userid);
            insertUserCred.setString(2, user.getEmail().trim());
            insertUserCred.setString(3, user.getPswd().trim());
            insertUserCred.setString(4, role);
            insertUserCred.executeUpdate();
            response="User password created";
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

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

    boolean loginUser( String email, String pswd){
        boolean boolResponse=false;
        String user=null;
        int count=0;
        try{

            conn=db.Connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select username,password from loginTable where trim(username) ='"  + email + "'  and trim(password)='"+pswd+"'; ");
            while (resultSet.next())
                user=resultSet.getString("username");

            if(user!= null)
                boolResponse=true;

            if(boolResponse)
                System.out.println("Login Successful" );
            else  System.out.println("Login Failed" );

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

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



}


