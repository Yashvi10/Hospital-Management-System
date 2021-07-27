package BL;

import Interface.BloodDonorBLDAO;
import Interface.BloodDonorDAO;
import Model.BloodDonor;
import View.BloodBank;
import View.Constant;

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

    System.out.println(String.format(Constant.STRING_FORMAT,"Firstname") + " " +
            String.format(Constant.STRING_FORMAT,"Middlename") + " " +
            String.format(Constant.STRING_FORMAT, "Lastname") + " " +
            String.format(Constant.STRING_FORMAT, "Blood Group") + " " +
            String.format(Constant.STRING_FORMAT,"Contact") + " " +
            String.format(Constant.STRING_FORMAT,"Date") + "\n");

    for(int i =0;i<bloodDonorList.size();i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, bloodDonorList.get(i).getFirstname()) + " " + String.format(Constant.STRING_FORMAT,bloodDonorList.get(i).getMiddlename())
              + " " + String.format(Constant.STRING_FORMAT,bloodDonorList.get(i).getLastname()) + " " + String.format(Constant.STRING_FORMAT,bloodDonorList.get(i).getBlood_group())
              + " " + String.format(Constant.STRING_FORMAT,bloodDonorList.get(i).getContact()) + " " + String.format(Constant.STRING_FORMAT,bloodDonorList.get(i).getDate())+ "\n");
    }

    bloodBank.menu();
    return bloodDonorList;
  }

}
