package DAO;

import Model.Order;
import Model.OrderItem;

import java.util.List;

public interface BillingDAO {
    List<OrderItem> getUserOrderItems(Integer order_id);

    List<Order> getUserOrder(Integer user_id);
}
