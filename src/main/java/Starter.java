import Services.PharmacyService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Starter {
    public static void main(String[] args) {
        System.out.println("Working");

        PharmacyPage pharmacyPage = new PharmacyPage();
        pharmacyPage.PharmacyMenu();
    }
}
