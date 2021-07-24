package Interface;

import Model.Order;
import Model.OrderItem;

/*
 *  Name of file: OrderDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Order and OrderItem model
 * */
public interface OrderDAO {

    Boolean addOrder(Order order);

    Boolean addOrderItems(OrderItem orderItem);
}
