package View;

import BL.*;
import Interface.FeatureMenu;
import Service.*;

/*
 *  Name of file: FeatureFactory.java
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
            return new PharmacyMenu(new PharmacyService(),
                    new OfferService(),
                    new Dashboard(),
                    new PharmacyBL(new PharmacyService()));
        } else if (menuType.equalsIgnoreCase(Constant.BILLING_MENU)) {
            return new BillingMenu(new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.BLOODBANK_MENU)) {
            return new BloodBank(new BloodServiceBL(new BloodService()),
                    new BloodRequesterBL(new BloodRequesterService()),
                    new BloodDonorBL(new BloodDonorService()));
        } else if (menuType.equalsIgnoreCase(Constant.LABORATORY_MENU)) {
            return new Laboratory(new ListOfTestsBL(new ListOfTestsService()),
                    new RegisterTestService(),
                    new ListOfReportsBL(new GenerateLabReportsService()),
                    new BloodReportBL(new BloodTestReportsService()),
                    new VitaminDReportBL(new VitaminDTestReportsService()),
                    new UricAcidReportBL(new UricAcidTestReportsService()),
                    new UrineReportBL(new UrineTestReportsService()));
        } else if (menuType.equalsIgnoreCase(Constant.FEEDBACK_MENU)) {
            return new RatingMenu(new UserIdVerifiedService(),
                    new RatingService(),
                    new DoctorRatingService(),
                    new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.APPOINTMENT_MENU)) {
            return new Appointments();
        } else if (menuType.equalsIgnoreCase(Constant.VACCINE_MENU)) {
            return new VaccinePage(new VaccineBL(new VaccineService()),
                    new VaccineRegisterBL(new VaccineRegistration()),
                    new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.CAMP_MENU)) {
            return new NearbyCamps(new AddCampsService(),
                    new SearchCampsBL(new SearchCampsService()),
                    new ListOfCampsBL(new ListOfCampsService()));
        } else if (menuType.equalsIgnoreCase(Constant.HELPDESK_MENU)) {
            return new HelpDeskPage(new HelpDeskFaqBL(new HelpDeskFaqService()),
                    new HelpDeskRequestRegisterBL(new HelpDeskRegisterService()),
                    new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.ACCOUNT_MENU)) {
            return new AccountsMenu();
        } else if (menuType.equalsIgnoreCase(Constant.COVID_MENU)) {
            return new CovidPage(new CovidBedBL(new CovidBedService()),
                    new CovidPlasmaBL(new CovidPlasmaService()),
                    new Dashboard());
        } else if (menuType.equalsIgnoreCase(Constant.MEDICAL_HISTORY_MENU)) {
            return new MedicalHistoryMenu();
        } else if (menuType.equalsIgnoreCase(Constant.POLICY_MENU)) {
            return new PolicyMenu();
        }

        return null;
    }
}
