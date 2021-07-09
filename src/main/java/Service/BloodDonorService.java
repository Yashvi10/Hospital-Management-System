package Service;

/*
 *  Name of file: BloodDonorService.java
 *  Author:  Yashvi Lad
 *  Purpose: For inserting and updating details of donor into database.
 *  Description: This file inserts the details of a donor into database and at the same time updates
 *               inventory by adding donated blood group to available items.
 * */

import DAO.BloodDonorDAO;
import Model.BloodDonor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BloodDonorService implements BloodDonorDAO {
    @Override
    public Boolean addDonor(BloodDonor bloodDonor) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "insert into blood_donation(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) " +
                    "values('" +bloodDonor.getPin() +"','" +bloodDonor.getFirstname() +"','" +bloodDonor.getMiddlename()
                    +"','" +bloodDonor.getLastname() +"','" +bloodDonor.getBlood_group()+ "','" + bloodDonor.getContact()
                    + "','" + bloodDonor.getDate()+"')";

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
    public Boolean updateDonor(String blood_group) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "update CSCI5308_8_DEVINT.blood_inventory SET No_of_bottles=No_of_bottles + 1 where blood_group = '" + blood_group + "';";
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
