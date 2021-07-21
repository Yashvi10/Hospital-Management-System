package Interface;

import Model.Pharmacy;

import java.util.List;

public interface IPharmacyBL {

  List<Pharmacy> getAllMedicines();

  Boolean isItemInList(String pid);

  void addItemInCart(String pid);

  Boolean updateStock(String pid);
}
