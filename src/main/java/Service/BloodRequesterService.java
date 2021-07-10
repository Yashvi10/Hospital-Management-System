package Service;

/*
 *  Name of file: BloodRequesterService.java
 *  Author:  Yashvi Lad
 *  Purpose: For inserting and updating details of requester into database.
 *  Description: This file inserts the details of a requester into database and at the same time updates
 *               inventory by deducting requested blood group from available items.
 * */


import DAO.BloodRequesterDAO;
import Model.BloodInventory;
import Model.BloodRequester;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BloodRequesterService implements BloodRequesterDAO {

    @Override
    public Boolean addRequester(BloodRequester bloodRequester) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "insert into blood_request(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) " +
                    "values('" +bloodRequester.getPin() +"','" +bloodRequester.getFirstname() +"','" +bloodRequester.getMiddlename()
                    +"','" +bloodRequester.getLastname() +"','" +bloodRequester.getBlood_group()+ "','" + bloodRequester.getContact()
                    + "','" + bloodRequester.getDate()+"')";

            Statement statement = null;
            try {
                statement = conn.createStatement();
                statement.executeUpdate(SQL);
                conn.close();
                result = true;
                return result;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Boolean updateRequester(Integer bottleQuantity, String blood_group) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "update CSCI5308_8_DEVINT.blood_inventory SET No_of_bottles=No_of_bottles-" +bottleQuantity + " where blood_group = '" + blood_group + "';";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                statement.executeUpdate(SQL);
                conn.close();
                result = true;
                return result;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }
}
