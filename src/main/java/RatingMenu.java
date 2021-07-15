import Interface.FeatureMenu;
import Interface.UserIdVerifiedDAO;

import java.util.Scanner;

public class RatingMenu implements FeatureMenu {
    Scanner scanner = new Scanner(System.in);

    UserIdVerifiedDAO userIdVerifiedDAO;

    public RatingMenu(UserIdVerifiedDAO userIdVerifiedDAO) {
        this.userIdVerifiedDAO = userIdVerifiedDAO;
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
            System.out.println("User is valid");
        } else {
            System.out.println("User is invalid");
        }
    }
}
