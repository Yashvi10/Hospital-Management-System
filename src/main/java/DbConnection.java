import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection Connect(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_8_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","CSCI5308_8_DEVINT_USER","cWhbaAs94FR");

            //con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project5308?useSSL=false","root","root");
            if(con != null) {
                return con;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}


