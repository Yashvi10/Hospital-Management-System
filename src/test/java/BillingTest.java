
import DAO.BillingOrderDAO;
import DAO.BillingOrderItemDAO;
import Services.BillingService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
