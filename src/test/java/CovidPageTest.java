import BL.CovidBedBL;
import BL.CovidPlasmaBL;
import Model.CovidPlasmaInformation;
import Model.Vaccine;
import Service.CovidBedService;
import Service.CovidPlasmaService;
import Service.VaccineRegistration;
import Service.VaccineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: CovidPageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: This class tests operations related to Covid Section
 *  Description: These test cases ensures how the logic should behave
 *               Passing all test cases ensures that logic is working as intended.
 * */
public class CovidPageTest {

  @Mock
  CovidBedService covidBedService;

  @Mock
  CovidPlasmaService covidPlasmaService;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  /*
   * This method uses Mock object to test the method
   * getTotalBeds() method of CovidBedService class - returns available beds based on bed type
   */
  @Test
  void getTotalBeds() {

    Mockito.when(covidBedService.getTotalBeds(Mockito.anyInt())).thenReturn(5);

    assertEquals(5,covidBedService.getTotalBeds(3));
  }

  /*
   * This method uses Mock object to test the method
   * registerBed() method of CovidBedService class - returns bed number which registered
   */
  @Test
  void registerBed() {

    Mockito.when(covidBedService.registerBed(Mockito.anyInt())).thenReturn(3);

    assertEquals(3,covidBedService.registerBed(3));
  }

  /*
   * This method uses Mock object to test the method
   * getDesiredBed() method of CovidBedService class - returns bed number which registered
   */
  @Test
  void getDesiredBed() {

    Mockito.when(covidBedService.getDesiredBed(Mockito.anyInt())).thenReturn(4);

    assertEquals(4,covidBedService.getDesiredBed(2));
  }

  /*
   * This method uses Mock object to test the method
   * changeAvailability() method of CovidBedService class - returns true if availability of bed is changed
   */
  @Test
  void changeAvailability() {

    Mockito.when(covidBedService.changeAvailability(Mockito.anyInt())).thenReturn(true);

    assertEquals(true,covidBedService.changeAvailability(2));
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

  /*
   * This method uses Mock object to test the method
   * showPlasmaAvailability() method of CovidPlasmaService class - returns the list of CovidPlasmaInformation class objects
   */
  @Test
  void showPlasmaAvailability() {

    List<CovidPlasmaInformation> covidPlasmaInformation = new ArrayList<>();
    CovidPlasmaInformation info1 = new CovidPlasmaInformation(1,"A+",150);
    CovidPlasmaInformation info2 = new CovidPlasmaInformation(3,"AB+",7);

    covidPlasmaInformation.add(info1);
    covidPlasmaInformation.add(info2);

    Mockito.when(covidPlasmaService.showPlasmaAvailability()).thenReturn(covidPlasmaInformation);

    assertEquals(covidPlasmaInformation,covidPlasmaService.showPlasmaAvailability());
  }
}
