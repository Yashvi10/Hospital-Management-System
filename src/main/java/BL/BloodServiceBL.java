package BL;

import Interface.BloodDAO;
import Interface.BloodServiceBLDAO;
import Model.BloodInventory;
import View.BloodBank;

import java.util.List;

/*
 *  Name of file: BloodServiceBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for Blood Bank feature to list inventory
 * */
public class BloodServiceBL implements BloodServiceBLDAO {

  private BloodDAO bloodDAO;

  BloodBank bloodBank = new BloodBank();

  public BloodServiceBL(BloodDAO bloodDAO) {
    this.bloodDAO = bloodDAO;
  }

  @Override
  public List<BloodInventory> listOfItems() {

    List<BloodInventory> bloodInventoryList = bloodDAO.getAllBloodGroup();

    for(int i =0;i<bloodInventoryList.size();i++) {
      System.out.println(bloodInventoryList.get(i).getBlood_group() + " " + bloodInventoryList.get(i).getNumber_of_bottles());
    }

    bloodBank.menu();
    return bloodInventoryList;
  }

}
