package Service;

import Interface.RegisterTestDAO;
import Model.RegisterTest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RegisterTestService implements RegisterTestDAO {

  Scanner input = new Scanner(System.in);

  RegisterTest registerTest;

  @Override
  public void registerTest() {

    System.out.println("Enter test_id: ");
    Integer test_id = input.nextInt();

    System.out.println("Enter user_id: ");
    Integer user_id = input.nextInt();

    input = new Scanner(System.in);
    System.out.println("Enter firstname: ");
    String firstname = input.nextLine();

    System.out.println("Enter lastname: ");
    String lastname = input.nextLine();

    System.out.println("Enter test-name: ");
    String test_name = input.nextLine();

    System.out.println("Enter contact: ");
    String contact = input.nextLine();

    System.out.println("Enter email: ");
    String email = input.nextLine();

    System.out.println("Enter gender: ");
    String gender = input.nextLine();

    System.out.println("Enter date_of_test: ");
    String date_of_test = input.nextLine();

    registerTest = new RegisterTest(test_id,user_id,firstname,lastname,test_name,contact,email,gender,date_of_test);

    addUserDetails();
  }

  @Override
  public Boolean addUserDetails() {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Boolean result = false;

    if(conn != null) {
      String SQL = "insert into registered_tests(test_id, user_id, firstname, lastname, test_name, contact, email, gender, date_of_test) " +
              "values('" + registerTest.getTest_id() + "','" + registerTest.getUser_id() + "','"
              + registerTest.getFirstname() + "','" + registerTest.getLastname() + "','"
              + registerTest.getTest_name() + "','" + registerTest.getContact() + "','"
              + registerTest.getEmail() + "','" + registerTest.getGender() + "','"
              + registerTest.getDate_of_test() + "')";

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
