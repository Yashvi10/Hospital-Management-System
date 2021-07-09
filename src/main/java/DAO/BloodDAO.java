package DAO;
/*
 *  Name of file: BloodDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to BloodInventory model
 * */

import Model.BloodInventory;

import java.util.List;

public interface BloodDAO {

     List<BloodInventory> getAllBloodGroup();
}
