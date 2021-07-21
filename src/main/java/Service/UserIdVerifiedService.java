package Service;

import Interface.UserIdVerifiedDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *  Name of file: UserIdVerifiedService.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like service class which will implement the UserIdVerifiedDAO
 * */
public class UserIdVerifiedService implements UserIdVerifiedDAO {

    CustomConnection connection = new CustomConnection();
    @Override
    public Boolean isUserFound(Integer user_id) {
        Connection conn = connection.Connect();

        Boolean result = false;

        if(conn != null) {
            Statement st = null;
            try {
                st = conn.createStatement();
                String SQL = "SELECT count(*) FROM loginTable where userid = " +user_id +";";
                ResultSet rs = st.executeQuery(SQL);
                rs.next();

                if(rs.getInt("count(*)") > 0) {
                    return true;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return result;
    }
}
