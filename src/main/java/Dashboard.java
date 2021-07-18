import Model.GenerateLabReports;
import Service.*;

import java.util.Scanner;

public class Dashboard {

    Scanner scanner = new Scanner(System.in);

    public void HomeMenu(){
        System.out.println("===============================");
        System.out.println("Press 1 for Pharmacy");
        System.out.println("Press 2 for billing");
        System.out.println("Press 3 for blood bank");
        System.out.println("Press 5 for Laboratory");
        String userInput = scanner.nextLine();

        if(userInput.equals("1")) {
            PharmacyPage pharmacyPage = new PharmacyPage(new PharmacyService(), new OfferService());
            pharmacyPage.PharmacyMenu();
        } else if (userInput.equals("2")) {
            BillingMenuPage billingPage = new BillingMenuPage();
            billingPage.showUserOrder();
        } else if (userInput.equals("3")) {
            BloodBank bloodBank = new BloodBank();
            bloodBank.BloodBankMainMenu();
        } else if (userInput.equals("5")) {
            Laboratory laboratory = new Laboratory(new ListOfTestsService());
            laboratory.LaboratoryMenu();
            Laboratory laboratory1 = new Laboratory(new GenerateLabReports());
            laboratory1.LaboratoryMenu();
        } else {
            System.out.println("Please select correct option");
        }

    }
}
