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
    PreparedStatement insertUserTable;
    int userid=0;
    DbConnection db;
    String response;
    private String role;
    //List<String>  userExist=new ArrayList<>();
    int chkUser=1;
    int checkRecord=0 ;

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
     public   int loadRecord(User users  ){
        List<String> userArray=new ArrayList<>();

        int userid=0;

        try {

            statement =  conn.createStatement();
           // resultSet = statement.executeQuery(" Select * from patientTable where email='" + users.getEmail().trim() + "';");

            resultSet = statement.executeQuery(" Select * from loginTable where username='" + users.getEmail().trim() + "';");

            while (resultSet.next()) {

                userid = resultSet.getInt("userid");
               /* userArray.add(String.valueOf(userid) );
                userArray.add( resultSet.getString("firstName"));
                userArray.add(  resultSet.getString("LastName"));
                userArray.add(  resultSet.getString("address"));
                userArray.add( resultSet.getString("phone"));
                userArray.add( resultSet.getString("email"));*/
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

       String registerLogin(User user){
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
                    insertUserTable.setInt(1, checkRecord );
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
                insertUserTable.setInt(1, checkRecord );
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

    boolean loginUser( User user){
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



}


