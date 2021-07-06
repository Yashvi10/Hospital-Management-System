package Services;

import DAO.PharmacyDAO;
import Model.Pharmacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
* PharmacyService class is responsible for all operation related to Pharmacy and has implemented PharmacyDAO
* */
public class PharmacyService implements PharmacyDAO  {

    @Override
    public List<Pharmacy> getAllMedicines()  {

        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();

        if  (conn != null)  {
            String sql = "Select * from CSCI5308_8_DEVINT.pharmacy_list where stock > 0";
            Statement statement = null;
            try  {
                    statement = conn.createStatement();
                    ResultSet result = statement.executeQuery(sql);
                    while(result.next()) {
                    Integer p_id = result.getInt(1);
                    String product_name = result.getString(2);
                    Double price = result.getDouble(3);
                    Integer stock = result.getInt(4);
                    String expiry_date = result.getString(5);

                    Pharmacy pharmacy = new Pharmacy(p_id, product_name, price, stock, expiry_date);
                    pharmacyList.add(pharmacy);
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }  finally  {
                try  {
                    conn.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
            }
        }
        return pharmacyList;
    }

    @Override
    public void updateStock(String pId, Integer qty)  {

        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        Integer stock = 0;

        if  (conn != null)  {
            String sql = "SELECT * from CSCI5308_8_DEVINT.pharmacy_list where p_id ='" +pId +"'";
            Statement statement = null;

            try  {
                statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    stock = result.getInt(4);
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }

            Integer newStock = stock - qty;
            sql = "UPDATE CSCI5308_8_DEVINT.pharmacy_list set stock = '" +newStock +"' where p_id ='" +pId +"'";
            try  {
                statement.executeUpdate(sql);
                System.out.println("Stock Updated");
                conn.close();
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
    }
}
