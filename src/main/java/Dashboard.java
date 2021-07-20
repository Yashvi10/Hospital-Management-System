import Interface.FeatureMenu;
import Interface.IDashboard;
import Service.OfferService;
import Service.PharmacyService;

import java.util.Scanner;

/*
 *  Name of file: Dashboard.java
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
        System.out.println("Press 2 for billing");
        System.out.println("Press 3 for blood bank");
        System.out.println("Press 5 for feedback and rating");
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
        } else if (userInput.equals("5")) {
            FeatureMenu featureMenu = featureFactory.getMenu(Constant.FEEDBACK_MENU);
            featureMenu.menu();
        } else {
            System.out.println("Please select correct option");
        }

    }
}
