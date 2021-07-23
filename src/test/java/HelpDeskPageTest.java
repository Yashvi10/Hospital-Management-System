import BL.HelpDeskRequestRegisterBL;
import BL.VaccineRegisterBL;
import Service.HelpDeskRegisterService;
import Service.VaccineRegistration;
import View.HelpDeskPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: HelpDeskPageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: This class tests operations related to Help Desk
 *  Description: These test cases ensures how the logic should behave
 *               Passing all test cases ensures that logic is working as intended.
 * */
public class HelpDeskPageTest {

  /*
   * Tests the logic of description input validation
   *
   * The method returns true as description is valid.
   */
  @Test
  void  checkValidateDescription_true() {
    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(true, helpDeskPage.validateDescription("When will I get vaccine?"));
  }

  /*
   * Tests the logic of description input validation
   *
   * The method returns false as description is null.
   */
  @Test
  void  checkValidateDescription_null_false() {
    HelpDeskPage helpDeskPage = new HelpDeskPage();
    assertEquals(false, helpDeskPage.validateDescription(null));
  }

  /*
   * Tests the logic of description input validation
   *
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
   *
   * The method returns false as the value is provided null.
   */
  @Test
  void validateAge_false_zero() {
    HelpDeskRequestRegisterBL helpDeskRequestRegisterBL = new HelpDeskRequestRegisterBL(new HelpDeskRegisterService());
    assertEquals(false, helpDeskRequestRegisterBL.getRequestInformation(null));
  }
}
