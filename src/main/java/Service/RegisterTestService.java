package Service;

import Interface.RegisterTestDAO;

import java.util.Scanner;

public class RegisterTestService implements RegisterTestDAO {

  Scanner input;

  @Override
  public void registerTest() {
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
    System.out.println("Enter contact: ");
    String contact = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter email: ");
    String email = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter gender: ");
    String gender = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter test_date: ");
    String test_date = input.next();
  }

}
