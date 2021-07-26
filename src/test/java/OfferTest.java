import Model.Offers;
import Service.OfferService;
import View.BillingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class OfferTest {

  @InjectMocks
  OfferService offerService;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAllOffer() {
    List<Offers> offersList = offerService.getAllOffer();
    Assertions.assertNotNull(offersList);
  }

  @Test
  void isOfferValid() {
    Integer result = offerService.isOfferValid(1);
    Assertions.assertNotNull(result);
  }

}
