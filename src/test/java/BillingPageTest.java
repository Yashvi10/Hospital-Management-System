import Model.Offers;
import Services.OfferService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BillingPageTest {

    @Test
    void calculateDiscount_right(){
        // I have pass 2 as paramter which is offer id we have offer avaiable in the db with this id so it
        // will return the amount of discount rate
        assertEquals(20, new OfferService().isOfferValid(2));
    }

    @Test
    void calculateDiscount_notfound(){
        // I have pass 2245 as paramter which is offer id as this offer is not found in db so
        // will return the 0
        assertEquals(0, new OfferService().isOfferValid(2245));
    }



}
