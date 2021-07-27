package BL;

import Interface.SearchCampsBLDAO;
import Interface.SearchCampsDAO;
import Model.SearchCamps;
import View.Constant;
import View.NearbyCamps;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: SearchCampsBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for searching camps based on location
 * */
public class SearchCampsBL implements SearchCampsBLDAO {

  NearbyCamps nearbyCamps = new NearbyCamps();

  private SearchCampsDAO searchCampsDAO;

  public SearchCampsBL(SearchCampsDAO searchCampsDAO) {
    this.searchCampsDAO = searchCampsDAO;
  }

  /* This method returns list of camps based on the provided location by user
   */
  @Override
  public List<SearchCamps> getCamps(String camp_location) throws IOException {

    List<SearchCamps> searchCampsList = searchCampsDAO.searchCamp(camp_location);

    System.out.println(String.format(Constant.STRING_FORMAT, "Camp_Type") + " " + String.format(Constant.STRING_FORMAT, "Camp_Description") + " " + String.format(Constant.STRING_FORMAT, "Camp_Location") + " " + String.format(Constant.STRING_FORMAT, "CampAddedBy") + "\n");

    for (int i = 0; i < searchCampsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_type().replace('-', ' ')) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_description().replace('-', ' ')) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_location()) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_addedBy().replace('-', ' ')));
    }
    nearbyCamps.menu();
    return searchCampsList;
  }

}
