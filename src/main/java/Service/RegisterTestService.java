package Service;

import Interface.RegisterTestDAO;
import Model.RegisterTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RegisterTestService implements RegisterTestDAO {

  Scanner input;
  RegisterTest registerTest = new RegisterTest();

  public RegisterTestService() {}

//  public RegisterTestService(RegisterTest registerTest) {
//    this.registerTest = registerTest;
//  }

  @Override
  public Boolean registerTest() {

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    input = new Scanner(System.in);
    System.out.println("Enter test_id: ");
    String test_id = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter user_id: ");
    String user_id = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter firstname: ");
    String firstname = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter lastname: ");
    String lastname = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter test-name: ");
    String test_name = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter contact: ");
    String contact = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter email: ");
    String email = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter gender: ");
    String gender = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter date_of_test: ");
    String date_of_test = input.next();

    Boolean result = false;

    if(conn != null) {
      String SQL = "insert into registered_tests(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test) " +
              "values('" +registerTest.getTest_id() +"','" +registerTest.getUser_id()
              +"','" +registerTest.getFirstname() +"','" +registerTest.getLastname()+ "','" +registerTest.getTest_name() +"','"
              + registerTest.getContact() + "','" + registerTest.getEmail()+ "','"+registerTest.getGender()+"','"+registerTest.getDate_of_test()+"')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
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
