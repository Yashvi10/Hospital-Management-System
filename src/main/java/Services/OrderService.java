package Services;

import DAO.OrderDAO;
import Model.Order;
import Model.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
*  This class is responsible for all operations related to Orders which are define in OrderDAO
* */
public class OrderService implements OrderDAO  {
    @Override
    public void addOrder(Order order)  {

        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        if(conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO CSCI5308_8_DEVINT.order (user_id) VALUES ('"
                        +order.getUser_id() +"')";
                st.executeUpdate(SQL);
                conn.close();
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void addOrderItems(OrderItem orderItem)  {

        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        if  (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO CSCI5308_8_DEVINT.order_items " +
                        "(pid,name,qty,price,total_bill,final_bill, order_id) VALUES ('"
                        +orderItem.getPid() +"','"
                        +orderItem.getName() +"','"
                        +orderItem.getQty() +"','"
                        +orderItem.getPrice() +"','"
                        +orderItem.getTotal_bill() +"','"
                        +orderItem.getFinal_bill() +"','"
                        +orderItem.getOrder_id()
                        +"')";
                st.executeUpdate(SQL);

                conn.close();
            }  catch (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Integer getLastOrderId()  {

        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();

        Integer lastOrderId = 0;

        if (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "Select max(order_id) from CSCI5308_8_DEVINT.order";
                ResultSet rs = st.executeQuery(SQL);
                if  (rs != null)  {
                    while(rs.next())
                    {
                        lastOrderId=rs.getInt("max(order_id)");
                    }
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
        return lastOrderId;
    }
}
