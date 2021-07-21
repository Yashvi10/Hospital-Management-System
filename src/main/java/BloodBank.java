import Interface.BloodDAO;
import Interface.BloodDonorDAO;
import Interface.BloodRequesterDAO;
import Model.BloodDonor;
import Model.BloodInventory;
import Model.BloodRequester;
import Service.BloodDonorService;
import Service.BloodRequesterService;
import Service.BloodService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: BloodBank.java
 *  Author:  Yashvi Lad
 *  Purpose: This is the driver class of feature BloodBank
 *  Description: This class will call methods from other files and runs functions based on requests.
 * */
public class BloodBank {

  static String contact;

  static String firstname = null;

  static String lastname = null;

  static String middlename = null;

  static String blood_group = null;

  static int blood_bottles = 0;

  static float haemoglobin = 0;

  static float weight = 0;

  static int age = 0;

  static String date = null;

  static Scanner input;

  private BloodDAO bloodDAO;

  private BloodRequesterDAO bloodRequesterDAO;

  private BloodDonorDAO bloodDonorDAO;

  public BloodBank() {
  }

  public BloodBank(BloodDAO bloodDAO, BloodRequesterDAO bloodRequesterDAO, BloodDonorDAO bloodDonorDAO) {
    this.bloodDAO = bloodDAO;
    this.bloodRequesterDAO = bloodRequesterDAO;
    this.bloodDonorDAO = bloodDonorDAO;
  }

  /* This method takes input of firstname from user
   * */
  public String firstname() {
    String firstname = null;
    input = new Scanner(System.in);
    System.out.println("Enter your firstname: ");
    firstname = input.next();

    if (firstname.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return null;
    }

    if (firstname == null || firstname.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return null;
    }
    return firstname;
  }

  /* This method takes input of middlename from user
   * */
  public String middlename() {
    input = new Scanner(System.in);
    System.out.println("Enter your middlename: ");
    middlename = input.next();

    if (middlename.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return null;
    }

    if (middlename == null || middlename.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return null;
    }
    return middlename;
  }

  /* This method takes input of lastname from user
   * */
  public static String lastname() {
    input = new Scanner(System.in);
    System.out.println("Enter your lastname: ");
    lastname = input.next();

    if (lastname.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return null;
    }

    if (lastname == null || lastname.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return null;
    }
    return lastname;
  }

  /* This method takes input of blood_group from user
   * */
  public String blood_group() {
    input = new Scanner(System.in);
    System.out.println("Enter blood group: ");
    blood_group = input.next();

    if (blood_group.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return null;
    }

    if (blood_group == null || blood_group.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return null;
    }
    return blood_group;
  }

  /* This method takes input of contact from user
   * */
  public String contact() {
    input = new Scanner(System.in);
    System.out.println("Enter your contact details: ");
    contact = input.next();

    if (contact == null || contact.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return null;
    }

    if (contact.length() > 10) {
      System.out.println("Contact number cannot be more than 10 digits!");
      return null;
    }
    return contact;
  }

  /* This method takes input of date from user
   * */
  public String Date() {
    input = new Scanner(System.in);
    System.out.println("Enter date in format YYYY-MM-DD: ");
    date = input.next();
    return date;
  }

  /* This method takes required blood bottles of blood from user
   * */
  public Integer Bloodbottles() {
    input = new Scanner(System.in);
    System.out.println("Enter required bottles of blood: ");
    blood_bottles = input.nextInt();

    if (blood_bottles == 0) {
      System.out.println("Input cannot be empty!");
      return 0;
    }
    return blood_bottles;
  }

  /* This method returns list of all available items from inventory
   * */
  public void listOfItems() {

    BloodService bloodService = new BloodService();
    List<BloodInventory> bloodInventoryList = bloodService.getAllBloodGroup();

    for (int i = 0; i < bloodInventoryList.size(); i++) {
      System.out.println(bloodInventoryList.get(i).getBlood_group() + " " + bloodInventoryList.get(i).getNumber_of_bottles());
    }

    BloodBankMainMenu();
  }

  /* This method returns list of all blood requesters
   * */
  public void listOfRequesters() {

    BloodRequesterService bloodRequesterService = new BloodRequesterService();
    List<BloodRequester> bloodRequesterList = bloodRequesterService.getAllRequesters();

    for (int i = 0; i < bloodRequesterList.size(); i++) {
      System.out.println(bloodRequesterList.get(i).getFirstname() + " " + bloodRequesterList.get(i).getMiddlename() + " " + bloodRequesterList.get(i).getLastname() + " " + bloodRequesterList.get(i).getBlood_group() + " " + bloodRequesterList.get(i).getContact() + " " + bloodRequesterList.get(i).getDate().toString());
    }

    BloodBankMainMenu();
  }

  /* This method returns list of all blood requesters
   * */
  public void listOfDonors() {

    BloodDonorService bloodDonorService = new BloodDonorService();
    List<BloodDonor> bloodDonorList = bloodDonorService.getAllDonors();

    for (int i = 0; i < bloodDonorList.size(); i++) {
      System.out.println(bloodDonorList.get(i).getFirstname() + " " + bloodDonorList.get(i).getMiddlename() + " " + bloodDonorList.get(i).getLastname() + " " + bloodDonorList.get(i).getBlood_group() + " " + bloodDonorList.get(i).getContact() + " " + bloodDonorList.get(i).getDate().toString());
    }

    BloodBankMainMenu();
  }

  /* This method takes input of age from user
   * */
  public Integer donationTest_age() {
    input = new Scanner(System.in);
    System.out.println("Enter your age: ");
    age = input.nextInt();

    if (age == 0) {
      System.out.println("Input cannot be empty!");
      return 0;
    }
    return age;
  }

  /* This method takes input of weight from user
   * */
  public Float donationTest_Weight() {
    input = new Scanner(System.in);
    System.out.println("Enter your weight: ");
    weight = input.nextFloat();

    if (weight == 0) {
      System.out.println("Input cannot be empty!");
      return 0.0f;
    }
    return weight;
  }

  /* This method takes input of haemoglobin from user
   * */
  public Float donationTest_Haemoglobin() {
    input = new Scanner(System.in);
    System.out.println("Enter your haemoglobin: ");
    haemoglobin = input.nextFloat();

    if (haemoglobin == 0) {
      System.out.println("Input cannot be empty!");
      return 0.0f;
    }
    return haemoglobin;
  }

  /* This method inserts all details of blood donor into db and updates number of blood bottles in inventory
   * */
  public void addToinventory() {

    BloodDonor bloodDonor = new BloodDonor(firstname, middlename, lastname, blood_group, contact, date);
    BloodDonorService bloodDonorService = new BloodDonorService();
    Boolean insert = bloodDonorService.addDonor(bloodDonor);
    if (insert) {
      System.out.println("Data inserted successfully");
    }

    Boolean insert1 = bloodDonorService.updateDonor(blood_group);
    if (insert1) {
      System.out.println("Bottle Update successfully");
    }

    BloodBankMainMenu();

  }

  /* This method checks if the donor can donate blood or not based on certain requiremnents
   * */
  public int donationTest() throws SQLException {
    String d = null;
    if (age < 18 || age > 65) {
      System.out.println("You are not allowed to donate blood");
    } else {
      if (weight < 45) {
        System.out.println("Not eligible!");
      } else {
        if (haemoglobin < 12.5) {
          System.out.println("Cannot donate blood.");
        } else {
          System.out.println("You passed test!");
          d = "Your age is: " + age + "\n" + "Your weight is: " + weight + "\n" + "Your haemoglobin is: " + haemoglobin;
          System.out.println(d);
        }
      }
    }
    if (d != null) {
      addToinventory();
    }
    return 1;
  }

  /* This method inserts details of blood requester into db and updates inventory on request of blood
   * */
  public void OnRequestUpdateInventory() {

    BloodRequesterService bloodRequesterService = new BloodRequesterService();

    Integer counter = bloodRequesterService.isBloodAvaiable(blood_group);
    if (counter == 0) {
      System.out.println("Requested blood group is not available at present.");
    } else {

      BloodRequester bloodRequester = new BloodRequester(firstname, middlename, lastname, blood_group, contact, date);
      Boolean insert = bloodRequesterService.addRequester(bloodRequester);
      if (insert) {
        System.out.println("Data inserted successfully");
      }

      Boolean insert1 = bloodRequesterService.updateRequester(blood_bottles, blood_group);
      if (insert1) {
        System.out.println("Bottle Update successfully");
      }

    }
    BloodBankMainMenu();
  }

  public void BloodBankMainMenu() {

    BloodBank b = new BloodBank();

    System.out.println("Press 1 for Blood Request\nPress 2 for Blood Donate\nPress 3 for List all available items in inventory\n" + "Press 4 for List of requesters\nPress 5 for List of donors\nPress 6 to exit");

    input = new Scanner(System.in);
    int choice = input.nextInt();

    switch (choice) {
      case 1:
        System.out.println("You selected request for blood option!");
        b.firstname();
        b.middlename();
        b.lastname();
        b.blood_group();
        b.contact();
        b.Date();
        b.Bloodbottles();
        b.OnRequestUpdateInventory();

        break;

      case 2:
        System.out.println("You selected blood donation");
        System.out.println("Provide your details and you will need to give some tests done to donate blood.");
        b.firstname();
        b.middlename();
        b.lastname();
        b.blood_group();
        b.contact();
        b.Date();
        b.donationTest_age();
        b.donationTest_Weight();
        b.donationTest_Haemoglobin();
        try {
          b.donationTest();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }

        break;

      case 3:
        System.out.println("You selected listing all items in inventory!");
        System.out.println("============List of Inventory============");
        b.listOfItems();

        break;

      case 4:
        System.out.println("You selected list of details of blood requesters!");
        System.out.println("============List of Requesters============");
        b.listOfRequesters();

        break;

      case 5:
        System.out.println("You selected list of details of blood donors!");
        System.out.println("============List of Donors============");
        b.listOfDonors();

      case 6:
        System.out.println("Exited");

        break;
      default:
        System.out.println("Your input is not valid. Check for valid input!");

        break;

    }
  }

}
