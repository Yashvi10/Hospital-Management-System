package View;

import Interface.IValidateEmail;
import Model.User;
import Service.CustomConnection;
import Service.UserManagement;

import java.io.IOException;
import java.util.Scanner;

/*
 *  Name of file: UserManagementPage.java
 *  Author:  Abimbola Babalola
 *  Purpose: This is the load page for the user Management feature
 *  Description: This class is the wrapper for the service methods in the userManagement.java service
 *
 */

public class UserManagementPage extends UserManagement implements IValidateEmail {

    CustomConnection db = new CustomConnection();
    Scanner scanner = new Scanner(System.in);
    String role  ;
    String firstName ;
    String lastName  ;
    String email ;
    String confirmEmail  ;
    String password ;
    String confirmPassword  ;
    String address ;
    String phone  ;

    public void MainMenu() {

        System.out.println("Press 1 to register as staff\nPress 2 to register as patient\nPress 3 to login" +
                "\nPress 4 to Update Registration Details");
        String userInput = scanner.nextLine();
        if(userInput.equals("1")){
            registerAsStaff();
        }
        else if(userInput.equals("2")){
            registerAsPatient();
        }
        else if(userInput.equals("3")){
            Login();
        }
        else if(userInput.equals("4")){
            recordUpdate();
        }
        else{
            System.out.println("Please select correct option");
            MainMenu();
        }
    }

    public boolean Login() {
        boolean loginStatus=false;
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        String result = "";
        if (validateEmail(email)) {
            result = loginUser(db, email, password);

            if (result.trim().equals("Staff")) {
                Dashboard dashboard = new Dashboard();
                try {
                    dashboard.homeMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (result.trim().equals("Patient")) {
                Dashboard dashboard = new Dashboard();
                try {
                    dashboard.homeMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Login Failed");
                MainMenu();
            }
            loginStatus=true;
        }
        else{
            System.out.println("Email is incorrect");
            MainMenu();
        }
        return loginStatus;
    }

    public void getUserInput() {
        System.out.println("Enter firstname: ");
        firstName = scanner.nextLine();
        System.out.println("Enter lastname: ");
        lastName = scanner.nextLine();
        System.out.println("Enter email: ");
        email = scanner.nextLine();
        System.out.println("Confirm email: ");
        confirmEmail = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        System.out.println("Confirm Password: ");
        confirmPassword = scanner.nextLine();
        System.out.println("Enter address: ");
        address = scanner.nextLine();
        System.out.println("Enter phone: ");
        phone = scanner.nextLine();
    }

    public void registerAsStaff() {
        System.out.println("Enter your role(doctor, nurse): ");
        role = scanner.nextLine();
        if((role.toLowerCase().trim().equals("doctor"))||(role.toLowerCase().trim().equals("nurse"))) {
            getUserInput();
            User myUser = new User(firstName, lastName, address, phone, email, confirmEmail, password, confirmPassword);
            boolean result = false;
            if (validateEmail(myUser.getEmail())) {
                if (registerLogin(db, myUser, "Staff")) {
                    result = registerStaff(db, role, myUser);
                    if (result) {
                        System.out.println("Staff Registered");
                    }
                } else if (!(result)) {
                    System.out.println("Registration failed");
                }
            } else {
                System.out.println("Email is incorrect");
            }
            MainMenu();
        }
        else{
            System.out.println("Please Role as 'doctor' or 'nurse'");
            registerAsStaff();
        }

    }

    @Override
    public boolean validateEmail(String email){
        String regex= "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public void registerAsPatient() {
        getUserInput();
        User myUser = new User(firstName, lastName, address, phone, email, confirmEmail, password, confirmPassword);
        if(validateEmail(myUser.getEmail())) {
            boolean result = false;
            if (validateEmail(myUser.getEmail())) {
                if (registerLogin(db, myUser, "Staff")) {
                    result = registerPatient(db, myUser);
                    if (result) {
                        System.out.println("Patient Registered");
                    }
                } else if (!(result)) {
                    System.out.println("Registration failed");
                }
            }
        }
        else {
            System.out.println("Email is incorrect");
        }
        MainMenu();
    }

    public void recordUpdate(){
        if(Login()){
            getUserInput();
            User myUser = new User(firstName, lastName, address, phone, email, confirmEmail, password, confirmPassword);
            boolean result = updateProfile(  db,  myUser);
            if(result){
                System.out.println("Record Updated");
            }
            else {
                System.out.println("Update failed");
            }
            MainMenu();
        }

    }
}
