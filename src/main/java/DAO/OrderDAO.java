package DAO;

import Model.Order;
import Model.OrderItem;

public interface OrderDAO  {

    Boolean addOrder(Order order);

    Boolean addOrderItems(OrderItem orderItem);

    Integer getLastOrderId();
}
