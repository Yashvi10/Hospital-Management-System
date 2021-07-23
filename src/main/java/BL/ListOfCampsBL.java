package BL;

import Interface.ListOfCampsBLDAO;
import Interface.ListOfCampsDAO;
import Model.ListOfCamps;
import View.Constant;
import View.NearbyCamps;

import java.util.List;

public class ListOfCampsBL implements ListOfCampsBLDAO {

  private ListOfCampsDAO listOfCampsDAO;

  NearbyCamps nearbyCamps = new NearbyCamps();

  public ListOfCampsBL(ListOfCampsDAO listOfCampsDAO) {
    this.listOfCampsDAO = listOfCampsDAO;
  }

  @Override
  public List<ListOfCamps> getCampsData() {
    List<ListOfCamps> listOfCampsList = listOfCampsDAO.allCamps();

    System.out.println(String.format(Constant.STRING_FORMAT, "Camp_Type") + " " + String.format(Constant.STRING_FORMAT, "Camp_Description") + " " + String.format(Constant.STRING_FORMAT, "Camp_Location") + " " + String.format(Constant.STRING_FORMAT, "CampAddedBy") + "\n");

    for (int i = 0; i < listOfCampsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_type()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_description()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_location()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_addedBy()));
    }
    nearbyCamps.menu();
    return listOfCampsList;
  }

}
