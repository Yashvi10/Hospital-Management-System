import Model.Order;
import Model.OrderItem;
import View.Billing;
import Interface.BillingOrderDAO;
import Interface.BillingOrderItemDAO;
import Service.BillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: BillingTest.java
 *  Author:  Nadish Maredia
 *  Purpose: This class test cases related to billing logic
 * */
public class BillingTest {

    @Mock
    BillingService billingService;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserOrder() {

        //FAKE OBJECT
        List<Order> orderList = new ArrayList<>();
        Order order = new Order(10);
        order.setOrder_id(1);
        orderList.add(order);

        Mockito.when(billingService.getUserOrder(anyInt())).thenReturn(orderList);

        assertEquals(orderList, billingService.getUserOrder(5), "No order found with that id");
    }

    @Test
    void getUserItemsOrder() {
        //FAKE OBJECT
        List<OrderItem> orderItemsList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(1);
        orderItem.setId(2);
        orderItem.setFinal_bill(25.0);
        orderItem.setName("Test Order");
        orderItem.setPid(2);
        orderItem.setPrice(25.0);
        orderItem.setQty(1);
        orderItem.setTotal_bill(25.0);
        orderItemsList.add(orderItem);

        Mockito.when(billingService.getUserOrderItems(anyInt())).thenReturn(orderItemsList);

        //this will return true if order is not present in db with that order id we pass otherwise return false
        assertEquals(orderItemsList, billingService.getUserOrderItems(12), "No order found with that id");
    }

}
