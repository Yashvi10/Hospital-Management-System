package DAO;

import Model.Order;

import java.util.List;

public interface BillingOrderDAO {

    List<Order> getUserOrder(Integer user_id);
}
