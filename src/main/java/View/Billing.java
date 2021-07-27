package View;

import Interface.BillingOrderDAO;
import Interface.BillingOrderItemDAO;
import Model.Order;
import Model.OrderItem;

import java.util.List;

/*
 *  Name of file: Billing.java
 *  Author:  Nadish Maredia
 * */
public class Billing {

    private BillingOrderDAO billingOrderDAO;
    private BillingOrderItemDAO billingOrderItemDAO;

    public Billing(BillingOrderDAO billingOrderDAO) {
        this.billingOrderDAO = billingOrderDAO;
    }

    public Billing(BillingOrderItemDAO billingOrderItemDAO) {
        this.billingOrderItemDAO = billingOrderItemDAO;
    }

    public List<OrderItem> getUserOrderItems(Integer order_id){
        return this.billingOrderItemDAO.getUserOrderItems(order_id);
    }

    public List<Order> getUserOrder(Integer order_id){
        return this.billingOrderDAO.getUserOrder(order_id);
    }
}
