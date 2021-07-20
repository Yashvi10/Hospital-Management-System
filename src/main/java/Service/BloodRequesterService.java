package Service;

import Interface.BloodRequesterDAO;
import Model.BloodRequester;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: BloodRequesterService.java
 *  Author:  Yashvi Lad
 *  Purpose: For inserting and updating details of requester into database.
 *  Description: This file inserts the details of a requester into database and at the same time updates
 *               inventory by deducting requested blood group from available items.
 * */

public class BloodRequesterService implements BloodRequesterDAO {

    @Override
    public Boolean addRequester(BloodRequester bloodRequester) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "insert into blood_request(First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) " +
                    "values('" +bloodRequester.getFirstname() +"','" +bloodRequester.getMiddlename()
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

    @Override
    public Integer isBloodAvaiable(String blood_group) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        if(conn != null) {
            String SQL = "select No_of_bottles from  CSCI5308_8_DEVINT.blood_inventory where blood_group = '" +blood_group +"';";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);

                while(rs.next()) {
                    Integer count = rs.getInt("No_of_bottles");
                    return count;
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
        return 0;
    }

    @Override
    public List<BloodRequester> getAllRequesters() {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        List<BloodRequester> bloodRequesterList = new ArrayList<BloodRequester>();

        if(conn != null) {
            String SQL = "Select * from blood_request";
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

                    BloodRequester bloodRequester = new BloodRequester(firstname,middlename,lastname,blood_group,contact,date);
                    bloodRequesterList.add(bloodRequester);
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
        return bloodRequesterList;
    }
}
