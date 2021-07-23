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
}
