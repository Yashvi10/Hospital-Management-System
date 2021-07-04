import Model.CartItem;
import Model.Pharmacy;
import Services.OfferService;
import Services.PharmacyService;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PharmacyPage {

    PharmacyService pharmacyService = new PharmacyService();
    List<Pharmacy> listOfMedicines = pharmacyService.getAllMedicines();
    public static HashMap<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
    public static Double finalPrice = 0.0;
    public static Boolean isFinalPriceVisited = false;

    /*
    * This is the Menu for pharmacy module
    * */
    public void PharmacyMenu(){
        System.out.println("Press l to list all medicines, Press e to back");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if(inputFromUser.equals("l") || inputFromUser.equals("L")){
            getAllMedicines();
        } else {
            NotFound();
        }
    }

    /*
     * This is the Function which will show list of all available medicines
     * */
    public void getAllMedicines(){

        System.out.println("=========PHARMACY LIST=========");
        System.out.println(String.format("|%10s|", "P_id") +" " +String.format("|%10s|", "Name") +" " +String.format("|%10s|", "Price")+" " +String.format("|%10s|", "Stock"));
        for(Pharmacy pharmacy: listOfMedicines) {
            String p_id = String.format("|%10d|", pharmacy.getP_id());
            String name = String.format("|%10s|", pharmacy.getProduct_name());
            DecimalFormat df = new DecimalFormat("#.##");
            String price = String.format("|%10s|", String.valueOf(pharmacy.getPrice()));
            String stock = String.format("|%10d|", pharmacy.getStock());
            System.out.println(p_id +" " +name +" " +price +" " +stock);
        }

        System.out.println("Press b to buy, Press c to show cart, Press e to exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(true) {

            if (userInput.equals("b") || userInput.equals("B")) {
                BuyItem();
            }else if(userInput.equals("c") || userInput.equals("C")){
                showCartItems();
                break;
            } else if(userInput.equals("e") || userInput.equals("E")){
                PharmacyMenu();
                break;
            } else {
                NotFound();
            }
        }
    }

    /*
     * This is the function from which user can buy specific medicines input will be product id
     * */
    public void BuyItem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the P_id you want to buy or Press e to exit");
        String userInput = scanner.nextLine();

        if(userInput.equals("e")) {
            getAllMedicines();
        } else {
            if(isItemInList(userInput)) {
                addItemInCart(userInput);
                System.out.println("Item Added in cart");
            }else {
                System.out.println("Invalid Product Id");
            }
        }
        }


    /*
     * This is the function which will check if there are any items present in the list
     * */
    // This method will return true if the item is present in the list otherwise false
    public Boolean isItemInList(String pid){
        if (pid.matches("[0-9]+" )) {
            for(Pharmacy pharmacy: listOfMedicines) {
                if(pharmacy.getP_id() == Integer.parseInt(pid)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
     * This is the function which will add medicines in the cart using product id
     * */
    public void addItemInCart(String pid){

        for(Pharmacy pharmacy: listOfMedicines) {
            if(pharmacy.getP_id() == Integer.parseInt(pid)) {
                if(!cart.containsKey(pharmacy.getP_id())) {
                    Double total_Price = pharmacy.getPrice();
                    CartItem cartItem = new CartItem(pharmacy.getProduct_name(),1, pharmacy.getPrice(), total_Price);

                    cart.put(pharmacy.getP_id(), cartItem);
                    updateStock(pid);

                } else {
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
    public Boolean updateStock(String pid){
        for(Pharmacy medicine: listOfMedicines) {
            if(medicine.getP_id() == Integer.parseInt(pid)) {
                Integer updatedStock = medicine.getStock() - 1;
                medicine.setStock(updatedStock);
                return true;
            }
        }
        return false;
    }

    public void showCartItems(){

        if(cart.size() == 0) {
            System.out.println("Cart is empty");
        } else {
            System.out.println("=========CART ITEM LIST=========");
            System.out.println(String.format("|%10s|", "P_id") +" " +String.format("|%10s|", "Name") +" " +String.format("|%10s|", "Qty") +" " +String.format("|%10s|", "Price") +" " +String.format("|%10s|", "Total Price"));

            showCart();
        }

        afterCartMenu();


    }

    /*
     * This is the Function which will show items present in the cart
     * */
    public void showCart(){
        for(Map.Entry me: cart.entrySet()) {
            String p_id = String.format("|%10d|", me.getKey());
            CartItem cartItem = (CartItem) me.getValue();
            String name = String.format("|%10s|", cartItem.getName());
            String price = String.format("|%10f|", cartItem.getPrice());
            String qty = String.format("|%10d|", cartItem.getQty());
            String totalPrice = String.format("|%10f|", cartItem.getTotalPrice());
//                String finalBill = String.format("|%10f|", cartItem.getFinalPrice());
            if(isFinalPriceVisited == false) {
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
    public void afterCartMenu(){
        System.out.println("Press c to checkout or press e to exit (it will remove the cart)");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if(userInput.equals("c") || userInput.equals("C")){
            System.out.println("Checkout");
            checkOut();

        } else {
            PharmacyMenu();
        }
    }

    /*
     * This is the checkout function
     * */
    public void checkOut(){

        BillingPage billingPage = new BillingPage();
        billingPage.CheckOut();
    }

    public void NotFound(){
        System.out.println("Please select the correct option");
    }

}
