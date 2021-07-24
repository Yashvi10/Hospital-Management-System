package BL;

import Interface.IPharmacyBL;
import Interface.PharmacyDAO;
import Model.CartItem;
import Model.Pharmacy;

import java.util.HashMap;
import java.util.List;

/*
 *  Name of file: PharmacyBL.java
 *  Author:  Nadish Maredia
 *  Purpose & Description: This class hold the business logic for pharmacy feature
 * */
public class PharmacyBL implements IPharmacyBL {

  public List<Pharmacy> listOfMedicines;

  public static HashMap<Integer, CartItem> cart = new HashMap<Integer, CartItem>();

  public static Double finalPrice = 0.0;

  public static Boolean isFinalPriceVisited = false;

  private PharmacyDAO pharmacyDAO;

  public PharmacyBL(PharmacyDAO pharmacyDAO){
    this.pharmacyDAO = pharmacyDAO;

    loadMedicines();
  }
  public void loadMedicines() {

    listOfMedicines = pharmacyDAO.getAllMedicines();
  }

  @Override
  public List<Pharmacy> getAllMedicines(){
    return listOfMedicines;
  }

  @Override
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

  @Override
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

  @Override
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
}
