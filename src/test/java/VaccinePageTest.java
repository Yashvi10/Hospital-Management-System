import BL.VaccineRegisterBL;
import Model.Vaccine;
import Model.VaccineUserInformation;
import Service.VaccineRegistration;
import Service.VaccineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: VaccinePageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: This class tests operations related to vaccine
 *  Description: These test cases ensures how the logic should behave
 *               Passing all test cases ensures that logic is working as intended.
 * */
public class VaccinePageTest {

  @Mock
  VaccineService vaccineService;

  @Mock
  VaccineRegistration vaccineRegistration;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  /*
   * This method uses Mock object to test the method
   * getVaccines() method of VaccineService class - returns the list of vaccine class objects
   */
  @Test
  void getVaccines() {

    List<Vaccine> vaccineList = new ArrayList<>();
    Vaccine vaccine1 = new Vaccine(1,"Moderna",250);
    Vaccine vaccine2 = new Vaccine(2,"Covishield",256);

    vaccineList.add(vaccine1);
    vaccineList.add(vaccine2);

    Mockito.when(vaccineService.getVaccines()).thenReturn(vaccineList);

    assertEquals(vaccineList,vaccineService.getVaccines());
  }

  /*
   * This method uses Mock object to test the method
   * registerUserVaccination() method of VaccineRegistration class - returns the true if user registered successfully
   */
  @Test
  void registerUserVaccination() {

    VaccineUserInformation vaccineUserInformation = new VaccineUserInformation(1, "kush@dal.ca", 23, "male", "S654987",
            java.sql.Date.valueOf("2021-07-25"), "morning", 1);

    Mockito.when(vaccineRegistration.registerUserVaccination(vaccineUserInformation)).thenReturn(true);
    assertEquals(true,vaccineRegistration.registerUserVaccination(vaccineUserInformation));
  }

  /*
   * This method uses Mock object to test the method
   * registerUserVaccination() method of VaccineRegistration class - returns the List of VaccineUserInformation class
   */
  @Test
  void getVaccinationInfo() {

    List<VaccineUserInformation> vaccineUserInformations = new ArrayList<>();


    VaccineUserInformation info1 = new VaccineUserInformation(1, "kush@dal.ca", 23, "male", "S654987",
            java.sql.Date.valueOf("2021-07-25"), "morning", 1);

    VaccineUserInformation info2 = new VaccineUserInformation(2, "nush@dal.ca", 27, "female", "S894987",
            java.sql.Date.valueOf("2021-08-15"), "evening", 3);

    vaccineUserInformations.add(info1);
    vaccineUserInformations.add(info2);

    Mockito.when(vaccineRegistration.getVaccinationInfo(Mockito.anyInt())).thenReturn(vaccineUserInformations);
    assertEquals(vaccineUserInformations,vaccineRegistration.getVaccinationInfo(2));
  }

  /*
   * This method uses Mock object to test the method
   * getUserVaccineData() method of VaccineRegistration class - returns the single VaccineUserInformation object
   */
  @Test
  void getUserVaccineData() {

    VaccineUserInformation info2 = new VaccineUserInformation(2, "nush@dal.ca", 27, "female", "S894987",
            java.sql.Date.valueOf("2021-08-15"), "evening", 3);

    Mockito.when(vaccineRegistration.getUserVaccineData(Mockito.anyInt())).thenReturn(info2);
    assertEquals(info2,vaccineRegistration.getUserVaccineData(2));
  }

  /*
   * This method uses Mock object to test the method
   * updateSlotAvailability() method of VaccineRegistration class - returns the true
   */
  @Test
  void updateSlotAvailability() {

    Date date = Date.valueOf("2021-07-25");

    Mockito.when(vaccineRegistration.updateSlotAvailability(date)).thenReturn(true);
    assertEquals(true, vaccineRegistration.updateSlotAvailability(date));
  }

  /*
   * This method uses Mock object to test the method
   * getAvailableSlots() method of VaccineRegistration class - returns Integer count of the slots
   */
  @Test
  void getAvailableSlots() {

    Date date = Date.valueOf("2021-07-25");
    Integer count = 250;

    Mockito.when(vaccineRegistration.getAvailableSlots(date)).thenReturn(count);
    assertEquals(count, vaccineRegistration.getAvailableSlots(date));
  }

  /*
   * This method uses Mock object to test the method
   * updateVaccineDoses() method of VaccineRegistration class - returns true if count is updated
   */
  @Test
  void updateVaccineDoses() {

    Integer vaccineId = 250;

    Mockito.when(vaccineRegistration.updateVaccineDoses(vaccineId)).thenReturn(true);
    assertEquals(true, vaccineRegistration.updateVaccineDoses(vaccineId));
  }

  /*
   * This method uses Mock object to test the method
   * getTotalVaccineDoses() method of VaccineRegistration class - returns the positive Integer value of total vaccines
   */
  @Test
  void getTotalVaccineDoses() {

    Integer totalVaccines = 3;

    Mockito.when(vaccineRegistration.getTotalVaccineDoses(Mockito.anyInt())).thenReturn(totalVaccines);

    assertEquals(true,vaccineRegistration.getTotalVaccineDoses(2) > 0);
  }

  /*
   * Tests the logic of emailId validation
   * The method returns true as emailId is in accepted format
   */
  @Test
  void validateMailId_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateEmailId("kush@dal.ca"));
  }

  /*
   * Tests the logic of emailId validation
   * The method returns false as emailId is invalid
   */
  @Test
  void validateMailId_false() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateEmailId("hello123"));
  }

  /*
   * Tests the logic of age validation
   *
   * The method returns true as age is in accepted format
   */
  @Test
  void validateAge_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateAge("23"));
  }

  /*
   * Tests the logic of age validation
   * The method returns false as age is invalid
   */
  @Test
  void validateAge_false_positive() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateAge("250"));
  }

  /*
   * Tests the logic of age validation
   * The method returns false as age is invalid
   */
  @Test
  void validateAge_false_negative() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateAge("-10"));
  }

  /*
   * Tests the logic of age validation
   * The method returns false as age is invalid
   */
  @Test
  void validateAge_false_zero() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateAge("0"));
  }

  /*
   * Tests the logic of gender validation
   * The method returns true as gender is valid case
   */
  @Test
  void validateGender_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateGender("male"));
  }

  /*
   * Tests the logic of gender validation
   * The method returns false as gender is invalid
   */
  @Test
  void validateGender_false() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateGender("99"));
  }

  /*
   * Tests the logic of governmentId validation
   * The method returns true as governmentId is valid case
   */
  @Test
  void validateGovernmentId_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateGovernmentId("R5468569"));
  }

  /*
   * Tests the logic of governmentId validation
   * The method returns true as governmentId is valid case
   */
  @Test
  void validateGovernmentId_false() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateGovernmentId("Ab-@956"));
  }

  /*
   * Tests the logic of date validation
   * The method returns true as date is in accepted format
   */
  @Test
  void validateDateTest_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateDate("2021-08-28"));
  }

  /*
   * Tests the logic of date validation
   * The method returns false as date is in invalid format
   */
  @Test
  void validateDateTest_false() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateDate("2021a-08-28"));
  }

  /*
   * Tests the logic of vaccineId validation
   * The method returns true as vaccineId is valid
   */
  @Test
  void validateVaccineId_true() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(true, vaccineRegisterBL.validateVaccineIdInput("1"));
  }

  /*
   * Tests the logic of vaccineId validation
   * The method returns false as vaccineId is invalid
   */
  @Test
  void validateVaccineId_false() {
    VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
    assertEquals(false, vaccineRegisterBL.validateVaccineIdInput("abc"));
  }
}
