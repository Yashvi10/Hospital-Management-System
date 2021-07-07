package DAO;

import Model.Order;
import Model.OrderItem;

import java.util.List;

/*
 *  Name of file: BillingDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling all DB operation related to Billing model
 * */
public interface BillingDAO  {

    List<OrderItem> getUserOrderItems(Integer order_id);

    List<Order> getUserOrder(Integer user_id);
}
