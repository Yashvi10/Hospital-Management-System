import Model.Offers;
import Model.Order;
import Service.OfferService;
import Service.OrderService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: BillingPageTest.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is used for testing the logic related to billing
 *  Description: class is used for testing the logic related to billing like calculateDiscount(), getAllOffer()
 * */
public class BillingPageTest {

    OfferService offerService = new OfferService();
    @Test
    void calculateDiscount_right()  {

        // I have pass 2 as paramter which is offer id we have offer avaiable in the db with this id so it
        // will return the amount of discount rate

        assertEquals(20, offerService.isOfferValid(2), "Offer Id is invalid");
    }

    @Test
    void calculateDiscount_notfound()  {

        // I have pass 2245 as paramter which is offer id as this offer is not found in db so
        // will return the 0
        assertEquals(0, offerService.isOfferValid(2245), "Offer Id is invalid");
    }

    @Test
    void getAllOffer()  {

        List<Offers> offersList = offerService.getAllOffer();
        assertEquals(true, offersList.size() > 0, "Offer donot fetch successfully");
    }

}
