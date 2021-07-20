import BL.Billing;
import Interface.BillingOrderDAO;
import Interface.BillingOrderItemDAO;
import Service.BillingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: PharmacyPage.java
 *  Author:  Nadish Maredia
 *  Purpose: This class test cases related to billing logic
 * */
public class BillingTest {

    @Test
    void getUserOrder() {
        BillingOrderDAO billingOrderDAO = new BillingService();
        Billing billing = new Billing(billingOrderDAO);
        //this will return true if order is not present in db with that order id we pass otherwise return false
        assertEquals(true, billing.getUserOrder(13).size() == 0);
    }

    @Test
    void getUserItemsOrder() {
        BillingOrderItemDAO billingOrderItemDAO = new BillingService();
        Billing billing = new Billing(billingOrderItemDAO);

        //this will return true if order items are not present in db with that orderid we pass otherwise return false
        assertEquals(true, billing.getUserOrderItems(1231).size() == 0);
    }

}
