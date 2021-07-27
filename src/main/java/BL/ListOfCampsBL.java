package BL;

import Interface.ListOfCampsBLDAO;
import Interface.ListOfCampsDAO;
import Model.ListOfCamps;
import View.Constant;
import View.NearbyCamps;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: ListOfCampsBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for getting lists of camps feature
 * */
public class ListOfCampsBL implements ListOfCampsBLDAO {

  private ListOfCampsDAO listOfCampsDAO;

  NearbyCamps nearbyCamps = new NearbyCamps();

  public ListOfCampsBL(ListOfCampsDAO listOfCampsDAO) {
    this.listOfCampsDAO = listOfCampsDAO;
  }

  /*
   *  This method returns list of all camps
   * */
  @Override
  public List<ListOfCamps> getCampsData() throws IOException {
    List<ListOfCamps> listOfCampsList = listOfCampsDAO.allCamps();

    System.out.println(String.format(Constant.STRING_FORMAT, "Camp_Type") + " " + String.format(Constant.STRING_FORMAT, "Camp_Description") + " " + String.format(Constant.STRING_FORMAT, "Camp_Location") + " " + String.format(Constant.STRING_FORMAT, "CampAddedBy") + "\n");

    for (int i = 0; i < listOfCampsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_type().replace('-',' ')) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_description().replace('-',' ')) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_location().replace('-',' ')) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_addedBy().replace('-',' ')));
    }
    nearbyCamps.menu();
    return listOfCampsList;
  }

}
