package Interface;

import Model.BloodInventory;

import java.util.List;

/*
 *  Name of file: BloodDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to BloodInventory model
 * */
public interface BloodDAO {

    List<BloodInventory> getAllBloodGroup();
}
