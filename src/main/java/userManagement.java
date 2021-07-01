import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class userManagement {
    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;
    int userid=0;
    DbConnection db;
    String response; String boolResponse;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String confirmEmail;
    private String pswd;
    private String confirmPswd ;
    private String role;

    userManagement(String email, String pswd ){

       db = new DbConnection();
        this.email=email;
         this.pswd=pswd;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }


    /*
     * This method saves users details in the database
     */
    String RegisterUser( String firstName,String lastName,String address,String phone, String confirmEmail,String confirmPswd , String role){

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

            String queryUserCred = " insert into usercred values(?,?,?,?)";

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
            resultSet = statement.executeQuery("Select username,password from usercred where trim(username) ='"  + email + "'  and trim(password)='"+pswd+"'; ");
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

    boolean updateProfile( String email ){
        boolean boolResponse=false;
        String user=null;
        int count=0;
        try{

            conn=db.Connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(" Select * from usertable where email='"+email.trim()+"';" );

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


