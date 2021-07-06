import Model.Offers;
import Model.Order;
import Model.OrderItem;
import Services.OfferService;
import Services.OrderService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillingPageTest {

    @Test
    void calculateDiscount_right()  {
        
        // I have pass 2 as paramter which is offer id we have offer avaiable in the db with this id so it
        // will return the amount of discount rate
        assertEquals(20, new OfferService().isOfferValid(2), "Offer Id is invalid");
    }

    @Test
    void calculateDiscount_notfound()  {

        // I have pass 2245 as paramter which is offer id as this offer is not found in db so
        // will return the 0
        assertEquals(0, new OfferService().isOfferValid(2245), "Offer Id is invalid");
    }

    @Test
    void getAllOffer()  {

        OfferService offerService = new OfferService();
        List<Offers> offersList = offerService.getAllOffer();
        assertEquals(true, offersList.size() > 0, "Offer donot fetch successfully");
    }

    @Test
    void addOrder(){

        Order order = new Order(999);
        order.setOrder_id(95899);

        assertEquals(true, new OrderService().addOrder(order), "Order not added in db");

    }


}
