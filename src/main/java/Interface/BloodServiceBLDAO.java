package Interface;

import Model.BloodInventory;

import java.util.List;

/*
 *  Name of file: BloodServiceBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Blood Bank feature for blood inventory
 * */
public interface BloodServiceBLDAO {

  List<BloodInventory> listOfItems();

}
