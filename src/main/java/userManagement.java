import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userManagement extends manageProfile implements  IRegistration {
    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;
    int userid=0;
    DbConnection db;
    String response; String boolResponse;
    private  String firstName ;
    private  String lastName ;
    private  String address ;
    private  String phone ;
    private String email;
    private String confirmEmail;
    private String pswd;
    private String confirmPswd ;
    private String role;
    Map<Integer, String> user=new HashMap<>();

    userManagement(){}

    userManagement(Connection conn){
        this.conn=conn;
    }

    /*User management:
    a. Registration
    b. Login
    c. Reset Password
    d. Profile Management*/

    //This method checks and returns an existing user's record from database
    @Override
    public Map<Integer, List<String>> loadRecord(User users  ){
        List<String> userArray=new ArrayList<>();
        Map<Integer, List<String>> userInfo=new HashMap<>();

        int userid=0;

        try {

            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(" Select * from patientTable where email='" + users.getEmail().trim() + "';");

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

    @Override
    public String registerPatient(User user ) {
        resultSet = null;
        conn = null;
        response=null;

        try {
           // user.setEmail( email);
           // user.setconfirmEmail(confirmEmail);
           // user.setPswd( pswd);
           // user.setconfirmPswd(confirmPswd);
            if( (user.getEmail().equals(user.getconfirmEmail( ) )) &&(user.getPswd().equals(user.getconfirmPswd( ) ))){
                String queryUserTable = " insert into patientTable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";
                conn = db.Connect();

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, firstName);
                insertUserTable.setString(2, lastName);
                insertUserTable.setString(3, address);
                insertUserTable.setString(4, phone);
                insertUserTable.setString(5, email.trim());
                insertUserTable.executeUpdate();
                response="User added";
            }
            else System.out.println("Confirm Email/Password");

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
        conn = null;
        response=null;
        this.role=role;

        try {
            if( (email.equals(confirmEmail) )&&(pswd.equals(confirmPswd))){
                String queryUserTable = " insert into usertable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";
                conn = db.Connect();

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, firstName);
                insertUserTable.setString(2, lastName);
                insertUserTable.setString(3, address);
                insertUserTable.setString(4, phone);
                insertUserTable.setString(5, email.trim());
                insertUserTable.executeUpdate();
                response="User added";
            }
            else System.out.println("Confirm Email/Password");

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
        conn = null;
        response=null;
        this.role=role;

        try {
            if( (email.equals(confirmEmail) )&&(pswd.equals(confirmPswd))){
                String queryUserTable = " insert into usertable(firstName,LastName,address,phone,email) values( ?,?,?,?,?)";
                conn = db.Connect();

                PreparedStatement insertUserTable = conn.prepareStatement(queryUserTable);
                insertUserTable.setString(1, firstName);
                insertUserTable.setString(2, lastName);
                insertUserTable.setString(3, address);
                insertUserTable.setString(4, phone);
                insertUserTable.setString(5, email.trim());
                insertUserTable.executeUpdate();
                response="User added";
            }
            else System.out.println("Confirm Email/Password");

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
    String RegisterUserCred(String role ){

        response="" ;


        try {
            conn=db.Connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select userid from usertable where email like  '%"  + email + "%' ; ");
            while (resultSet.next())
                userid = resultSet.getInt("userid");

            resultSet = null;

            String queryUserCred = " insert into loginTable values(?,?,?,?)";

            PreparedStatement insertUserCred = conn.prepareStatement(queryUserCred);
            insertUserCred.setInt(1, userid);
            insertUserCred.setString(2, email);
            insertUserCred.setString(3, pswd);
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


