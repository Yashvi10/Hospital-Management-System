package View;

import Interface.FeatureMenu;
import Interface.IDashboard;

import java.util.Scanner;

/*
 *  Name of file: BL.Dashboard.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is used for showing different feature menus
 *  Description: This class is using Factory Pattern so passing name of object
 *               and it will create object and show menu for that feature
 * */
public class Dashboard implements IDashboard {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void HomeMenu(){
        System.out.println("===============================");
        System.out.println("Press 1 for Pharmacy");
        System.out.println("Press 2 for Billing");
        System.out.println("Press 3 for Blood bank");
        System.out.println("Press 4 for Laboratory");
        System.out.println("Press 5 for Feedback and rating");
        System.out.println("Press 6 for Appointments");
        System.out.println("Press 7 for Vaccine");
        System.out.println("Press 8 for Near by Camps");
        String userInput = scanner.nextLine();

        FeatureFactory featureFactory = new FeatureFactory();

        if(userInput.equals("1")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.PHARMACY_MENU);
            featureMenu.menu();
        } else if (userInput.equals("2")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.BILLING_MENU);
            featureMenu.menu();
        } else if (userInput.equals("3")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.BLOODBANK_MENU);
            featureMenu.menu();
        }else if (userInput.equals("4")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.LABORATORY_MENU);
            featureMenu.menu();
        } else if (userInput.equals("5")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.FEEDBACK_MENU);
            featureMenu.menu();
        } else if (userInput.equals("6")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.APPOINTMENT_MENU);
            featureMenu.menu();
        } else if (userInput.equals("7")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.VACCINE_MENU);
            featureMenu.menu();
        } else if (userInput.equals("8")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.CAMP_MENU);
            featureMenu.menu();
        } else {
            System.out.println("Please select correct option");
        }

    }
}