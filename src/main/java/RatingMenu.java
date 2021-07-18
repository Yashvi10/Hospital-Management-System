import Interface.FeatureMenu;
import Interface.RatingDAO;
import Interface.UserIdVerifiedDAO;
import Model.AppointmentModel;
import Service.UserSession;

import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: RatingMenu.java
 *  Author:  Nadish Maredia
 *  Purpose & Description: This class implement the RatingMenu and is responsible for showing
 *                          different menus related to RatingAndFeedback module
 * */
public class RatingMenu implements FeatureMenu {
    Scanner scanner = new Scanner(System.in);

    UserIdVerifiedDAO userIdVerifiedDAO;
    RatingDAO ratingDAO;

    public RatingMenu(){}
    //dependency injection
    public RatingMenu(UserIdVerifiedDAO userIdVerifiedDAO, RatingDAO ratingDAO) {
        this.userIdVerifiedDAO = userIdVerifiedDAO;
        this.ratingDAO = ratingDAO;
    }

    @Override
    public void menu() {
        System.out.println("Submit Feedback and Rating: ");

        System.out.println("Do you want to provide a feedback?");
        System.out.println("Press (Y) for yes or (N) for no");
        String inputFromUser = scanner.nextLine();

        if(inputFromUser.equals(Constant.SMALL_y) || inputFromUser.equals(Constant.CAPITAL_Y)) {
            verifyUserMenu();
        } else if (inputFromUser.equals(Constant.SMALL_n) || inputFromUser.equals(Constant.CAPITAL_N)) {
            Dashboard dashboard = new Dashboard();
            dashboard.HomeMenu();
        }  else {
            System.out.println(Colors.C_RED +" Please select correct option " +Colors.C_RESET);
            menu();
        }
    }

    public void verifyUserMenu() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your userId");
        String userId = scanner.nextLine();

        if(isNumber(userId)) {
            Boolean isValid = userIdVerifiedDAO.isUserFound(Integer.parseInt(userId));
            if(isValid) {
                System.out.println("Verification successful!");
                List<AppointmentModel> userAppointments = ratingDAO.userAppointments(UserSession.userId.toString());
                System.out.println(String.format(Constant.STRING_FORMAT, "Appointment_Id") +" "
                                    +String.format(Constant.STRING_FORMAT, "Appointment_Date") +" "
                                    +String.format(Constant.STRING_FORMAT,"Appoinment_Time") +" "
                                    +String.format(Constant.STRING_FORMAT, "Status"));
                for(int i = 0;i<userAppointments.size();i++) {
                    System.out.println(String.format(Constant.INTERGER_FORMAT,userAppointments.get(i).getAppointment_id()) +" \t\t"
                                    +String.format(Constant.STRING_FORMAT,userAppointments.get(i).getAppointment_date()) +" \t\t"
                                    +String.format(Constant.STRING_FORMAT, userAppointments.get(i).getAppointment_time()) +" \t\t"
                                    +String.format(Constant.STRING_FORMAT, userAppointments.get(i).getAppointment_status()));
                }

                getFeedbackFromUser();
            } else {
                System.out.println(Colors.C_RED +"User is invalid" +Colors.C_RESET);
                menu();
            }
        } else {
            System.out.println(Colors.C_RED +" User Id is wrong" +Colors.C_RESET);
            menu();
        }
    }

    public Boolean isNumber(String data){
        return data.matches("[0-9]+");
    }

    public Boolean lengthBelow500(String data) {
        if(data.length()>500){
            return false;
        }
        return true;
    }

    public Boolean isValidRating(String rate) {
        return rate.matches("[1-5]");
    }

    public void getFeedbackFromUser(){
        System.out.println("Please enter the appointment id for which you want to provide feedback: ");
        String ap_id = scanner.nextLine();

        if(isNumber(ap_id)) {
            System.out.println("Enter your feedback");
            String feedback = scanner.nextLine();
            if(lengthBelow500(feedback)) {

                System.out.println("Press (S) to submit feeback or (C) to cancel it");
                String userInput = scanner.nextLine();

                if(userInput.equals("S") || userInput.equals("s")) {
                    addRating();
                } else if (userInput.equals("C") || userInput.equals("c")) {
                    Dashboard dashboard = new Dashboard();
                    dashboard.HomeMenu();
                } else {
                    System.out.println(Colors.C_RED +" Please select correct option" +Colors.C_RESET);
                    menu();
                }

            } else {
                System.out.println(Colors.C_RED +"Feedback cannot be more than 500 characters" +Colors.C_RESET);
                menu();
            }
        } else {
            System.out.println(Colors.C_RED +"Appointment id only contains numher" +Colors.C_RESET);
            menu();
        }
    }

    public void addRating(){
        System.out.println("Please provide a rating for your appointment");
        System.out.println("How good was your experience on the scale from 1 to 5");
        System.out.println("Please enter number from 1 to 5: ");
        String rate = scanner.nextLine();

        if(isValidRating(rate)) {
            System.out.println("Thank your for you feedback!!");
            Dashboard dashboard = new Dashboard();
            dashboard.HomeMenu();
        } else {
            System.out.println(Colors.C_RED +" Rating should be between 1 to 5"+ Colors.C_RESET);
            addRating();
        }

    }
}
