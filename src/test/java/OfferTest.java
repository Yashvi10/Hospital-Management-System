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
import static org.mockito.Matchers.anyInt;
import java.util.ArrayList;
import java.util.List;

public class OfferTest {

  @Mock
  OfferService offerService;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAllOffer() {
    List<Offers> offersList = new ArrayList<>();
    Offers offers = new Offers();
    offers.setName("test offer");
    offers.setOffer_id(1);
    offers.setActive(true);
    offers.setDiscount(25.0);
    offersList.add(offers);

    Mockito.when(offerService.getAllOffer()).thenReturn(offersList);

    Assertions.assertEquals(offersList, offerService.getAllOffer(), "There are no offers");
  }

  @Test
  void isOfferValid() {

    Mockito.when(offerService.isOfferValid(anyInt())).thenReturn(1);

    Assertions.assertEquals(1, offerService.isOfferValid(2), "Offer is not valid");
  }

}
