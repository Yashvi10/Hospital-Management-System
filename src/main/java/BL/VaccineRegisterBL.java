package BL;

import Interface.VaccineRegisterUserDAO;
import Interface.VaccineRegistrationBLInterface;
import Model.VaccineUserInformation;
import Service.UserSession;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *  Name of file: VaccineRegisterBL.java
 *  Author:  Kushang Mistry
 *  Purpose: This class implements business logic and methods described in
 *            the interface named VaccineRegistrationBLInterface
 *  Description: The class is under Business Logic layer and
 *               provides vaccine user registration to the presentation layer class.
 * */
public class VaccineRegisterBL implements VaccineRegistrationBLInterface {

  // Instance of Interface
  VaccineRegisterUserDAO vaccineRegisterUserDAO;

  // Instance of the object from scanner class
  static Scanner userInput;

  // A constructor of the class
  public VaccineRegisterBL(VaccineRegisterUserDAO vaccineRegisterUserDAO) {
    this.vaccineRegisterUserDAO = vaccineRegisterUserDAO;
  }

  /*
   * Mainly initiates the process of registering the user
   * Validates the availability of slots and vaccines
   */
  @Override
  public Boolean registerUserVaccine(VaccineUserInformation vaccineUserInformation) {

    if (vaccineRegisterUserDAO.registerUserVaccination(vaccineUserInformation)) {
      vaccineRegisterUserDAO.updateSlotAvailability(vaccineUserInformation.getPreferredDate());
      vaccineRegisterUserDAO.updateVaccineDoses(vaccineUserInformation.getVaccineId());
      return true;
    } else
      return false;
  }

  /*
   * This method checks weather user is already registered or not
   * Returns integers how many doses given
   */
  @Override
  public Integer checkUserRegistration(Integer userId) {

    List<VaccineUserInformation> userInformation = vaccineRegisterUserDAO.getVaccinationInfo(userId);

    return userInformation.size();
  }

  /*
   * This method retrieves information about single user
   */
  @Override
  public VaccineUserInformation getUserDetails(Integer userId) {

    VaccineUserInformation vaccineUserInformation = vaccineRegisterUserDAO.getUserVaccineData(userId);

    if(vaccineUserInformation != null) {
      Date date = getDate();
      if (date == null)
        return null;

      String preferredTiming = getPreferredTiming();
      if (preferredTiming == null)
        return null;

      vaccineUserInformation.setPreferredDate(date);
      vaccineUserInformation.setPreferredTiming(preferredTiming);

      return vaccineUserInformation;
    }

    return null;
  }

  /*
   * This method gets all user information required for vaccine registration.
   */
  @Override
  public Boolean getUserInformation() {

    VaccineUserInformation vaccineUserInformation = new VaccineUserInformation();

    String mailId = getMailId();
    if(mailId == null)
      return false;

    Integer vaccineId = getVaccineId();
    if (vaccineId == null)
      return false;

    Integer age = getAge();
    if (age == null)
      return false;

    String gender = getGender();
    if (gender == null)
      return false;

    String governmentId = getGovernmentId();
    if (governmentId == null)
      return false;

    Date date = getDate();
    if (date == null)
      return false;

    String preferredTiming = getPreferredTiming();
    if (preferredTiming == null)
      return false;

    vaccineUserInformation.setUserId(UserSession.userId);
    vaccineUserInformation.setMailId(mailId);
    vaccineUserInformation.setAge(age);
    vaccineUserInformation.setGender(gender);
    vaccineUserInformation.setGovernmentId(governmentId);
    vaccineUserInformation.setPreferredDate(date);
    vaccineUserInformation.setPreferredTiming(preferredTiming);
    vaccineUserInformation.setVaccineId(vaccineId);

    if (registerUserVaccine(vaccineUserInformation))
      return true;
    else
      return false;
  }

  /*
   * This method retrieves information about administered doses of user
   */
  @Override
  public List<VaccineUserInformation> getDosageInformation() {

    List<VaccineUserInformation> dosageInfo = vaccineRegisterUserDAO.getVaccinationInfo(UserSession.userId);

    if(dosageInfo != null) {
      return dosageInfo;
    } else {
      return null;
    }
  }

  /*
   * Method asks for mail id
   * returns string if mail id is proper
   */
  private String getMailId() {

    String mailId=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your MailID: ");
    mailId = userInput.next();

    mailId = mailId.trim().toLowerCase();

    if(validateEmailId(mailId))
      return mailId;
    else
      return null;
  }

  /*
   * Method validates mail id
   * returns true if mail id is proper
   */
  public Boolean validateEmailId(String emailId) {

    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    matcher = pattern.matcher(emailId);

    if(matcher.matches())
      return true;
    else
      return false;
  }

  /*
   * Method asks for age
   * returns age as Integer if it is proper
   */
  private Integer getAge() {

    String age=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your Age: ");
    age = userInput.next();

    age = age.trim();

    if(validateAge(age))
      return Integer.parseInt(age);
    else
      return null;
  }

  /*
   * Method validates entered
   * returns true if age is proper
   */
  public Boolean validateAge(String age) {

    String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    matcher = pattern.matcher(age);

    if(matcher.matches())
      return true;
    else
      return false;
  }

  /*
   * Method asks for gender
   * returns string of gender if entered properly
   */
  private String getGender() {

    String gender=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your gender: ");
    gender = userInput.next();

    gender = gender.trim().toLowerCase();

    if(validateGender(gender))
      return gender;
    else
      return null;
  }

  /*
   * Method validates the gender
   * returns true if gender is proper
   */
  public Boolean validateGender(String gender) {

    if(gender.equals("male") || gender.equals("female") || gender.equals("m") || gender.equals("f"))
      return true;
    else
      return false;
  }

  /*
   * Method asks for vaccine information
   * returns vaccine ID as Integer if it is proper
   */
  private Integer getVaccineId() {

    Integer vaccineId = null;

    String userVaccineInput = null;

    userInput = new Scanner(System.in);
    System.out.println("---------------------");
    System.out.println("Approved Vaccines");
    System.out.println("---------------------\n");
    System.out.println("Press 1: Covishield");
    System.out.println("Press 2: Pfizer BioNTech");
    System.out.println("Press 3: Moderna");
    System.out.println("Press 4: Sputnik V");
    System.out.println("\nEnter your Preffered Vaccine: ");
    userVaccineInput = userInput.next();

    if(validateVaccineIdInput(userVaccineInput)) {
      vaccineId = Integer.parseInt(userVaccineInput);
      if(checkVaccineAvailability(vaccineId))
        return vaccineId;
      else
        return null;
    } else
      return null;
  }

  /*
   * Method validates the vaccine ID
   * returns true if vaccine ID falls under the proper vaccine list
   */
  public Boolean validateVaccineIdInput(String userInput) {

    String vaccineId = userInput;

    try {
      Integer.parseInt(vaccineId);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /*
   * Method validates the vaccine availability
   * returns true if vaccine is available
   * false if vaccines are less than 0 or required amount
   */
  public Boolean checkVaccineAvailability (Integer vaccineId) {

    if(vaccineRegisterUserDAO.getTotalVaccineDoses(vaccineId) != null && vaccineRegisterUserDAO.getTotalVaccineDoses(vaccineId) > 0)
      return true;
    else
      return false;
  }

  /*
   * Method asks for government ID
   * returns string if id is proper
   */
  private String getGovernmentId() {

    String governmentId=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your Government ID Number: ");
    governmentId = userInput.next();

    governmentId = governmentId.trim();

    if(validateGovernmentId(governmentId))
      return governmentId;
    else
      return null;
  }

  /*
   * Method validates the government ID
   * returns true if ID is valid (in terms of length and no extra characters as defined in method)
   */
  public Boolean validateGovernmentId(String governmentId) {

    String regex = "^[A-Za-z0-9]*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    matcher = pattern.matcher(governmentId);

    if(governmentId.length() > 0 && governmentId.length() < 20) {
      if(matcher.matches())
        return true;
      else
        return false;
    } else
      return false;
  }

  /*
   * Method asks preferred date
   * returns date if it is proper
   */
  private Date getDate() {

    String dateInputString;
    Date dateToFetch;

    userInput = new Scanner(System.in);
    System.out.println("Enter your preferred date from next week in YYYY-MM-DD format: ");
    dateInputString = userInput.next();

    dateInputString = dateInputString.trim();

    if(validateDate(dateInputString)) {
      dateToFetch = java.sql.Date.valueOf(dateInputString);
      if(checkSlotAvailability(dateToFetch))
        return dateToFetch;
      else
        return null;
    } else
      return null;
  }

  /*
   * Method validates the date
   * returns true if date is in valid format
   */
  public Boolean validateDate(String dateString) {

    String dateInputString;

    Date date=null;

    dateInputString = dateString;

    dateInputString = dateInputString.trim();

    try {
      date= java.sql.Date.valueOf(dateInputString);
    } catch (IllegalArgumentException e) {
      return false;
    }

    if(date != null)
      return true;
    else
      return false;
  }

  /*
   * Method validates the availability of vaccine slots on entered date
   * returns true if slots are available on entered date
   */
  public Boolean checkSlotAvailability(Date date) {

    if(vaccineRegisterUserDAO.getAvailableSlots(date) != null && vaccineRegisterUserDAO.getAvailableSlots(date) > 0)
      return true;
    else
      return false;
  }

  /*
   * Method asks for preferred timing (morning/afternoon/evening)
   * returns string of the timing
   * Proper word needed for the confirmation (ie. Morning)
   * Not allowed: M, m, spelling mistake etc ...
   */
  private String getPreferredTiming() {

    String timing=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your Preferred timing from Below : \n Morning | Afternoon | Evening: ");
    timing = userInput.next();

    timing = timing.trim().toLowerCase();

    if(timing.equals("morning") || timing.equals("afternoon") || timing.equals("evening"))
      return timing;
    else
      return null;
  }
}
