package BL;

import Interface.BloodDonorBLDAO;
import Interface.BloodDonorDAO;
import Model.BloodDonor;
import View.BloodBank;

import java.util.List;

/*
 *  Name of file: BloodDonorBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for Blood Bank feature to list all donors
 * */
public class BloodDonorBL implements BloodDonorBLDAO {

  private BloodDonorDAO bloodDonorDAO;

  BloodBank bloodBank = new BloodBank();

  public BloodDonorBL(BloodDonorDAO bloodDonorDAO) {
    this.bloodDonorDAO = bloodDonorDAO;
  }

  @Override
  public List<BloodDonor> listOfDonors() {

    List<BloodDonor> bloodDonorList = bloodDonorDAO.getAllDonors();

    for(int i =0;i<bloodDonorList.size();i++) {
      System.out.println(bloodDonorList.get(i).getFirstname() + " " + bloodDonorList.get(i).getMiddlename()
              + " " + bloodDonorList.get(i).getLastname() + " " + bloodDonorList.get(i).getBlood_group()
              + " " + bloodDonorList.get(i).getContact() + " " + bloodDonorList.get(i).getDate()+ "\n");
    }

    bloodBank.menu();
    return bloodDonorList;
  }

}
