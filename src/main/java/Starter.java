import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Starter {
    //a
    public static void main(String[] args) {
        System.out.println("Working");
        // This is the sample method which is fetching data from database
        getDataFromDb();
    }

    public String getMsg(){
        return "Hello";
    }

    //This is the implementation of how data can be fetch from db but in our project we will not do like this
    //we will use different design patterns like we like create interfaces and then we define functions and use them
    // this is just a sample
    public static void getDataFromDb(){
        DbConnection conn = new DbConnection();
        Connection connection = conn.Connect();
        String sql = "Select * from test_table";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                String namee = result.getString(2);
                System.out.println(namee);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
