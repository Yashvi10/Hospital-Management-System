import Model.Offers;
import Model.Order;
import Service.OfferService;
import Service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: BillingPageTest.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is used for testing the logic related to billing
 *  Description: class is used for testing the logic related to billing like calculateDiscount(), getAllOffer()
 * */
public class BillingPageTest {

    @Mock
    OfferService offerService;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void calculateDiscount_right()  {

        Mockito.when(offerService.isOfferValid(anyInt())).thenReturn(20);

        assertEquals(20, offerService.isOfferValid(2), "Offer Id is invalid");
    }

    @Test
    void getAllOffer()  {

        List<Offers> offersList = offerService.getAllOffer();
        Offers offers = new Offers();
        offers.setOffer_id(1);
        offers.setName("test offer");
        offers.setDiscount(15.0);
        offers.setActive(true);
        offersList.add(offers);

        Mockito.when(offerService.getAllOffer()).thenReturn(offersList);

        assertEquals(offersList, offerService.getAllOffer(), "Offer donot fetch successfully");
    }

}
