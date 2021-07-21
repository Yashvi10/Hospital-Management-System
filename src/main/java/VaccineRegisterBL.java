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

  public VaccineRegisterBL() {}

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


    return null;
  }

  public Boolean getUserInformation() {
    VaccineUserInformation vaccineUserInformation = new VaccineUserInformation();

    String mailId = getMailId();
    if(mailId == null)
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

    if (registerUserVaccine(vaccineUserInformation))
      return true;
    else
      return false;
  }

  public String getMailId() {

    String mailId=null;

    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    userInput = new Scanner(System.in);
    System.out.println("Enter your MailID: ");
    mailId = userInput.next();

    mailId = mailId.trim().toLowerCase();
    matcher = pattern.matcher(mailId);

    if(!matcher.matches())
      return null;
    else
      return mailId;
  }

  public Integer getAge() {

    String age=null;

    String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    userInput = new Scanner(System.in);
    System.out.println("Enter your Age: ");
    age = userInput.next();

    age = age.trim();
    matcher = pattern.matcher(age);

    if(!matcher.matches())
      return null;
    else
      return Integer.parseInt(age);
  }

  public String getGender() {

    String gender=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your gender: ");
    gender = userInput.next();

    gender = gender.trim().toLowerCase();

    if(gender.equals("male") || gender.equals("female") || gender.equals("m") || gender.equals("f"))
      return gender;
    else
      return null;
  }

  public String getGovernmentId() {

    String governmentId=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your Government ID Number: ");
    governmentId = userInput.next();

    governmentId = governmentId.trim();

    if(governmentId.length() > 0 && governmentId.length() < 20)
      return governmentId;
    else
      return null;
  }

  public Date getDate() {

    String dateInputString;

    Date date=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your preferred date from next week in YYYY-MM-DD format: ");
    dateInputString = userInput.next();

    dateInputString = dateInputString.trim();

    date= java.sql.Date.valueOf(dateInputString);

    if(date == null)
      return null;
    else
      return date;
  }

  public String getPreferredTiming() {

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
