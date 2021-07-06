import Model.CartItem;
import Model.Order;
import Model.OrderItem;
import Model.Pharmacy;
import Services.OfferService;
import Services.OrderService;
import Services.PharmacyService;

import java.util.Map;
import java.util.Scanner;
/*
 * This class will hold everything which user see on their console related to billing
 * */
public class BillingPage {

    String user_id = "";
    /*
     * This is the Main checkout function which will call another methods like paidByCash
     * */
    public void CheckOut()  {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press (s for cash): ");
        String paymentType = scanner.nextLine();
        String paymentMode = "";

        System.out.println("Enter user Id: ");
        user_id = scanner.nextLine();

        if  (paymentType.equals("c") || paymentType.equals("C"))  {
            paymentMode = "Card";
        }  else if  (paymentType.equals("s") || paymentType.equals("S"))  {
            paymentMode = "Cash";
            paidByCash();
        }  else  {
            System.out.println("Please select correct payment mode");
        }
    }

    /*
     * This is the paid by cash function it will update the stock and clear the cart
     * */
    public void paidByCash()  {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Offer id:");
        String offer_id = scanner.nextLine();

        Integer o_id = 0;

        if  (!offer_id.equals(""))  {
            o_id = Integer.parseInt(offer_id);
        }

        calculateDiscount(o_id);

        OrderService orderService = new OrderService();
        orderService.addOrder(new Order(123)); //replace this with user_id
        Integer order_id = orderService.getLastOrderId();

        for  (Map.Entry me: PharmacyPage.cart.entrySet())  {
            CartItem cartItem = (CartItem) me.getValue();

            PharmacyService pharmacyService = new PharmacyService();
            pharmacyService.updateStock(me.getKey().toString(), cartItem.getQty());

            OrderItem orderItem = new OrderItem(Integer.parseInt(me.getKey().toString()),cartItem.getName(),cartItem.getQty(),cartItem.getPrice(),cartItem.getTotalPrice(),PharmacyPage.finalPrice,order_id);
            orderService.addOrderItems(orderItem);
        }
        PharmacyPage.cart.clear();
        PharmacyPage.finalPrice = 0.0;
        System.out.println("You have been successfully checkout");
        PharmacyPage pharmacyPage = new PharmacyPage();
        pharmacyPage.PharmacyMenu();
    }

    public void calculateDiscount(Integer oid)  {
        OfferService offerService = new OfferService();
        Integer rate = offerService.isOfferValid(oid);

        if  (rate != 0)  {
            Double newPrice = (PharmacyPage.finalPrice * rate ) / 100;
            PharmacyPage.finalPrice = PharmacyPage.finalPrice - newPrice;
            System.out.println("Your discount bill is: " +PharmacyPage.finalPrice +" after giving " +rate +"% discount");
        }
    }

}
