import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public Connection Connect(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    Constant.DB_STR,Constant.DB_DEV_USERNAME, Constant.DB_DEV_PASSWORD);

            if(con != null) {
                return con;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
