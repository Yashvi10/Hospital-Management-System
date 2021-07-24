package Interface;

import Model.Pharmacy;

import java.util.List;

/*
 *  Name of file: IPharmacyBL.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the business logic methods of pharmacy feature
 * */
public interface IPharmacyBL {

  List<Pharmacy> getAllMedicines();

  Boolean isItemInList(String pid);

  void addItemInCart(String pid);

  Boolean updateStock(String pid);
}
