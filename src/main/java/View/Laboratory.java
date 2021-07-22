package View;

import Interface.*;
import Model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: Laboratory.java
 *  Author:  Yashvi Lad
 *  Purpose: This is the driver class of feature Laboratory
 *  Description: It provides menu to users for selecting appropriate choice and perform their task.
 * */
public class Laboratory implements FeatureMenu {

  private ListOfTestsDAO listOfTestsDAO;

  private static Integer uid_input;

  private RegisterTestDAO registerTestDAO;

  private GenerateLabReportsDAO generateLabReportsDAO;

  private BloodTestReportsDAO bloodTestReportsDAO;

  private VitaminDTestReportsDAO vitaminDTestReportsDAO;

  private UricAcidTestReportsDAO uricAcidTestReportsDAO;

  private UrineTestReportsDAO urineTestReportsDAO;

  FileWriter fileWriter;

  public Laboratory() {
  }

  ;

  public Laboratory(ListOfTestsDAO listOfTestsDAO, RegisterTestDAO registerTestDAO, GenerateLabReportsDAO generateLabReportsDAO, BloodTestReportsDAO bloodTestReportsDAO, VitaminDTestReportsDAO vitaminDTestReportsDAO, UricAcidTestReportsDAO uricAcidTestReportsDAO, UrineTestReportsDAO urineTestReportsDAO) {
    this.listOfTestsDAO = listOfTestsDAO;
    this.registerTestDAO = registerTestDAO;
    this.generateLabReportsDAO = generateLabReportsDAO;
    this.bloodTestReportsDAO = bloodTestReportsDAO;
    this.vitaminDTestReportsDAO = vitaminDTestReportsDAO;
    this.uricAcidTestReportsDAO = uricAcidTestReportsDAO;
    this.urineTestReportsDAO = urineTestReportsDAO;
  }

  /* This method offers laboratory menu to users
   * */
  @Override
  public void menu() throws IOException {

    System.out.println("===============================");
    System.out.println("Press 1 to register for test\nPress 2 for Listing all Tests\nPress 3 for Generating your test reports\n" + "Press 4 to exit");

    Scanner input = new Scanner(System.in);
    String choice = input.nextLine();

    if (choice.equals("1")) {
    } else if (choice.equals("2")) {
    } else if (choice.equals("3")) {
    } else if (choice.equals("4")) {
    } else {
      System.out.println("Please select correct option");
    }

    switch (choice) {
      case "1":
        System.out.println("============Test Registration============");
        System.out.println("**List of available tests**");
        listOfTests();
        System.out.println("\n" + "Press 1 for Vitamin D test\nPress 2 for Complete Blood Count test\nPress 3 for uric-acid test\n" + "Press 4 for urine test\nPress 5 to exit");

        Scanner test_input = new Scanner(System.in);
        String test_choice = test_input.nextLine();

        if (test_choice.equals("1")) {
        } else if (test_choice.equals("2")) {
        } else if (test_choice.equals("3")) {
        } else if (test_choice.equals("4")) {
        } else if (test_choice.equals("5")) {
        } else {
          System.out.println("Please select correct option");
        }

        switch (test_choice) {
          case "1":
            System.out.println("You selected Vitamin D checkup test.");
            registerTestDAO.registerTest();
            menu();
            break;

          case "2":
            System.out.println("You selected CBC test.");
            registerTestDAO.registerTest();
            menu();
            break;

          case "3":
            System.out.println("You selected uric-acid test.");
            registerTestDAO.registerTest();
            menu();
            break;

          case "4":
            System.out.println("You selected urine test.");
            registerTestDAO.registerTest();
            menu();
            break;

          case "5":
            System.out.println("Exited");
            menu();
            break;
        }

        break;

      case "2":
        System.out.println("============List of all tests============");
        listOfTests();
        menu();
        break;

      case "3":
        System.out.println("Generate reports");
        System.out.println("Enter your user_id");
        Scanner user_id = new Scanner(System.in);

        if (user_id.hasNextInt() == false) {
          System.out.println("Only numbers are allowed.");
          return;
        } else {
          uid_input = user_id.nextInt();
        }

        listOfReports();

        System.out.println("\n" + "Press 1 for test_id 1\nPress 2 for test_id 2\nPress 3 for test_id 3\n" + "Press 4 for test_id 4\nPress 5 to exit");

        Scanner report = new Scanner(System.in);
        String report_input = report.nextLine();

        if (report_input == null || report_input.isEmpty()) {
          System.out.println("Input cannot be empty!");
          return;
        }

        if (report_input.equals("1")) {
        } else if (report_input.equals("2")) {
        } else if (report_input.equals("3")) {
        } else if (report_input.equals("4")) {
        } else if (report_input.equals("5")) {
        } else {
          System.out.println("Please select correct option");
        }

        switch (report_input) {
          case "1":
            System.out.println("report of Vitamin D test");
            vitaminDReport();
            break;
          case "2":
            System.out.println("report of CBC test");
            bloodReport();
            break;
          case "3":
            System.out.println("report of uric-acid test");
            uric_acidReports();
            break;
          case "4":
            System.out.println("report of urine test");
            urineReports();
            break;
          case "5":
            System.out.println("Exited");
            break;
        }
        break;

      case "4":
        System.out.println("Exited");

        break;

      default:
        System.out.println("Your input is not valid. Check for valid input!");

        break;
    }
  }

  /* This method returns list of all tests from lab_tests
   */
  public void listOfTests() {
    List<ListOfTests> listOfTestsList = listOfTestsDAO.getListOfTests();

    System.out.println(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "Test Name"));

    for (int i = 0; i < listOfTestsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_name()));
    }
  }

  /* This method returns list of all reports one specified user_id have.
   * */
  public void listOfReports() {

    List<GenerateLabReports> generateLabReportsList = generateLabReportsDAO.generateReports(uid_input);

    System.out.println(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Firstname") + " " + String.format(Constant.STRING_FORMAT, "Lastname") + " " + String.format(Constant.STRING_FORMAT, "Test Name") + " " + String.format(Constant.STRING_FORMAT, "Contact") + " " + String.format(Constant.STRING_FORMAT, "Email") + " " + String.format(Constant.STRING_FORMAT, "Gender") + " " + String.format(Constant.STRING_FORMAT, "Date of test") + " " + String.format(Constant.STRING_FORMAT, "Report Generation Date") + " " + String.format(Constant.STRING_FORMAT, "Time of test") + " " + String.format(Constant.STRING_FORMAT, "Report Generation Time"));

    for (int i = 0; i < generateLabReportsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getFirstname()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getLastname()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTest_name()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getContact()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getEmail()) + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getGender()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getDate_of_test()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getReport_generation_date()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTime_of_test() + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getReport_generation_time())));
    }
  }

  /* This method prints blood test report of the entered user_id.
   * */
  public void bloodReport() throws IOException {
    List<BloodTestReports> bloodTestReportsList = bloodTestReportsDAO.bloodTestReport(uid_input);

    fileWriter = new FileWriter("resources/bloodReport"+uid_input+".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Blood Group") + " " + String.format(Constant.STRING_FORMAT, "White Blood Cells") + " " + String.format(Constant.STRING_FORMAT, "Platelet Count") + " " + String.format(Constant.STRING_FORMAT, "Blood Cell Count") + " " + String.format(Constant.STRING_FORMAT, "Hemoglobin") + " " + String.format(Constant.STRING_FORMAT, "Hematocrit"));

    for (int i = 0; i < bloodTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getBlood_group()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getWhite_blood_cell_count()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getPlatelet_count()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getRed_blood_cell_count()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getHemoglobin()) + " " + String.format(Constant.STRING_FORMAT, bloodTestReportsList.get(i).getHematocrit()));
    }
    fileWriter.close();
    menu();
  }

  /* This method prints Vitamin D report of the entered user_id.
   * */
  public void vitaminDReport() throws IOException {
    List<VitaminDTestReports> vitaminDTestReportsList = vitaminDTestReportsDAO.vitaminDReports(uid_input);

    fileWriter = new FileWriter("resources/VitaminDReport"+uid_input+".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Hydroxy VitaminD Serum") + " " + String.format(Constant.STRING_FORMAT, "Units"));

    for (int i = 0; i < vitaminDTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT, vitaminDTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, vitaminDTestReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, vitaminDTestReportsList.get(i).getHydroxy_VitaminD_serum()) + " " + String.format(Constant.STRING_FORMAT, vitaminDTestReportsList.get(i).getUnits()));
    }
    fileWriter.close();
    menu();
  }

  /* This method prints urine report of the entered user_id.
   * */
  public void urineReports() throws IOException {
    List<UrineTestReports> urineTestReportsList = urineTestReportsDAO.urineReports(uid_input);

    fileWriter = new FileWriter("resources/UrineReport"+uid_input+".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Colour") + " " + String.format(Constant.STRING_FORMAT, "Specific Gravity") + " " + String.format(Constant.STRING_FORMAT, "pH") + " " + String.format(Constant.STRING_FORMAT, "Blood") + " " + String.format(Constant.STRING_FORMAT, "Glucose") + " " + String.format(Constant.STRING_FORMAT, "Urobilinogen") + " " + String.format(Constant.STRING_FORMAT, "Protein") + " " + String.format(Constant.STRING_FORMAT, "Red blood cells") + " " + String.format(Constant.STRING_FORMAT, "Pus Cells") + " " + String.format(Constant.STRING_FORMAT, "Crystals") + " " + String.format(Constant.STRING_FORMAT, "Casts") + " " + String.format(Constant.STRING_FORMAT, "Turbidity") + " " + String.format(Constant.STRING_FORMAT, "White blood cells"));

    for (int i = 0; i < urineTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getColor()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getSpecific_gravity()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getpH()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getBlood()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getGlucose()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getUrobilinogen()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getProtein()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getRbc()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getPus_cells()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getCrystals()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getCasts()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getTurbidity()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getWbc()));
    }
    fileWriter.close();
    menu();
  }

  /* This method prints uric-acid report of the entered user_id.
   * */
  public void uric_acidReports() throws IOException {
    List<UricAcidTestReports> uricAcidTestReportsList = uricAcidTestReportsDAO.uricacidReports(uid_input);

    fileWriter = new FileWriter("resources/UricAcidReport"+uid_input+".txt");
    System.out.println(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Serum Uric Acid: %f") + " " + String.format(Constant.STRING_FORMAT, "Units"));

    for (int i = 0; i < uricAcidTestReportsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, uricAcidTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, uricAcidTestReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, uricAcidTestReportsList.get(i).getSerum_uric_acid()) + " " + String.format(uricAcidTestReportsList.get(i).getUnits(), uricAcidTestReportsList.get(i).getUnits()));
    }
    fileWriter.close();
    menu();
  }

}
