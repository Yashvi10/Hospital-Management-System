import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class manageProfile {
    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;

    manageProfile(Connection conn){
        this.conn=conn;
    }

    boolean updateProfile( String email ){
        boolean boolResponse=false;
        String user=null;
        int count=0;
        try{

            statement = this.conn.createStatement();
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
            if (this.conn != null) {
                try { this.conn.close(); } catch (SQLException sqlEx) { sqlEx.getMessage();}
                this.conn = null;
            }
        }
        return boolResponse;
    }
}
