import Model.CartItem;
import Model.Pharmacy;
import Services.PharmacyService;

import java.util.Map;
import java.util.Scanner;

public class BillingPage {

    public void CheckOut(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press (c for card or s for cash): ");
        String paymentType = scanner.nextLine();
        String paymentMode = "";

        System.out.println("Enter user Id: ");
        String user_id = scanner.nextLine();

        if(paymentType.equals("c") || paymentType.equals("C")) {
            paymentMode = "Card";
        } else if (paymentType.equals("s") || paymentType.equals("S")) {
            paymentMode = "Cash";
            paidByCash();
        } else {
            System.out.println("Please select correct payment mode");
        }
    }

    public void paidByCash(){
        for(Map.Entry me: PharmacyPage.cart.entrySet()) {
            CartItem cartItem = (CartItem) me.getValue();

            PharmacyService pharmacyService = new PharmacyService();
            pharmacyService.updateStock(me.getKey().toString(), cartItem.getQty());


        }

        PharmacyPage.cart.clear();
        System.out.println("You have been successfully checkout");
        PharmacyPage pharmacyPage = new PharmacyPage();
        pharmacyPage.PharmacyMenu();
    }


}
