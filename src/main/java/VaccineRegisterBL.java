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

  VaccineRegisterUserDAO vaccineRegisterUserDAO;

  static Scanner userInput;

  //public VaccineRegisterBL() {}

  public VaccineRegisterBL(VaccineRegisterUserDAO vaccineRegisterUserDAO) {
    this.vaccineRegisterUserDAO = vaccineRegisterUserDAO;
  }

  public Boolean registerUserVaccine(VaccineUserInformation vaccineUserInformation) {
    if (vaccineRegisterUserDAO.registerUserVaccination(vaccineUserInformation))
      return true;
    else
      return false;
  }

  public Integer checkUserRegistration(Integer userId) {
    List<VaccineUserInformation> userInformation = vaccineRegisterUserDAO.getVaccinationInfo(userId);
    return userInformation.size();
  }

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

  public Boolean validateGender(String gender) {

    if(gender.equals("male") || gender.equals("female") || gender.equals("m") || gender.equals("f"))
      return true;
    else
      return false;
  }

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

    if(validateVaccineIdInput(userVaccineInput)) {
      vaccineId = Integer.parseInt(userVaccineInput);
      if(checkVaccineAvailability(vaccineId))
        return vaccineId;
      else
        return null;
    }
    else
      return null;
  }

  public Boolean validateVaccineIdInput(String userInput) {
    String vaccineId = userInput;

    try {
      Integer.parseInt(vaccineId);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public Boolean checkVaccineAvailability (Integer vaccineId) {
    if(vaccineRegisterUserDAO.getTotalVaccineDoses(vaccineId) != null && vaccineRegisterUserDAO.getTotalVaccineDoses(vaccineId) > 0)
      return true;
    else
      return false;
  }

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
    }
    else
      return false;
  }

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
    }
    else
      return null;
  }

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

  public Boolean checkSlotAvailability(Date date) {
    if(vaccineRegisterUserDAO.getAvailableSlots(date) != null && vaccineRegisterUserDAO.getAvailableSlots(date) > 0)
      return true;
    else
      return false;
  }

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
