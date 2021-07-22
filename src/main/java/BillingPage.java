import Interface.FeatureMenu;
import Interface.OfferValidDAO;
import Interface.OrderDAO;
import Interface.OrderLastIdDAO;
import Model.CartItem;
import Model.Order;
import Model.OrderItem;
import Service.OrderService;
import Service.PharmacyService;

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
    public void paidByCash()   {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Offer id:");
        String offer_id = scanner.nextLine();

        Integer o_id = 0;

        if  (!offer_id.equals(""))  {
            o_id = Integer.parseInt(offer_id);
        }

        calculateDiscount(o_id);

        System.out.println("Enter User id:");
        String user_id = scanner.nextLine();

        OrderService orderService = new OrderService();

        if(orderService.isUserFound(Integer.parseInt(user_id))) {

            orderDAO.addOrder(new Order(Integer.parseInt(user_id)));
            Integer order_id = orderLastIdDAO.getLastOrderId();

            for  (Map.Entry me: PharmacyMenu.cart.entrySet())  {
                CartItem cartItem = (CartItem) me.getValue();

                PharmacyService pharmacyService = new PharmacyService();
                pharmacyService.updateStock(me.getKey().toString(), cartItem.getQty());

                OrderItem orderItem = new OrderItem(Integer.parseInt(me.getKey().toString()),
                        cartItem.getName(),
                        cartItem.getQty(),
                        cartItem.getPrice(),
                        cartItem.getTotalPrice(),
                        PharmacyMenu.finalPrice,order_id);
                orderDAO.addOrderItems(orderItem);
            }
            PharmacyMenu.cart.clear();
            PharmacyMenu.finalPrice = 0.0;
            System.out.println("You have been successfully checkout");
//            PharmacyMenu pharmacyPage = new PharmacyMenu(new PharmacyService(), new OfferService());
//            pharmacyPage.menu();
            FeatureFactory featureFactory = new FeatureFactory();
            FeatureMenu featureMenu = featureFactory.getMenu("PHARMACY");
            featureMenu.menu();
        } else {
            System.out.println(Colors.C_RED +"User with this id not found" +Colors.C_RESET);
            paidByCash();
        }
    }

    public void calculateDiscount(Integer oid)  {

        Integer rate = offerValidDAO.isOfferValid(oid);

        if  (rate != 0)  {
            Double newPrice = (PharmacyMenu.finalPrice * rate ) / 100;
            PharmacyMenu.finalPrice = PharmacyMenu.finalPrice - newPrice;
            System.out.println("Your discount bill is: " +PharmacyMenu.finalPrice +" after giving " +rate +"% discount");
        }
    }
}
