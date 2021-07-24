package Service;

import Interface.CovidBadDAO;
import Model.Vaccine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: CovidBedService.java
 *  Author:  Kushang Mistry
 *  Purpose: Returns number of available beds based on user choice and registers bed to database
 *  Description: Fetches information from database and gives count of availability of bed.
 *               As well as registers bed as per user's choice and availability.
 * */
public class CovidBedService implements CovidBadDAO {

  CustomConnection customConnection = new CustomConnection();

  @Override
  public Integer getTotalBeds(Integer bedType) {

    Connection conn = customConnection.Connect();

    Integer availableBeds = 0;

    String dbBed = null;

    if(bedType == null && bedType == 0)
      return null;

    if(bedType == 1)
      dbBed = "g";
    else if(bedType == 2)
      dbBed = "o";
    else if(bedType == 3)
      dbBed = "v";
    else
      return null;

    if (conn != null) {
      String fetchQuery = "select * from covid_beds where bed_type=\""+dbBed+"\" and bed_availability=0";
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          Integer bedId = resultSet.getInt(1);
          String type = resultSet.getString(2);
          Integer availability = resultSet.getInt(3);
          availableBeds++;
        }
      } catch (SQLException exception) {
        exception.printStackTrace();
        return null;
      } finally {
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
          }
        }
      }
    }

    if(availableBeds == 0)
      return null;
    else
      return availableBeds;
  }
}
