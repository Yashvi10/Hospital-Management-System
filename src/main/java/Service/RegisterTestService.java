package Service;

import Interface.RegisterTestDAO;
import Model.RegisterTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * File Name: RegisterTestService.java
 * Author: Yashvi Lad
 * */
public class RegisterTestService implements RegisterTestDAO {

  Scanner input = new Scanner(System.in);

  RegisterTest registerTest;

  static Integer test_id = 0;

  static Integer user_id = 0;

  LocalTime time_of_test = LocalTime.now();

  LocalTime report_generation_time = LocalTime.now().plusHours(3);


  @Override
  public void registerTest() {

    try {
      input = new Scanner(System.in);
      System.out.println("Enter test_id: ");
      test_id = input.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Incorrect! Only integers are allowed");
      return;
    }

    if (test_id == 0) {
      System.out.println("Invalid input");
      return;
    }

    try {
      input = new Scanner(System.in);
      System.out.println("Enter user_id: ");
      user_id = input.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Incorrect! Only integers are allowed");
      return;
    }

    if (user_id == 0) {
      System.out.println("Invalid input");
      return;
    }

    input = new Scanner(System.in);
    System.out.println("Enter firstname: ");
    String firstname = input.nextLine();

    if (firstname.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return;
    }

    if (firstname == null || firstname.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    System.out.println("Enter lastname: ");
    String lastname = input.nextLine();

    if (lastname.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return;
    }

    if (lastname == null || lastname.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    System.out.println("Enter test-name: ");
    String test_name = input.nextLine();

    if (test_name.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return;
    }

    if (test_name == null || test_name.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    System.out.println("Enter contact: ");
    String contact = input.nextLine();

    if (contact == null || contact.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    if (contact.length() < 10 || contact.length() > 10) {
      System.out.println("Contact number in incorrect");
      return;
    }

    input = new Scanner(System.in);
    System.out.println("Enter email: ");
    String email = input.nextLine();

    if (email == null || email.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    if (email.matches("^(.+)@(\\S+)$")) {
    } else {
      System.out.println("Email id cannot have any other special characters except @ and .");
    }

    System.out.println("Enter gender(Male(M)/Female(F)/Transgender(T)): ");
    String gender = input.nextLine();

    if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("T")) {
    } else {
      System.out.println("Invalid input");
      return;
    }

    if (gender.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return;
    }

    if (gender == null || gender.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    LocalDate date_of_test = LocalDate.now();
    LocalDate report_generation_date = LocalDate.now().plusDays(1);

    registerTest = new RegisterTest(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test.toString(), report_generation_date.toString(), time_of_test.toString(), report_generation_time.toString());

    System.out.println("Do you want report on the same day?\nPress Y for Yes\nPress N for No");
    Scanner input = new Scanner(System.in);
    String str = input.next();
    if (str.equals("Y") || str.equals("y")) {
      scheduler();
    } else if (str.equals("N") || str.equals("n")) {
      System.out.println("Your report will be generated on: " + report_generation_date + "." + "You can be check it from Generate Report Tab!");
      addUserDetails();
    } else {
      System.out.println("Invalid input! Press Yes or No.");
    }
  }

  @Override
  public Boolean addUserDetails() {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Boolean result = false;

    if (conn != null) {
      String SQL = "insert into registered_tests(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test,report_generation_date,time_of_test,report_generation_time) " + "values('" + registerTest.getTest_id() + "','" + registerTest.getUser_id() + "','" + registerTest.getFirstname() + "','" + registerTest.getLastname() + "','" + registerTest.getTest_name() + "','" + registerTest.getContact() + "','" + registerTest.getEmail() + "','" + registerTest.getGender() + "','" + registerTest.getDate_of_test() + "','" + registerTest.getReport_generation_date() + "','" + "" + "','" + "" + "')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        result = true;
        return result;

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
    return result;
  }

  @Override
  public Boolean scheduler() {

    System.out.println("Your report will be generated on: " + report_generation_time + "." + "You can be check it from Generate Report Tab!");

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Boolean result = false;

    if (conn != null) {
      String SQL = "insert into registered_tests(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test,report_generation_date,time_of_test,report_generation_time) " + "values('" + registerTest.getTest_id() + "','" + registerTest.getUser_id() + "','" + registerTest.getFirstname() + "','" + registerTest.getLastname() + "','" + registerTest.getTest_name() + "','" + registerTest.getContact() + "','" + registerTest.getEmail() + "','" + registerTest.getGender() + "','" + "" + "','" + "" + "','" + time_of_test.toString() + "','" + report_generation_time.toString() + "')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        result = true;
        return result;

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
    return result;
  }

}
