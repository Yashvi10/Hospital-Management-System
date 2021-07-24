import BL.CovidBedBL;
import Service.CovidBedService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

  /*
   * Tests the type of bed (General/Oxygen/Ventilator)
   *
   * The method returns true as number 1 resembles to g (General Bed).
   */
  @Test
  void validateBedType_general_true() {
    CovidBedService covidBedService = new CovidBedService();
    assertEquals(true,covidBedService.validateBedtype(1).equals("g"));
  }

  /*
   * Tests the type of bed (General/Oxygen/Ventilator)
   *
   * The method returns true as number 0 is boundary condition and methods returns null.
   */
  @Test
  void validateBedType_zero_true() {
    CovidBedService covidBedService = new CovidBedService();
    assertEquals(true,covidBedService.validateBedtype(0) == null);
  }

  /*
   * Tests the type of bed (General/Oxygen/Ventilator)
   * In scope numbers are (1/2/3)
   * The method returns true as number 0 is out of scope negative number and methods returns null.
   */
  @Test
  void validateBedType_negative_true() {
    CovidBedService covidBedService = new CovidBedService();
    assertEquals(true,covidBedService.validateBedtype(-5) == null);
  }

  /*
   * Tests the type of bed (General/Oxygen/Ventilator)
   * In scope numbers are (1/2/3)
   * The method returns true as number 0 is out of scope positive number and methods returns null.
   */
  @Test
  void validateBedType_outOfBoundary_true() {
    CovidBedService covidBedService = new CovidBedService();
    assertEquals(true,covidBedService.validateBedtype(5) == null);
  }
}
