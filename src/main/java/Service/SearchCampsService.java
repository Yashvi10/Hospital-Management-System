package Service;

import Interface.SearchCampsDAO;
import Model.SearchCamps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: SearchCampsService.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like service which will implement the SearchCampsDAO
 *  Description: This class will implement all the actual logic define in SearchCampsDAO
 * */
public class SearchCampsService implements SearchCampsDAO {


  CustomConnection customConnection = new CustomConnection();

  @Override
  public List<SearchCamps> searchCamp(String camp_location) {

    Connection conn = customConnection.Connect();
    List<SearchCamps> searchCampsList = new ArrayList<>();
    if (conn != null) {
      String SQL = "select * from AddCamps where camp_location = '" + camp_location + "';";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);

        while (rs.next()) {
          String camp_type = rs.getString(2);
          String camp_desc = rs.getString(3);
          String camp_loc = rs.getString(4);
          String camp_added = rs.getString(5);

          SearchCamps searchCamps1 = new SearchCamps(camp_type, camp_desc, camp_loc, camp_added);
          searchCampsList.add(searchCamps1);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
    return searchCampsList;
  }
}
