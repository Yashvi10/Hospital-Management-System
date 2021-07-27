package BL;

import Interface.BloodRequesterBLDAO;
import Interface.BloodRequesterDAO;
import Model.BloodRequester;
import View.BloodBank;

import java.util.List;

/*
 *  Name of file: BloodRequesterBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for Blood Bank feature to list all requesters
 * */
public class BloodRequesterBL implements BloodRequesterBLDAO {

  private BloodRequesterDAO bloodRequesterDAO;

  BloodBank bloodBank = new BloodBank();

  public BloodRequesterBL(BloodRequesterDAO bloodRequesterDAO) {
    this.bloodRequesterDAO = bloodRequesterDAO;
  }

  @Override
  public List<BloodRequester> listOfRequesters() {

    List<BloodRequester> bloodRequesterList = bloodRequesterDAO.getAllRequesters();

    for(int i =0;i<bloodRequesterList.size();i++) {
      System.out.println(bloodRequesterList.get(i).getFirstname() + " " + bloodRequesterList.get(i).getMiddlename()
              + " " + bloodRequesterList.get(i).getLastname() + " " + bloodRequesterList.get(i).getBlood_group()
              + " " + bloodRequesterList.get(i).getContact() + " " + bloodRequesterList.get(i).getDate() + "\n");
    }

    bloodBank.menu();
    return bloodRequesterList;
  }

}
