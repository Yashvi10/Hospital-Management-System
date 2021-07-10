/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is a custom connection class to connect to database
 *  Description: This class can be used by various other classes when they want to connect to database
 * */
package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnection {
    public Connection Connect(){
        Connection con = null;
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
