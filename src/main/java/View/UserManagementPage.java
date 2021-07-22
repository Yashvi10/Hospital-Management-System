package View;

import Interface.IRegistration;
import Model.User;
import Service.CustomConnection;
import Service.UserManagement;

import java.io.IOException;
import java.util.Scanner;

public class UserManagementPage {

    CustomConnection db=new CustomConnection();
    UserManagement user = new UserManagement(db.Connect());
    IRegistration register=new UserManagement(db.Connect());
    Scanner scanner = new Scanner(System.in);

    public void MainMenu() throws IOException {
        System.out.println("================Hospital Management System===============");
        System.out.println("Press 1 to login\nPress 2 to register as staff\nPress 3 to register as patient");

        String userInput = scanner.nextLine();


        if(userInput.equals("1")) {
            Login();
        } else if (userInput.equals("2")) {
            registerAsStaff();
        } else if (userInput.equals("3")) {
            registerAsPatient();
        } else {
            System.out.println("Please select correct option");
        }
    }

    public void Login() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User myUser=new User("","","","",email,"",password,"",register);

        Boolean result = user.loginUser(myUser);

        if(result) {
            System.out.println("Login");
            Dashboard dashboard = new Dashboard();
            dashboard.HomeMenu();
        } else {
            System.out.println("Login Failed");
            MainMenu();
        }
    }

    public void registerAsStaff(){

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

        User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,password,confirmPassword,register );
//        String result = user.registerStaff(role,myUser );

        String result = user.registerLogin(myUser);

        if(result.equals("Login added")) {
            user = new UserManagement(db.Connect());
            myUser.setUserid(user.getLastUserId());
            result = user.registerStaff(role,myUser );
            if(result.equals("Staff record added")) {
                System.out.println("User Added");
            }

        } else {
            System.out.println(result);
        }
    }

    public void registerAsPatient(){

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

        User myUser=new User(firstName,lastName,address,phone,email,confirmEmail,password,confirmPassword,register );
//        String result = user.registerStaff(role,myUser );

        String result = user.registerLogin(myUser);

        if(result.equals("Login added")) {
            user = new UserManagement(db.Connect());
            myUser.setUserid(user.getLastUserId());
            result = user.registerPatient(myUser );
            if(result.equals("Staff record added")) {
                System.out.println("User Added");
            }
        } else {
            System.out.println(result);
        }
    }
}
