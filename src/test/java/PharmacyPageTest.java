import Model.Pharmacy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PharmacyPageTest {

    @Test
    void isItemInList_true()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
        Pharmacy P1 = new Pharmacy(1, "P1", 2.0, 25, "2025-05-02");
        Pharmacy P2 = new Pharmacy(2, "P2", 4.0, 25, "2024-05-12");
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        //Pid 1 is available in the list return true
        assertEquals(true, pharmacyPage.isItemInList("1"),
                "The product is not in the list");
    }

    @Test
    void isItemInList_false()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
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
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        //Pid 6 is not in the list return false
        assertEquals(false, pharmacyPage.isItemInList("6"),
                "The product is not in the list");
    }

    @Test
    void addItemInCart_true()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
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
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        // there are two items avaiable to buy we want to buy item with id 1 and if that item is present in the list
        // it will be added in the cart
        pharmacyPage.addItemInCart("1");
        assertEquals(true, pharmacyPage.cart.size() > 0, "There is no item in the cart");
    }

    @Test
    void addItemInCart_false()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
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
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        // there are two items avaiable to buy we want to buy item with id 9989 and if that item is not present in the list
        // it will not added in the cart
        pharmacyPage.addItemInCart("9989");
        assertEquals(false, pharmacyPage.cart.size() > 0, "Cart is empty");
    }

    @Test
    public void updateStock_true()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
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
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        // return true of medicine with that pid in this case "2" is in the list and update the stock by -1
        assertEquals(true, pharmacyPage.updateStock("2"),"Medicine with that id is not found");

    }

    @Test
    public void updateStock_false()  {

        PharmacyPage pharmacyPage = new PharmacyPage();
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
        pharmacyPage.listOfMedicines.add(P1);
        pharmacyPage.listOfMedicines.add(P2);

        // return false of medicine with that pid in this case "22" is not in the list
        assertEquals(false, pharmacyPage.updateStock("22"), "Medicine with that id not found");

    }
}
