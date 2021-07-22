import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection con = null;

    public Connection Connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_8_DEVINT","CSCI5308_8_DEVINT_USER","cWhbaAs94FR");

            if(con != null) {
                return con;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


}
