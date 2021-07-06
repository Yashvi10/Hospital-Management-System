package DAO;

import Model.Order;
import Model.OrderItem;

public interface OrderDAO  {

    void addOrder(Order order);

    void addOrderItems(OrderItem orderItem);

    Integer getLastOrderId();
}
