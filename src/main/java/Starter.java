import Services.OfferService;
import Services.PharmacyService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Starter {
    public static void main(String[] args)  {

        PharmacyPage pharmacyPage = new PharmacyPage(new PharmacyService(), new OfferService());
        pharmacyPage.PharmacyMenu();
    }
}
