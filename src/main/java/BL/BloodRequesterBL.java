package BL;

import Interface.BloodRequesterBLDAO;
import Interface.BloodRequesterDAO;
import Model.BloodRequester;
import View.BloodBank;
import View.Constant;

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

    System.out.println(String.format(Constant.STRING_FORMAT,"Firstname") + " " +
            String.format(Constant.STRING_FORMAT,"Middlename") + " " +
            String.format(Constant.STRING_FORMAT, "Lastname") + " " +
            String.format(Constant.STRING_FORMAT, "Blood Group") + " " +
            String.format(Constant.STRING_FORMAT,"Contact") + " " +
            String.format(Constant.STRING_FORMAT,"Date") + "\n");

    for(int i =0;i<bloodRequesterList.size();i++) {
      System.out.println(String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getFirstname()) + " " + String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getMiddlename())
              + " " + String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getLastname()) + " " + String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getBlood_group())
              + " " + String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getContact()) + " " + String.format(Constant.STRING_FORMAT,bloodRequesterList.get(i).getDate()) + "\n");
    }

    bloodBank.menu();
    return bloodRequesterList;
  }

}
