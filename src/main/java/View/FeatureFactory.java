package View;

import BL.PharmacyBL;
import Interface.FeatureMenu;
import Service.*;

/*
 *  Name of file: BL.FeatureFactory.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is responsible for creating different objects
 *  Description: It will take menu type as parameter and based on that it will return object
 * */
public class FeatureFactory {

    public FeatureMenu getMenu(String menuType) {
        if(menuType == null) {
            return null;
        }

        if(menuType.equalsIgnoreCase(Constant.PHARMACY_MENU)) {
            return new PharmacyMenu(new PharmacyService(), new OfferService(), new Dashboard(), new PharmacyBL(new PharmacyService()));
        } else if (menuType.equalsIgnoreCase(Constant.BILLING_MENU)) {
            return new BillingMenu(new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.BLOODBANK_MENU)) {
            return new BloodBank();
        } else if (menuType.equalsIgnoreCase(Constant.LABORATORY_MENU)) {
            return new Laboratory(new ListOfTestsService(), new RegisterTestService(), new GenerateLabReportsService(),
                    new BloodTestReportsService(), new VitaminDTestReportsService(), new UricAcidTestReportsService(), new UrineTestReportsService());
        } else if (menuType.equalsIgnoreCase(Constant.FEEDBACK_MENU)) {
            return new RatingMenu(new UserIdVerifiedService(), new RatingService(), new DoctorRatingService(), new Dashboard());
        }

        return null;
    }
}
