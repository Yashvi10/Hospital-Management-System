import Interface.FeatureMenu;
import Interface.RatingDAO;
import Interface.UserIdVerifiedDAO;
import Model.AppointmentModel;
import Service.UserSession;

import java.util.List;
import java.util.Scanner;

public class RatingMenu implements FeatureMenu {
    Scanner scanner = new Scanner(System.in);

    UserIdVerifiedDAO userIdVerifiedDAO;
    RatingDAO ratingDAO;

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
        } else {
            System.out.println(Colors.C_RED +" Please select correct option " +Colors.C_RESET);
            menu();
        }
    }

    public void verifyUserMenu() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your userId");
        String userId = scanner.nextLine();

        Boolean isValid = userIdVerifiedDAO.isUserFound(Integer.parseInt(userId));
        if(isValid) {
            System.out.println("Verification successful!");
            List<AppointmentModel> userAppointments = ratingDAO.userAppointments(UserSession.userId.toString());
            for(int i = 0;i<userAppointments.size();i++) {
                System.out.println(userAppointments.get(i).getAppointment_date());
            }
        } else {
            System.out.println("User is invalid");
        }
    }
}
