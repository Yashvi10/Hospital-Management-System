package Service;

/*
 *  Name of file: CustomConnection.java
 *  Author:  Yashvi Lad
 *  Purpose: For connecting code with database.
 *  Description: This file will make JDBC connections.
 * */

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
