import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class manageProfile {
    Statement statement ;
    ResultSet resultSet ;
    Connection conn ;

    manageProfile(Connection conn){
        this.conn=conn;
    }


    //This method returns an existing user's record from database
    Map<Integer, List<String>> returnRecord( String email ){
        List<String> userArray=new ArrayList<>();
        Map<Integer, List<String>> userInfo=new HashMap<>();
        int userid=0;

        try {

            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(" Select * from usertable where email='" + email.trim() + "';");

            userArray.clear();
            userInfo.clear();
            while (resultSet.next()) {

                userid = resultSet.getInt("userid");
                userArray.add( resultSet.getString("firstName"));
                userArray.add(  resultSet.getString("LastName"));
                userArray.add(  resultSet.getString("address"));
                userArray.add( resultSet.getString("phone"));
            }

            if (userArray != null) {
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

    void updateProfile( String email ){

    }
}
