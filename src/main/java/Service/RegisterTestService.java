package Service;

import Interface.RegisterTestDAO;
import Model.RegisterTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterTestService implements RegisterTestDAO {

  Scanner input = new Scanner(System.in);

  RegisterTest registerTest;

  static Integer test_id = 0;

  static Integer user_id = 0;

  @Override
  public void registerTest() {

    try{
      input = new Scanner(System.in);
      System.out.println("Enter test_id: ");
      test_id = input.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Incorrect! Only integers are allowed");
      return;
    }

    if(test_id == 0) {
      System.out.println("Invalid input");
      return;
    }

    try{
      input = new Scanner(System.in);
      System.out.println("Enter user_id: ");
      user_id = input.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Incorrect! Only integers are allowed");
      return;
    }

    if(user_id == 0) {
      System.out.println("Invalid input");
      return;
    }

    input = new Scanner(System.in);
    System.out.println("Enter firstname: ");
    String firstname = input.nextLine();

    if(firstname.matches("^[0-9]")){
      System.out.println("Your input is not valid");
      return;
    }

    if(firstname == null || firstname.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    System.out.println("Enter lastname: ");
    String lastname = input.nextLine();

    if(lastname.matches("^[0-9]")){
      System.out.println("Your input is not valid");
      return;
    }

    if(lastname == null || lastname.isEmpty()) {
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

    if(contact == null || contact.isEmpty()){
      System.out.println("Input cannot be empty!");
      return;
    }

    if(contact.length() < 10 || contact.length() > 10){
      System.out.println("Contact number in incorrect");
      return;
    }

    input = new Scanner(System.in);
    System.out.println("Enter email: ");
    String email = input.nextLine();

    if(email == null || email.isEmpty()){
      System.out.println("Input cannot be empty!");
      return;
    }

    if(email.matches("^(.+)@(\\S+)$")){
    } else {
      System.out.println("Email id cannot have any other special characters except @ and .");
    }

    System.out.println("Enter gender: ");
    String gender = input.nextLine();

    if (gender.matches("^[0-9]")) {
      System.out.println("Your input is not valid");
      return;
    }

    if (gender == null || gender.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    System.out.println("Enter date_of_test: ");
    String date_of_test = input.nextLine();

    registerTest = new RegisterTest(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test, "");

    addUserDetails();
  }

//  @Override
//  public Boolean addUserDetails() {
//    return null;
//  }

    @Override
  public Boolean addUserDetails() {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Boolean result = false;

    if(conn != null) {
//      String SQL = "insert into registered_tests(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test,report_generation_date) " +
//              "values('" + registerTest.getTest_id() + "','" + registerTest.getUser_id() + "','"
//              + registerTest.getFirstname() + "','" + registerTest.getLastname() + "','"
//              + registerTest.getTest_name() + "','" + registerTest.getContact() + "','"
//              + registerTest.getEmail() + "','" + registerTest.getGender() + "','"
//              + registerTest.getDate_of_test() + "DATE_ADD('"+registerTest.getDate_of_test()+"',interval 1 day)" + "')";

//      Statement statement = null;
      PreparedStatement preparedStatement = null;
      try {
        preparedStatement = conn.prepareStatement("insert into registered_tests(?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1,registerTest.getTest_id());
        preparedStatement.setInt(2,registerTest.getUser_id());
        preparedStatement.setString(3,registerTest.getFirstname());
        preparedStatement.setString(4,registerTest.getLastname());
        preparedStatement.setString(5,registerTest.getTest_name());
        preparedStatement.setString(6,registerTest.getContact());
        preparedStatement.setString(7,registerTest.getEmail());
        preparedStatement.setString(8,registerTest.getGender());
        preparedStatement.setString(9,registerTest.getDate_of_test());
        preparedStatement.setString(10,"DATE_ADD('"+registerTest.getDate_of_test()+"',INTERVAL 1 DAY)");
        preparedStatement.executeUpdate();
        conn.close();
        result = true;
        return result;

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return result;
  }

}
