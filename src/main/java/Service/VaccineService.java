package Service;

import Interface.VaccineDAO;
import Model.Vaccine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: VaccineService.java
 *  Author:  Kushang Mistry
 *  Purpose: Lists all available and approved Vaccines.
 *  Description: Fetches information from database and lists all vaccines available.
 * */
public class VaccineService implements VaccineDAO {

  CustomConnection customConnection = new CustomConnection();

  @Override
  public List<Vaccine> getVaccines() {

    Connection conn = customConnection.Connect();

    List<Vaccine> vaccineList = new ArrayList<Vaccine>();

    if (conn != null) {
      String fetchQuery = "select * from vaccine";
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          Integer vaccineId = resultSet.getInt(1);
          String vaccineName = resultSet.getString(2);
          Integer availableDoses = resultSet.getInt(3);

          Vaccine vaccineData = new Vaccine(vaccineId, vaccineName, availableDoses);
          vaccineList.add(vaccineData);
        }
      } catch (SQLException exception) {
        exception.printStackTrace();
      } finally {
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
          }
        }
      }
    }

    return vaccineList;
  }
}
