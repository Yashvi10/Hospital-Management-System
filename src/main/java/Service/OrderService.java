package Service;

import Interface.OrderDAO;
import Interface.OrderLastIdDAO;
import Interface.UserIdVerifiedDAO;
import Model.Order;
import Model.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *  Name of file: OrderService.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like service which will implement the OrderDAO,OrderLastIdDAO
 *  Description: This class will implement the actual logic how to process or query
 *               the DB and return the result
 * */
public class OrderService implements OrderDAO, OrderLastIdDAO, UserIdVerifiedDAO {

    CustomConnection connection = new CustomConnection();
    @Override
    public Boolean addOrder(Order order)  {

        Connection conn = connection.Connect();
        if(conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO `order` (user_id) VALUES ('"
                        +order.getUser_id() +"')";
                st.executeUpdate(SQL);
                conn.close();
                return true;
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return false;
    }

    @Override
    public Boolean addOrderItems(OrderItem orderItem)  {

        Connection conn = connection.Connect();

        if  (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO order_items " +
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
                return true;
            }  catch (SQLException throwables)  {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Integer getLastOrderId()  {

        Connection conn = connection.Connect();

        Integer lastOrderId = 0;

        if (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "Select max(order_id) from `order`";
                ResultSet rs = st.executeQuery(SQL);
                if  (rs != null)  {
                    while(rs.next())
                    {
                        lastOrderId=rs.getInt("max(order_id)");
                    }
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return lastOrderId;
    }

    @Override
    public Boolean isUserFound(Integer user_id) {

        Connection conn = connection.Connect();

        Boolean result = false;

        if(conn != null) {
            Statement st = null;
            try {
                st = conn.createStatement();
                String SQL = "SELECT count(*) FROM loginTable where userid = " +user_id +";";
                ResultSet rs = st.executeQuery(SQL);
                rs.next();

                if(rs.getInt("count(*)") > 0) {
                    return true;
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

        return result;
    }
}
