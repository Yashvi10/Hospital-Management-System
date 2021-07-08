import DAO.OfferDAO;
import DAO.OfferValidDAO;
import DAO.OrderDAO;
import DAO.OrderLastIdDAO;
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
 *  Name of file: BillingPage.java
 *  Author:  Nadish Maredia
 *  Purpose: This class contains different menus like checkout(), paidByCash()
 *  Description: This class basically take input from user and validate them and
 *               call different Services which we created
 * */
public class BillingPage {

    String user_id = "";

    private OrderDAO orderDAO;
    private OrderLastIdDAO orderLastIdDAO;
    private OfferValidDAO offerValidDAO;

    public BillingPage(OrderDAO orderDAO, OrderLastIdDAO orderLastIdDAO, OfferValidDAO offerValidDAO) {
        this.orderDAO = orderDAO;
        this.orderLastIdDAO = orderLastIdDAO;
        this.offerValidDAO = offerValidDAO;
    }
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

        if  (paymentType.equals(Constant.SMALL_s) || paymentType.equals(Constant.CAPITAL_S))  {
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
        orderDAO.addOrder(new Order(123));
//        orderService.addOrder(new Order(123)); //replace this with user_id
//        Integer order_id = orderService.getLastOrderId();
        Integer order_id = orderLastIdDAO.getLastOrderId();

        for  (Map.Entry me: PharmacyPage.cart.entrySet())  {
            CartItem cartItem = (CartItem) me.getValue();

            PharmacyService pharmacyService = new PharmacyService();
            pharmacyService.updateStock(me.getKey().toString(), cartItem.getQty());

            OrderItem orderItem = new OrderItem(Integer.parseInt(me.getKey().toString()),
                    cartItem.getName(),
                    cartItem.getQty(),
                    cartItem.getPrice(),
                    cartItem.getTotalPrice(),
                    PharmacyPage.finalPrice,order_id);
//            orderService.addOrderItems(orderItem);
            orderDAO.addOrderItems(orderItem);
        }
        PharmacyPage.cart.clear();
        PharmacyPage.finalPrice = 0.0;
        System.out.println("You have been successfully checkout");
        PharmacyPage pharmacyPage = new PharmacyPage(new PharmacyService(), new OfferService());
        pharmacyPage.PharmacyMenu();
    }

    public void calculateDiscount(Integer oid)  {
//        OfferService offerService = new OfferService();
//        Integer rate = offerService.isOfferValid(oid);
        Integer rate = offerValidDAO.isOfferValid(oid);

        if  (rate != 0)  {
            Double newPrice = (PharmacyPage.finalPrice * rate ) / 100;
            PharmacyPage.finalPrice = PharmacyPage.finalPrice - newPrice;
            System.out.println("Your discount bill is: " +PharmacyPage.finalPrice +" after giving " +rate +"% discount");
        }
    }

}
