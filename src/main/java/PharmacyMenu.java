import Interface.FeatureMenu;
import Interface.OfferDAO;
import Interface.PharmacyDAO;
import Model.CartItem;
import Model.Offers;
import Model.Pharmacy;
import Service.OfferService;
import Service.OrderService;

import java.text.DecimalFormat;
import java.util.HashMap;
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

    List<Pharmacy> listOfMedicines;

    public static HashMap<Integer, CartItem> cart = new HashMap<Integer, CartItem>();

    public static Double finalPrice = 0.0;

    public static Boolean isFinalPriceVisited = false;

    private PharmacyDAO pharmacyDAO;

    private OfferDAO offerDAO;

    public PharmacyMenu(){}

    public PharmacyMenu(PharmacyDAO pharmacyDAO, OfferDAO offerDAO){

        //dependency injection
        this.pharmacyDAO = pharmacyDAO;
        this.offerDAO = offerDAO;

        loadMedicines();
    }

    public void loadMedicines() {

        listOfMedicines = pharmacyDAO.getAllMedicines();
    }

    /*
     * This is the Menu for pharmacy module
     * */
    @Override
    public void menu() {
        System.out.println("*************************************");
        System.out.println("Press l to list all medicines\nPress e to back");
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if  (inputFromUser.equals(Constant.SMALL_l) || inputFromUser.equals(Constant.CAPITAL_L))  {
            getAllMedicines();
        }  else if  (inputFromUser.equals(Constant.SMALL_e) || inputFromUser.equals(Constant.CAPITAL_E))  {
            Dashboard dashboard = new Dashboard();
            dashboard.HomeMenu();
        }  else  {
            NotFound();
        }
    }


    /*
     * This is the Function which will show list of all available medicines
     * */
    public void getAllMedicines(){

        System.out.println("=========PHARMACY LIST=========");
        System.out.println(String.format(Constant.STRING_FORMAT, "P_id") +" "
                +String.format(Constant.STRING_FORMAT, "Name") +" "
                +String.format(Constant.STRING_FORMAT, "Price")+" "
                +String.format(Constant.STRING_FORMAT, "Stock"));


        for(Pharmacy pharmacy: listOfMedicines)  {
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

    /*
     * This is the function from which user can buy specific medicines input will be product id
     * */
    public void BuyItem()  {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************************");
        System.out.println("Please enter the P_id you want to buy\nPress e to exit");
        System.out.println("*************************************");
        String userInput = scanner.nextLine();

        if  (userInput.equals(Constant.CAPITAL_E) || userInput.equals(Constant.SMALL_e))  {
            getAllMedicines();
        }  else  {
            if  (isItemInList(userInput))  {
                addItemInCart(userInput);
                System.out.println("Item Added in cart");
            }  else  {
                System.out.println("Invalid Product Id");
            }
        }
    }

    /*
     * This is the function which will check if there are any items present in the list
     * */
    // This method will return true if the item is present in the list otherwise false
    public Boolean isItemInList(String pid)  {

        if  (pid.matches("[0-9]+" ))  {
            for  (Pharmacy pharmacy: listOfMedicines)  {
                if  (pharmacy.getP_id() == Integer.parseInt(pid))  {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * This is the function which will add medicines in the cart using product id
     * */
    public void addItemInCart(String pid)  {

        for  (Pharmacy pharmacy: listOfMedicines)  {
            if  (pharmacy.getP_id() == Integer.parseInt(pid))  {
                if  (!cart.containsKey(pharmacy.getP_id()))  {
                    Double total_Price = pharmacy.getPrice();
                    CartItem cartItem = new CartItem(pharmacy.getProduct_name(),
                            1,
                            pharmacy.getPrice(), total_Price);
                    cart.put(pharmacy.getP_id(), cartItem);
                    updateStock(pid);
                }  else  {
                    CartItem cartItem = cart.get(pharmacy.getP_id());
                    cartItem.setQty(cartItem.getQty() + 1);
                    Double total_price = cartItem.getPrice() * cartItem.getQty();
                    cartItem.setTotalPrice(total_price);
                    cart.put(pharmacy.getP_id(), cartItem);
                    updateStock(pid);
                }
            }
        }
    }

    /*
     * This is the Function which will call when user checkout so we can minus the qty in the stock
     * */
    public Boolean updateStock(String pid)  {

        for  (Pharmacy medicine: listOfMedicines)  {
            if  (medicine.getP_id() == Integer.parseInt(pid))  {
                Integer updatedStock = medicine.getStock() - 1;
                medicine.setStock(updatedStock);
                return true;
            }
        }
        return false;
    }

    public void showCartItems()  {

        if  (cart.size() == 0)  {
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

    /*
     * This is the Function which will show items present in the cart
     * */
    public void showCart()  {

        for  (Map.Entry me: cart.entrySet())  {
            String p_id = String.format(Constant.INTERGER_FORMAT, me.getKey());
            CartItem cartItem = (CartItem) me.getValue();
            String name = String.format(Constant.STRING_FORMAT, cartItem.getName());
            String price = String.format(Constant.DOUBLE_FORMAT_WITHOUT_DOT, cartItem.getPrice());
            String qty = String.format(Constant.INTERGER_FORMAT, cartItem.getQty());
            String totalPrice = String.format(Constant.DOUBLE_FORMAT_WITHOUT_DOT, cartItem.getTotalPrice());
//                String finalBill = String.format("|%10f|", cartItem.getFinalPrice());
            if  (isFinalPriceVisited == false)  {
                finalPrice += cartItem.getTotalPrice();
            }
            System.out.println(p_id +" " +name +" " +qty +" " +price +" " +totalPrice);
            cartItem.setFinalPrice(finalPrice);
        }

        isFinalPriceVisited = true;
        System.out.println("Your total bill is: $" +finalPrice);
    }

    /*
     * This is the just simple menu which will be called after showing cart
     * */
    public void afterCartMenu()  {
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

    /*
     * This is the checkout function
     * */
    public void checkOut()  {

        if  (cart.size() > 0) {
            BillingPage billingPage = new BillingPage(new OrderService(), new OrderService(), new OfferService());
            billingPage.CheckOut();
        }  else  {
            System.out.println(Colors.C_RED  +  "You can't checkout, your cart is empty"  +  Colors.C_RESET);
            menu();
        }

    }

    public void NotFound(){
        System.out.println(Colors.C_RED  +  "Please select the correct option"  +  Colors.C_RESET);
    }

    public void ViewOffers(){
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
