import BL.HelpDeskRequestRegisterBL;
import Model.HelpDeskFaq;
import Model.HelpDeskRequestInformation;
import Model.Vaccine;
import Service.HelpDeskFaqService;
import Service.HelpDeskRegisterService;
import Service.VaccineRegistration;
import Service.VaccineService;
import View.HelpDeskPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: HelpDeskPageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: This class tests operations related to Help Desk
 *  Description: These test cases ensures how the logic should behave
 *               Passing all test cases ensures that logic is working as intended.
 * */
public class HelpDeskPageTest {

  @Mock
  HelpDeskFaqService helpDeskFaqService;

  @Mock
  HelpDeskRegisterService helpDeskRegisterService;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }


  /*
   * This method uses Mock object to test the method
   * getHelpDeskFaq() method of HelpDeskFaqService class - returns the list of HelpDeskFaq class objects
   */
  @Test
  void getHelpDeskFaq() {

    List<HelpDeskFaq> helpDeskFaq = new ArrayList<>();
    HelpDeskFaq faq1 = new HelpDeskFaq("Emergency contact number of hospital", "Here it is: +91-8794561320");
    HelpDeskFaq faq2 = new HelpDeskFaq("What are operating hours of Covid section", "It is open 24x7.");

    helpDeskFaq.add(faq1);
    helpDeskFaq.add(faq2);

    Mockito.when(helpDeskFaqService.getHelpDeskFaq()).thenReturn(helpDeskFaq);

    assertEquals(helpDeskFaq,helpDeskFaqService.getHelpDeskFaq());
  }

  /*
   * This method uses Mock object to test the method
   * registerHelpDeskRequest() method of HelpDeskRegisterService class - returns true if request is registered
   */
  @Test
  void registerHelpDeskRequest() {

    HelpDeskRequestInformation helpDeskRequestInformation = new HelpDeskRequestInformation("When will I get my report", 1, 7);

    Mockito.when(helpDeskRegisterService.registerHelpDeskRequest(helpDeskRequestInformation)).thenReturn(true);

    assertEquals(true,helpDeskRegisterService.registerHelpDeskRequest(helpDeskRequestInformation));
  }

  /*
   * This method uses Mock object to test the method
   * getActiveRequestsOfStaffId() method of HelpDeskRegisterService class - returns total requests active to staff - helper
   */
  @Test
  void getActiveRequestsOfStaffId() {

    Mockito.when(helpDeskRegisterService.getActiveRequestsOfStaffId(Mockito.anyInt())).thenReturn(3);

    assertEquals(3,helpDeskRegisterService.getActiveRequestsOfStaffId(7));
  }

  /*
   * This method uses Mock object to test the method
   * getRandomStaffId() method of HelpDeskRegisterService class - returns staff ID
   */
  @Test
  void getRandomStaffId() {

    Mockito.when(helpDeskRegisterService.getRandomStaffId()).thenReturn(7);

    assertEquals(7,helpDeskRegisterService.getRandomStaffId());
  }

  /*
   * Tests the logic of description input validation
   * The method returns true as description is valid.
   */
  @Test
  void  checkValidateDescription_true() {
    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(true, helpDeskPage.validateDescription("When will I get vaccine?"));
  }

  /*
   * Tests the logic of description input validation
   * The method returns false as description is null.
   */
  @Test
  void  checkValidateDescription_null_false() {
    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(false, helpDeskPage.validateDescription(null));
  }

  /*
   * Tests the logic of description input validation
   * The method returns false as description is empty.
   */
  @Test
  void  checkValidateDescription_empty_false() {
    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(false, helpDeskPage.validateDescription(""));
  }

  /*
   * Tests the logic of description input validation
   *
   * The method returns false as maximum description character count is allowed 500 characters.
   * The given string has 525 characters which is invalid.
   */
  @Test
  void  checkValidateDescription_longDescription_false() {
    String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            "Quisque sed risus sit amet mauris laoreet tincidunt in in neque. Vivamus vitae lorem magna." +
            "Donec lacinia euismod nisi, non fermentum nisl aliquet in." +
            "Vivamus ornare lacus eu augue fermentum ullamcorper. Suspendisse potenti." +
            "Sed in faucibus diam. Mauris in imperdiet ligula. Nulla a consequat risus, viverra rutrum ante." +
            "Phasellus tincidunt imperdiet dictum. Pellentesque eu ex egestas, scelerisque turpis at," +
            "ultricies ligula. Vivamus mollis euismod est nec viverra.";

    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(false, helpDeskPage.validateDescription(description));
  }

  /*
   * Tests the logic of registering request input validation
   * The method returns false as the value is provided null.
   */
  @Test
  void validateAge_false_zero() {
    HelpDeskRequestRegisterBL helpDeskRequestRegisterBL = new HelpDeskRequestRegisterBL(new HelpDeskRegisterService());
    assertEquals(false, helpDeskRequestRegisterBL.getRequestInformation(null));
  }
}