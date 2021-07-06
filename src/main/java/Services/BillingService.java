package Services;

import DAO.BillingDAO;
import Model.Order;
import Model.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillingService implements BillingDAO  {
    @Override
    public List<OrderItem> getUserOrderItems(Integer order_id)  {

        CustomConnection conn = new CustomConnection();
        Connection con = conn.Connect();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        if  (con != null)  {

            String sql = "SELECT * FROM CSCI5308_8_DEVINT.order_items where order_id = "
                    + order_id +";";
            Statement statement = null;
            try  {
                statement = con.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    Integer order_id_db = result.getInt(8);
                    String name = result.getString(3);
                    Integer qty = result.getInt(4);
                    Double price = result.getDouble(5);
                    Double total_bill = result.getDouble(6);
                    Double final_bill = result.getDouble(7);

                    OrderItem orderItemsFromDb = new OrderItem();
                    orderItemsFromDb.setOrder_id(order_id_db);
                    orderItemsFromDb.setName(name);
                    orderItemsFromDb.setQty(qty);
                    orderItemsFromDb.setPrice(price);
                    orderItemsFromDb.setTotal_bill(total_bill);
                    orderItemsFromDb.setFinal_bill(final_bill);

                    orderItems.add(orderItemsFromDb);
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }

        return orderItems;
    }

    @Override
    public List<Order> getUserOrder(Integer user_id)  {

        CustomConnection conn = new CustomConnection();
        Connection con = conn.Connect();
        List<Order> orders = new ArrayList<Order>();

        if  (con != null)  {

            String sql = "SELECT * FROM CSCI5308_8_DEVINT.order where user_id = "
                    + user_id +" order by order_id desc;";
            Statement statement = null;

            try  {
                statement = con.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    Integer order_id_db = result.getInt(1);
                    Integer user_id_db = result.getInt(2);
                    Order orderFromDb = new Order(user_id_db);
                    orderFromDb.setOrder_id(order_id_db);
                    orders.add(orderFromDb);
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
        return orders;
    }
}
