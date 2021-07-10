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
import Model.BloodRequester;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BloodDonorService implements BloodDonorDAO {
    @Override
    public Boolean addDonor(BloodDonor bloodDonor) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "insert into blood_donation(First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) " +
                    "values('" +bloodDonor.getFirstname() +"','" +bloodDonor.getMiddlename()
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

    @Override
    public List<BloodDonor> getAllDonors() {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        List<BloodDonor> bloodDonorList = new ArrayList<BloodDonor>();

        if(conn != null) {
            String SQL = "Select * from blood_donation";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);
                while(rs.next()) {
                    String firstname = rs.getString(1);
                    String middlename = rs.getString(2);
                    String lastname = rs.getString(3);
                    String blood_group = rs.getString(4);
                    String contact = rs.getString(5);
                    String date = rs.getString(6);

                    BloodDonor bloodDonor = new BloodDonor(firstname,middlename,lastname,blood_group,contact,date);
                    bloodDonorList.add(bloodDonor);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return bloodDonorList;
    }
}
