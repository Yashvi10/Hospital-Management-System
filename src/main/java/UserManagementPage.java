import Model.User;
import Service.CustomConnection;
import Service.UserManagement;

import java.util.Scanner;

/*
 *  Name of file: UserManagementPage.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the load page for the user Management feature
 *  Description: This class is the wrapper for the service methods in the userManagement.java service
 *
 */

public class UserManagementPage {

  CustomConnection db = new CustomConnection();

  UserManagement user = new UserManagement(db);
  Scanner scanner = new Scanner(System.in);

  public void MainMenu() {
    System.out.println("================Hospital Management System===============");
    System.out.println("Press 1 to login\nPress 2 to register as staff\nPress 3 to register as patient");

    String userInput = scanner.nextLine();

    if(userInput.equals("1")){
      Login();
    }
    else if(userInput.equals("2")){
      registerAsStaff();
    }
    else if(userInput.equals("3")){
      registerAsPatient();
    }
    else{
      System.out.println("Please select correct option");
    }

  }

  public void Login() {
    System.out.println("Enter email: ");
    String email = scanner.nextLine();

    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    User myUser = new User("", "", "", "", email, "", password, "");

    Boolean result = user.loginUser(email, password);

    if (result) {
      System.out.println("Login");
      Dashboard dashboard = new Dashboard();
      dashboard.HomeMenu();
    } else {
      System.out.println("Login Failed");
    }
  }

  public void registerAsStaff() {

    System.out.println("Enter your role(doctor, nurse): ");
    String role = scanner.nextLine();
    System.out.println("Enter firstname: ");
    String firstName = scanner.nextLine();
    System.out.println("Enter lastname: ");
    String lastName = scanner.nextLine();
    System.out.println("Enter email: ");
    String email = scanner.nextLine();
    System.out.println("Enter confirm email: ");
    String confirmEmail = scanner.nextLine();
    System.out.println("Enter password: ");
    String password = scanner.nextLine();
    System.out.println("Enter confirmPassword: ");
    String confirmPassword = scanner.nextLine();
    System.out.println("Enter address: ");
    String address = scanner.nextLine();
    System.out.println("Enter phone: ");
    String phone = scanner.nextLine();

    User myUser = new User(firstName, lastName, address, phone, email, confirmEmail, password, confirmPassword);

    boolean result = user.registerLogin(myUser, "Staff");

    if (result) {
      user = new UserManagement(db);
      myUser.setUserid(user.getLastUserId());
      result = user.registerStaff(role, myUser);
      if (result) {
        System.out.println("User Added");
      }

    } else {
      System.out.println(false);
    }
  }

  public void registerAsPatient() {

    System.out.println("Enter firstname: ");
    String firstName = scanner.nextLine();

    System.out.println("Enter lastname: ");
    String lastName = scanner.nextLine();

    System.out.println("Enter email: ");
    String email = scanner.nextLine();

    System.out.println("Enter confirm email: ");
    String confirmEmail = scanner.nextLine();

    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    System.out.println("Enter confirmPassword: ");
    String confirmPassword = scanner.nextLine();

    System.out.println("Enter address: ");
    String address = scanner.nextLine();

    System.out.println("Enter phone: ");
    String phone = scanner.nextLine();

    User myUser = new User(firstName, lastName, address, phone, email, confirmEmail, password, confirmPassword);

    boolean result = user.registerLogin(myUser, "Patient");

    if (result) {
      user = new UserManagement(db);
      myUser.setUserid(user.getLastUserId());
      result = user.registerPatient(myUser);
      if (result) {
        System.out.println("User Added");
      }
    }
  }
}
