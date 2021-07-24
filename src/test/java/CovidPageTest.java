import BL.CovidBedBL;
import BL.HelpDeskRequestRegisterBL;
import Service.CovidBedService;
import Service.HelpDeskRegisterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 *  Name of file: CovidPageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: This class tests operations related to Covid Section
 *  Description: These test cases ensures how the logic should behave
 *               Passing all test cases ensures that logic is working as intended.
 * */
public class CovidPageTest {

  /*
   * Tests the availability of ventilator beds
   *
   * The method returns true as more beds are available and not zero.
   */
  @Test
  void  checkBedAvailability() {
    CovidBedBL covidBedBL = new CovidBedBL(new CovidBedService());
    assertEquals(true, covidBedBL.getBedCount(3) > 0);
  }
}
