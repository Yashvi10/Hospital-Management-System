package Service;

import Interface.ListOfCampsDAO;
import Model.ListOfCamps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: ListOfCampsService.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like service which will implement the ListOfCampsDAO
 *  Description: This class will implement all the actual logic define in ListOfCampsDAO
 * */
public class ListOfCampsService implements ListOfCampsDAO {

  CustomConnection customConnection = new CustomConnection();

  @Override
  public List<ListOfCamps> allCamps() {

    Connection conn = customConnection.Connect();
    List<ListOfCamps> listOfCampsList = new ArrayList<>();

    if (conn != null) {
      String SQL = "select * from AddCamps";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next()) {
          String camp_type = rs.getString(2);
          String camp_desc = rs.getString(3);
          String camp_loc = rs.getString(4);
          String camp_added = rs.getString(5);

          ListOfCamps listOfCamps1 = new ListOfCamps(camp_type, camp_desc, camp_loc, camp_added);
          listOfCampsList.add(listOfCamps1);
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
    return listOfCampsList;
  }

}
