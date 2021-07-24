package Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CustomConnection {

    public String getProperty(String name) {
        try {
            FileInputStream fis = new FileInputStream("resources/config.properties");
            Properties properties = new Properties();

            properties.load(fis);

            return properties.getProperty(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Connection Connect(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    getProperty("GCP_STRING_PROD"),     // GCP_STRING_PROD
                    getProperty("GCP_USERNAME_PROD"),   // GCP_USERNAME_PROD
                    getProperty("GCP_PASSWORD_PROD"));  // GCP_PASSWORD_PROD

            if(con != null) {
                return con;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
