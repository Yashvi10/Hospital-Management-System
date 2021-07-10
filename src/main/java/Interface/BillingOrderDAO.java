package Interface;

import Model.Order;

import java.util.List;

/*
 *  Name of file: BillingOrderDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Billing model
 *               split it into another interface to apply interface segregation
 * */
public interface BillingOrderDAO {

    List<Order> getUserOrder(Integer user_id);
}
