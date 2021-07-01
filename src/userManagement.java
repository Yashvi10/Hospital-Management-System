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

    userManagement(String firstName,String lastName,String address,String phone,String email,String confirmEmail,String pswd, String confirmPswd,String role ){
          db = new DbConnection();
          this.firstName=firstName;
          this.lastName=lastName;
          this.address=address;
          this.phone=phone;
          this.email=email;
          this.confirmEmail=confirmEmail;
          this.pswd=pswd;
          this.confirmPswd=confirmPswd;
          this.role=role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*
     * This method saves users details in the database
     */
    String RegisterUser( ){
        resultSet = null;
        conn = null;
        response=null;

        try {
            //ResultSet resultSet = null;
            Connection conn = null;

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

           /* if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException sqlEx) { }
                resultSet = null;
            }*/

            if (conn != null) {
                try { conn.close(); } catch (SQLException sqlEx) { }
                conn = null;
            }
        }
        return response;
    }

    /*
     * This method inserts into the usercred
     */
    String RegisterUserCred( ){

        response="" ;
        try {
            conn=db.Connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select userid from usertable where email like  '%"  + email + "%' ; ");
            while (resultSet.next())
                userid = resultSet.getInt("userid");

            String queryUserCred = " insert into usercred values(?,?,?,?)";

            PreparedStatement insertUserCred = conn.prepareStatement(queryUserCred);
            insertUserCred.setInt(1, userid);
            insertUserCred.setString(2, email.trim());
            insertUserCred.setString(3, pswd.trim());
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

 
}
