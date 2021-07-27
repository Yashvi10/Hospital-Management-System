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

  private ListOfTestsBLDAO listOfTestsBLDAO;

  private static Integer uid_input;

  private RegisterTestDAO registerTestDAO;

  private GenerateLabReportsDAO generateLabReportsDAO;

  private ListOfReportsBLDAO listOfReportsBLDAO;

  private BloodTestReportsDAO bloodTestReportsDAO;

  private VitaminDTestReportsDAO vitaminDTestReportsDAO;

  private UricAcidTestReportsDAO uricAcidTestReportsDAO;

  private UrineTestReportsDAO urineTestReportsDAO;

  Dashboard dashboard = new Dashboard();

  public Laboratory() {};

  public Laboratory(ListOfTestsBLDAO listOfTestsBLDAO, RegisterTestDAO registerTestDAO, ListOfReportsBLDAO listOfReportsBLDAO, BloodTestReportsDAO bloodTestReportsDAO, VitaminDTestReportsDAO vitaminDTestReportsDAO, UricAcidTestReportsDAO uricAcidTestReportsDAO, UrineTestReportsDAO urineTestReportsDAO) {
    this.listOfTestsBLDAO = listOfTestsBLDAO;
    this.registerTestDAO = registerTestDAO;
    //    this.generateLabReportsDAO = generateLabReportsDAO;
    this.bloodTestReportsDAO = bloodTestReportsDAO;
    this.vitaminDTestReportsDAO = vitaminDTestReportsDAO;
    this.uricAcidTestReportsDAO = uricAcidTestReportsDAO;
    this.urineTestReportsDAO = urineTestReportsDAO;
    this.listOfReportsBLDAO = listOfReportsBLDAO;
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
        listOfTestsBLDAO.getTests();
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
        listOfTestsBLDAO.getTests();
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
        listOfReportsBLDAO.getReport(uid_input);

        System.out.println("\n" + "Press 1 for test_id 1\nPress 2 for test_id 2\nPress 3 for test_id 3\n" + "Press 4 for test_id 4\nPress 5 to exit");

        Scanner report = new Scanner(System.in);
        String report_input = report.nextLine();

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
            vitaminDTestReportsDAO.vitaminDReports(uid_input);
            menu();
            break;
          case "2":
            System.out.println("report of CBC test");
            bloodTestReportsDAO.bloodTestReport(uid_input);
            menu();
            break;
          case "3":
            System.out.println("report of uric-acid test");
            uricAcidTestReportsDAO.uricacidReports(uid_input);
            menu();
            break;
          case "4":
            System.out.println("report of urine test");
            urineTestReportsDAO.urineReports(uid_input);
            menu();
            break;
          case "5":
            System.out.println("Exited");
            menu();
            break;
        }
        break;

      case "4":
        System.out.println("Exited");
        dashboard.homeMenu();
        break;

      default:
        System.out.println("Your input is not valid. Check for valid input!");

        break;
    }
  }

}
