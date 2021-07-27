package View;

import BL.PharmacyBL;
import Interface.*;
import Model.CartItem;
import Model.Offers;
import Model.Pharmacy;
import Service.OfferService;
import Service.OrderService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 *  Name of file: PharmacyMenu.java
 *  Author:  Nadish Maredia
 *  Purpose & Description: This class implement the FeatureMenu and is responsible for showing
 *                          different menus related to Pharmcy module
 * */
public class PharmacyMenu implements FeatureMenu {

    private PharmacyDAO pharmacyDAO;

    private OfferDAO offerDAO;

    private IDashboard dashboard;

    private IPharmacyBL iPharmacyBL;

    public PharmacyMenu(){}

    public PharmacyMenu(PharmacyDAO pharmacyDAO, OfferDAO offerDAO, IDashboard dashboard, IPharmacyBL iPharmacyBL){

        //dependency injection
        this.pharmacyDAO = pharmacyDAO;
        this.offerDAO = offerDAO;
        this.dashboard = dashboard;
        this.iPharmacyBL = iPharmacyBL;
    }



    @Override
    public void menu() throws IOException {
        System.out.println("*************************************");
        System.out.println("Press l to list all medicines\nPress e to back");
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if  (inputFromUser.equals(Constant.SMALL_l) || inputFromUser.equals(Constant.CAPITAL_L))  {
            getAllMedicines();
        }  else if  (inputFromUser.equals(Constant.SMALL_e) || inputFromUser.equals(Constant.CAPITAL_E))  {
            dashboard.homeMenu();
        }  else  {
            NotFound();
        }
    }


    public void getAllMedicines() throws IOException {

        System.out.println("=========PHARMACY LIST=========");
        System.out.println(String.format(Constant.STRING_FORMAT, "P_id") +" "
                +String.format(Constant.STRING_FORMAT, "Name") +" "
                +String.format(Constant.STRING_FORMAT, "Price")+" "
                +String.format(Constant.STRING_FORMAT, "Stock"));


        for(Pharmacy pharmacy: iPharmacyBL.getAllMedicines())  {
            String p_id = String.format(Constant.INTERGER_FORMAT, pharmacy.getP_id());
            String name = String.format(Constant.STRING_FORMAT, pharmacy.getProduct_name());
            DecimalFormat df = new DecimalFormat("#.##");
            String price = String.format(Constant.STRING_FORMAT, String.valueOf(pharmacy.getPrice()));
            String stock = String.format(Constant.INTERGER_FORMAT, pharmacy.getStock());
            System.out.println(p_id +" " +name +" " +price +" " +stock);
        }

        System.out.println("*************************************");
        System.out.println("Press b to buy\nPress c to show cart\nPress e to exit");
        System.out.println("*************************************");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(true)  {
            if  (userInput.equals(Constant.SMALL_b) || userInput.equals(Constant.CAPITAL_B))  {
                BuyItem();
            }  else if(userInput.equals(Constant.SMALL_c) || userInput.equals(Constant.CAPITAL_C))  {
                showCartItems();
                break;
            }  else if(userInput.equals(Constant.SMALL_e) || userInput.equals(Constant.CAPITAL_E))  {
                menu();
                break;
            }  else  {
                NotFound();
                break;
            }
        }
    }

    public void BuyItem() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************************");
        System.out.println("Please enter the P_id you want to buy\nPress e to exit");
        System.out.println("*************************************");
        String userInput = scanner.nextLine();

        if  (userInput.equals(Constant.CAPITAL_E) || userInput.equals(Constant.SMALL_e))  {
            getAllMedicines();
        }  else  {
            if  (iPharmacyBL.isItemInList(userInput))  {
                iPharmacyBL.addItemInCart(userInput);
                System.out.println("Item Added in cart");
            }  else  {
                System.out.println("Invalid Product Id");
            }
        }
    }


    public void showCartItems() throws IOException {

        if  (PharmacyBL.cart.size() == 0)  {
            System.out.println(Colors.C_RED +"Cart is empty" +Colors.C_RESET);
            getAllMedicines();
        }  else  {
            System.out.println("=========CART ITEM LIST=========");
            System.out.println(String.format(Constant.STRING_FORMAT, "P_id") +" "
                    +String.format(Constant.STRING_FORMAT, "Name") +" "
                    +String.format(Constant.STRING_FORMAT, "Qty") +" "
                    +String.format(Constant.STRING_FORMAT, "Price") +" "
                    +String.format(Constant.STRING_FORMAT, "Total Price"));
            showCart();
        }
        afterCartMenu();
    }

    public void showCart()  {

        for  (Map.Entry me: PharmacyBL.cart.entrySet())  {
            String p_id = String.format(Constant.INTERGER_FORMAT, me.getKey());
            CartItem cartItem = (CartItem) me.getValue();
            String name = String.format(Constant.STRING_FORMAT, cartItem.getName());
            String price = String.format(Constant.DOUBLE_FORMAT_WITHOUT_DOT, cartItem.getPrice());
            String qty = String.format(Constant.INTERGER_FORMAT, cartItem.getQty());
            String totalPrice = String.format(Constant.DOUBLE_FORMAT_WITHOUT_DOT, cartItem.getTotalPrice());
            if  (PharmacyBL.isFinalPriceVisited == false)  {
                PharmacyBL.finalPrice += cartItem.getTotalPrice();
            }
            System.out.println(p_id +" " +name +" " +qty +" " +price +" " +totalPrice);
            cartItem.setFinalPrice(PharmacyBL.finalPrice);
        }

        PharmacyBL.isFinalPriceVisited = true;
        System.out.println("Your total bill is: $" +PharmacyBL.finalPrice);
    }

    public void afterCartMenu() throws IOException {
        System.out.println("*************************************");
        System.out.println("Press c to checkout\nPress o to see offers\npress e to exit (it will remove the cart)");
        System.out.println("*************************************");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if  (userInput.equals(Constant.SMALL_c) || userInput.equals(Constant.CAPITAL_C))  {
            System.out.println("Checkout");
            checkOut();
        }  else if  (userInput.equals(Constant.SMALL_o) || userInput.equals(Constant.CAPITAL_O)) {
            ViewOffers();
        }  else if  (userInput.equals(Constant.SMALL_e) || userInput.equals(Constant.CAPITAL_E))  {
            return;
        }  else  {
            NotFound();
            afterCartMenu();
        }
    }

    public void checkOut() throws IOException {

        if  (PharmacyBL.cart.size() > 0) {
            BillingPage billingPage = new BillingPage(new OrderService(), new OrderService(), new OfferService());
            billingPage.checkOut();
        }  else  {
            System.out.println(Colors.C_RED  +  "You can't checkout, your cart is empty"  +  Colors.C_RESET);
            menu();
        }

    }

    public void NotFound(){
        System.out.println(Colors.C_RED  +  "Please select the correct option"  +  Colors.C_RESET);
    }

    public void ViewOffers() throws IOException {
        System.out.println("==========Offer List==========");

        List<Offers> offersList = offerDAO.getAllOffer();

        System.out.println(String.format(Constant.STRING_FORMAT, "Offer_Id") +" "
                +String.format(Constant.STRING_FORMAT, "Name") +" "
                +String.format(Constant.STRING_FORMAT, "Discount"));

        for(int i = 0;i<offersList.size();i++) {
            String o_id = String.format(Constant.INTERGER_FORMAT, offersList.get(i).getOffer_id());
            String name = String.format(Constant.STRING_FORMAT, offersList.get(i).getName());
            String discount = String.format(Constant.DOUBLE_FORMAT_WITHOUT_DOT, offersList.get(i).getDiscount());

            System.out.println(o_id +" " +name +" " +discount);
        }
        afterCartMenu();
    }
}
