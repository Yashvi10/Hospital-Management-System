import Interface.FeatureMenu;
import Service.OfferService;
import Service.PharmacyService;
import Service.VaccineRegistration;
import Service.VaccineService;

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

        if(menuType.equalsIgnoreCase("PHARMACY")) {
            return new PharmacyMenu(new PharmacyService(), new OfferService());
        } else if (menuType.equalsIgnoreCase("BILLING")) {
            return new BillingMenu();
        } else if (menuType.equalsIgnoreCase("BLOODBANK")) {
            return new BloodBank();
        } else if (menuType.equalsIgnoreCase("VACCINE")) {
            return new VaccinePage(new VaccineBL(new VaccineService()), new VaccineRegisterBL(new VaccineRegistration()));
        }

        return null;
    }
}
