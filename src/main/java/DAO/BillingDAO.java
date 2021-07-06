package DAO;

import Model.Order;
import Model.OrderItem;

import java.util.List;
/*
* This interface is related to billing and will return the orders
* */
public interface BillingDAO  {

    List<OrderItem> getUserOrderItems(Integer order_id);

    List<Order> getUserOrder(Integer user_id);
}
