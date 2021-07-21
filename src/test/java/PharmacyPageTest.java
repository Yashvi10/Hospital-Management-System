import BL.PharmacyBL;
import View.Dashboard;
import View.PharmacyMenu;
import Model.Pharmacy;
import Service.OfferService;
import Service.PharmacyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: PharmacyPageTest.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is used for testing the logic related to pharmacy
 *  Description: class is used for testing the logic related to pharmacy like addItemInCart(), updateStock()
 * */
public class PharmacyPageTest {

    PharmacyBL pharmacyBL = new PharmacyBL(new PharmacyService());
    @Test
    void isItemInList_true()  {


        Pharmacy P1 = new Pharmacy(1, "P1", 2.0, 25, "2025-05-02");
        Pharmacy P2 = new Pharmacy(2, "P2", 4.0, 25, "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        //Pid 1 is available in the list return true
        assertEquals(true, pharmacyBL.isItemInList("1"),
                "The product is not in the list");
    }

    @Test
    void isItemInList_false()  {

        Pharmacy P1 = new Pharmacy(1,
                "P1",
                2.0,
                25,
                "2025-05-02");
        Pharmacy P2 = new Pharmacy(2,
                "P2",
                4.0,
                25,
                "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        //Pid 6 is not in the list return false
        assertEquals(false, pharmacyBL.isItemInList("6"),
                "The product is not in the list");
    }

    @Test
    void addItemInCart_true()  {

        Pharmacy P1 = new Pharmacy(1,
                "P1",
                2.0,
                25,
                "2025-05-02");
        Pharmacy P2 = new Pharmacy(2,
                "P2",
                4.0,
                25,
                "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        // there are two items avaiable to buy we want to buy item with id 1 and if that item is present in the list
        // it will be added in the cart
        pharmacyBL.addItemInCart("1");
        assertEquals(true, pharmacyBL.cart.size() > 0, "There is no item in the cart");
    }

    @Test
    void addItemInCart_false()  {

        Pharmacy P1 = new Pharmacy(1,
                "P1",
                2.0,
                25,
                "2025-05-02");
        Pharmacy P2 = new Pharmacy(2,
                "P2",
                4.0,
                25,
                "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        // there are two items avaiable to buy we want to buy item with id 9989 and if that item is not present in the list
        // it will not added in the cart
        pharmacyBL.addItemInCart("9989");
        assertEquals(false, pharmacyBL.cart.size() > 0, "Cart is empty");
    }

    @Test
    public void updateStock_true()  {

        Pharmacy P1 = new Pharmacy(1,
                "P1",
                2.0,
                25,
                "2025-05-02");
        Pharmacy P2 = new Pharmacy(2,
                "P2",
                4.0,
                25,
                "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        // return true of medicine with that pid in this case "2" is in the list and update the stock by -1
        assertEquals(true, pharmacyBL.updateStock("2"),"Medicine with that id is not found");

    }

    @Test
    public void updateStock_false()  {

        Pharmacy P1 = new Pharmacy(1,
                "P1",
                2.0,
                25,
                "2025-05-02");
        Pharmacy P2 = new Pharmacy(2,
                "P2",
                4.0,
                25,
                "2024-05-12");
        pharmacyBL.listOfMedicines.add(P1);
        pharmacyBL.listOfMedicines.add(P2);

        // return false of medicine with that pid in this case "22" is not in the list
        assertEquals(false, pharmacyBL.updateStock("22"), "Medicine with that id not found");

    }
}
