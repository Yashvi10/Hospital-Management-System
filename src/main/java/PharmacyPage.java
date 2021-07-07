import Model.CartItem;
import Model.Offers;
import Model.Pharmacy;
import Services.OfferService;
import Services.PharmacyService;
import java.text.DecimalFormat;
import java.util.*;

/*
 *  Name of file: PharmacyPage.java
 *  Author:  Nadish Maredia
 *  Purpose: This class contains different menus and it will redirect based on their input
 *  Description: This class basically take input from user and validate them and
 *               call different Services which we created
 * */
public class PharmacyPage {

    PharmacyService pharmacyService = new PharmacyService();

    List<Pharmacy> listOfMedicines = pharmacyService.getAllMedicines();

    public static HashMap<Integer, CartItem> cart = new HashMap<Integer, CartItem>();

    public static Double finalPrice = 0.0;

    public static Boolean isFinalPriceVisited = false;

    /*
    * This is the Menu for pharmacy module
    * */
    public void PharmacyMenu()  {

        System.out.println("Press l to list all medicines, Press e to back");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if  (inputFromUser.equals("l") || inputFromUser.equals("L"))  {
            getAllMedicines();
        }  else if  (inputFromUser.equals("e") || inputFromUser.equals("E"))  {
            return;
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

        System.out.println("Press b to buy, Press c to show cart, Press e to exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(true)  {
            if  (userInput.equals("b") || userInput.equals("B"))  {
                BuyItem();
            }  else if(userInput.equals("c") || userInput.equals("C"))  {
                showCartItems();
                break;
            }  else if(userInput.equals("e") || userInput.equals("E"))  {
                PharmacyMenu();
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
        System.out.println("Please enter the P_id you want to buy or Press e to exit");
        String userInput = scanner.nextLine();

        if  (userInput.equals("e"))  {
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
            System.out.println("Cart is empty");
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
        System.out.println("Press c to checkout, Press o to see offers or press e to exit (it will remove the cart)");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if  (userInput.equals("c") || userInput.equals("C"))  {
            System.out.println("Checkout");
            checkOut();
        }  else if  (userInput.equals("o") || userInput.equals("O")) {
            ViewOffers();
        }  else if  (userInput.equals("e") || userInput.equals("E"))  {
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
            BillingPage billingPage = new BillingPage();
            billingPage.CheckOut();
        }  else  {
            System.out.println(Colors.C_RED  +  "You can't checkout, your cart is empty"  +  Colors.C_RESET);
            PharmacyMenu();
        }

    }

    public void NotFound(){
        System.out.println(Colors.C_RED  +  "Please select the correct option"  +  Colors.C_RESET);
    }

    public void ViewOffers(){
        System.out.println("==========Offer List==========");

        OfferService offerService = new OfferService();
        List<Offers> offersList = offerService.getAllOffer();

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
