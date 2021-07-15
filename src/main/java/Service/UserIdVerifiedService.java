package Service;

import Interface.UserIdVerifiedDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserIdVerifiedService implements UserIdVerifiedDAO {
    @Override
    public Boolean isUserFound(Integer user_id) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();

        Boolean result = false;

        if(conn != null) {
            Statement st = null;
            try {
                st = conn.createStatement();
                String SQL = "SELECT count(*) FROM CSCI5308_8_DEVINT.loginTable where userid = " +user_id +";";
                ResultSet rs = st.executeQuery(SQL);
                rs.next();

                if(rs.getInt("count(*)") > 0) {
                    return true;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return result;
    }
}
