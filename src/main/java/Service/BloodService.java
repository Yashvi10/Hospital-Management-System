package Service;

import Interface.BloodDAO;
import Model.BloodInventory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: BloodService.java
 *  Author:  Yashvi Lad
 *  Purpose: To list all the available items in blood inventory.
 *  Description: This file will fetch all the items from database table to check available items in inevntory.
 * */
public class BloodService implements BloodDAO {

    @Override
    public List<BloodInventory> getAllBloodGroup() {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        List<BloodInventory> bloodInventoryList = new ArrayList<BloodInventory>();

        if(conn != null) {
            String SQL = "Select * from blood_inventory";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);
                while(rs.next()) {
                    Integer blood_id = rs.getInt(1);
                    String blood_group = rs.getString(2);
                    Integer bottles = rs.getInt(3);

                    BloodInventory bloodInventory = new BloodInventory(blood_group, bottles);
                    bloodInventoryList.add(bloodInventory);
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
        return bloodInventoryList;
    }
}
