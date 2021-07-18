import Interface.DoctorRatingDAO;
import Interface.FeatureMenu;
import Interface.RatingDAO;
import Interface.UserIdVerifiedDAO;
import Model.AppointmentModel;
import Model.DoctorRating;
import Model.Doctors;
import Model.Feedback;
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

    DoctorRatingDAO doctorRatingDAO;

    List<AppointmentModel> userAppointments;

    List<Doctors> doctorsList;

    String feedbackVar = "";

    Integer selectedAppointmentId = 0;

    public RatingMenu(){}
    //dependency injection to reduce coupling
    public RatingMenu(UserIdVerifiedDAO userIdVerifiedDAO,
                      RatingDAO ratingDAO,
                      DoctorRatingDAO doctorRatingDAO) {
        this.userIdVerifiedDAO = userIdVerifiedDAO;
        this.ratingDAO = ratingDAO;
        this.doctorRatingDAO = doctorRatingDAO;
    }

    /*
    * This method is the main menu of Rating module
    * */
    @Override
    public void menu() {
        System.out.println("Press 1 for feedback");
        System.out.println("Press 2 to rate doctors");
        String userInput = scanner.nextLine();

        if(userInput.equals("1")) {
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
        } else if(userInput.equals("2")) {
            doctorRatingMenu();
        } else {
            System.out.println(Colors.C_RED +" Please select correct option" +Colors.C_RESET);
            menu();
        }
    }

    public void doctorRatingMenu() {
        System.out.println("==============================");
        System.out.println("\t\tLIST OF DOCTORS");
        System.out.println("==============================");

        doctorsList = doctorRatingDAO.getAllDoctors();

        for(int i = 0;i<doctorsList.size();i++) {
            System.out.println(String.format(Constant.INTERGER_FORMAT, doctorsList.get(i).getDoctor_id()) +" "
                        +String.format(Constant.STRING_FORMAT, doctorsList.get(i).getName()) +" "
                        +String.format(Constant.STRING_FORMAT, doctorsList.get(i).getSpecialization()) +" "
                        +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, doctorsList.get(i).getRating()));
        }

        System.out.println("Enter the doctor id to rate:");
        String d_id = scanner.nextLine();

        System.out.println("Please give the rating from 1 to 5:");
        String rate = scanner.nextLine();

        if (isValidDoctorId(Integer.parseInt(d_id))) {
            DoctorRating doctorRating = doctorRatingDAO.isDoctorAlreadyInDB(Integer.parseInt(d_id));
            if(doctorRating != null) {
                // already added
                Double newRating = (doctorRating.getTotal() + Double.parseDouble(rate)) / (doctorRating.getCount() + 1);
                Double total = doctorRating.getTotal() + Double.parseDouble(rate);

                Boolean result = doctorRatingDAO.updateRating(Integer.parseInt(d_id),newRating, doctorRating.getCount()+1,total);
                if(result) {
                    System.out.println("Thank you for rating the doctor");
                    menu();
                }
            } else {
                if(isValidRating(rate)) {
                    //First time adding
                    DoctorRating doctorRating1 = new DoctorRating(Integer.parseInt(d_id),
                            Double.parseDouble(rate),1,Double.parseDouble(rate));

                    doctorRatingDAO.AddFirstRating(doctorRating1);

                    System.out.println("Thank you for rating the doctor");
                    menu();
                } else {
                    System.out.println(Colors.C_RED +" Please give the rating from 1 to 5.");
                    menu();
                }

            }
        } else {
            System.out.println(Colors.C_RED +" Doctor not found with this ID" +Colors.C_RESET);
            menu();
        }
    }

    public Boolean isValidDoctorId(Integer d_id){
        for(int i = 0;i<doctorsList.size();i++) {
            if(doctorsList.get(i).getDoctor_id().toString().equals(d_id.toString())) {
                return true;
            }
        }
        return false;
    }

    /*
    * This method basically ask user for their information and verify if the user is in the db or not
    * If it is then go ahead otherwise show menu again
    * */
    public void verifyUserMenu() {

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your userId");
        String userId = scanner.nextLine();

        if(isNumber(userId)) {
            Boolean isValid = userIdVerifiedDAO.isUserFound(Integer.parseInt(userId));
            if(isValid) {
                System.out.println("Verification successful!");
                userAppointments = ratingDAO.userAppointments(UserSession.userId.toString());
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

    /*
    * Simple method to check if its a number or not
    * */
    public Boolean isNumber(String data){
        return data.matches("[0-9]+");
    }

    /*
     * Simple method to check if string is more than 500 characters
     * */
    public Boolean lengthBelow500(String data) {

        if(data.length()>500){
            return false;
        }
        return true;
    }

    /*
     * Simple method to validate the rating should be from 1 to 5
     * */
    public Boolean isValidRating(String rate) {
        return rate.matches("[1-5]");
    }

    /*
     * This method will show rating menu to the user and get rating from user
     * */
    public void getFeedbackFromUser(){

        System.out.println("Please enter the appointment id for which you want to provide feedback: ");
        String ap_id = scanner.nextLine();

        if(validAppointment(ap_id)) {

            if(isNumber(ap_id)) {

                selectedAppointmentId = Integer.parseInt(ap_id);
                System.out.println("Enter your feedback");
                String feedback = scanner.nextLine();

                if(isRequired(feedback)) {

                    if(lengthBelow500(feedback)) {

                        feedbackVar = feedback;
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
                    System.out.println(Colors.C_RED +" Feedback is required" +Colors.C_RESET);
                    menu();
                }

            } else {
                    System.out.println(Colors.C_RED +"Appointment id only contains numher" +Colors.C_RESET);
                    menu();
            }
        } else {
            System.out.println(Colors.C_RED +" Please enter correct appointment Id " +Colors.C_RESET);
            getFeedbackFromUser();
        }
    }

    /*
    * This method will validate if the appointmentID is valid or not
    * */
    public Boolean validAppointment(String appoint_id) {
        for(int i = 0;i<userAppointments.size();i++) {
            if(userAppointments.get(i).getAppointment_id().toString().equals(appoint_id)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Simple method to check if str is required or not
     * */
    public Boolean isRequired(String data) {
        if(data.length() == 0) {
            return false;
        }
        return true;
    }

    /*
     * This method will add rating and feedback to the db
     * */
    public void addRating(){
        System.out.println("Please provide a rating for your appointment");
        System.out.println("How good was your experience on the scale from 1 to 5");
        System.out.println("Please enter number from 1 to 5: ");
        String rate = scanner.nextLine();

        if(isValidRating(rate)) {

            if(!ratingDAO.feedbackExists(UserSession.userId, selectedAppointmentId)) {

                Feedback feedback = new Feedback(selectedAppointmentId, UserSession.userId,feedbackVar, Integer.parseInt(rate));

                if(ratingDAO.addFeedback(feedback)) {
                    System.out.println("Thank your for you feedback!!");
                    Dashboard dashboard = new Dashboard();
                    dashboard.HomeMenu();
                } else {
                    System.out.println(Colors.C_RED +"Sorry! There was some issue sending your feedback. Please try later"
                            +Colors.C_RESET);
                    Dashboard dashboard = new Dashboard();
                    dashboard.HomeMenu();
                }
            } else {
                System.out.println(Colors.C_RED +" You have already send feedback for this appointment " +Colors.C_RESET);
                menu();
            }
        } else {
            System.out.println(Colors.C_RED +" Rating should be between 1 to 5"+ Colors.C_RESET);
            addRating();
        }

    }
}
