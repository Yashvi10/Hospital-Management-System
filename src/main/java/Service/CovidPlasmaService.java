package Service;

import Interface.CovidPlasmaDAO;
import Model.CovidPlasmaInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: CovidPlasmaService.java
 *  Author:  Kushang Mistry
 *  Purpose: Returns Plasma information and handles database operations related to plasma.
 *  Description: Fetches information from database and gives count of availability of plasma.
 *               As well as requests for plasma.
 * */
public class CovidPlasmaService implements CovidPlasmaDAO {

  CustomConnection customConnection = new CustomConnection();

  @Override
  public List<CovidPlasmaInformation> showPlasmaAvailability() {

    Connection conn = customConnection.Connect();

    List<CovidPlasmaInformation> covidPlasmaInformation = new ArrayList<>();

    if (conn != null) {
      String fetchQuery = "select * from plasma_inventory";
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          Integer plasmaId = resultSet.getInt(1);
          String plasmaType = resultSet.getString(2);
          Integer availability = resultSet.getInt(3);

          CovidPlasmaInformation covidPlasmaInformation1 = new CovidPlasmaInformation(plasmaId, plasmaType, availability);
          covidPlasmaInformation.add(covidPlasmaInformation1);
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
    return covidPlasmaInformation;
  }
}
