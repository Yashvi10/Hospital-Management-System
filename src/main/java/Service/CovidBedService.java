package Service;

import Interface.CovidBedDAO;

import java.sql.*;

/*
 *  Name of file: CovidBedService.java
 *  Author:  Kushang Mistry
 *  Purpose: Returns number of available beds based on user choice and registers bed to database
 *  Description: Fetches information from database and gives count of availability of bed.
 *               As well as registers bed as per user's choice and availability.
 * */
public class CovidBedService implements CovidBedDAO {

  // Initialization of the object of custom connection class
  CustomConnection customConnection = new CustomConnection();

  /*
   * This methods retrieves information about total available beds based on given bed type
   * returns total beds count
   */
  @Override
  public Integer getTotalBeds(Integer bedType) {

    Connection conn = customConnection.Connect();

    Integer availableBeds = 0;

    String dbBed;

    if(validateBedtype(bedType) == null)
      return null;

    dbBed = validateBedtype(bedType);

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

  /*
   * This methods registers the bed based on availability and user choice
   * returns bed number of specific type
   */
  @Override
  public Integer registerBed(Integer bedType) {

    Connection conn = customConnection.Connect();

    Integer assignedBed=null;
    if(getDesiredBed(bedType) == null)
      return null;
    else
      assignedBed = getDesiredBed(bedType);

    if (conn != null) {
      String insertQuery = "insert into covid_bed_patients(user_id, request_date, bed_id)"
              + " values( "+ UserSession.userId + ",\""+ getTodaysDate()
              + "\"," + assignedBed +")";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(insertQuery);
        conn.close();

        if(changeAvailability(assignedBed))
          return assignedBed;
        else
          return null;
      } catch (SQLException exception) {
        exception.printStackTrace();
        return null;
      } finally {
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException exception) {
            exception.printStackTrace();
          }
        }
      }
    }

    return null;
  }

  /*
   * This methods retrieves information about total available beds based on given bed type
   * returns total beds count
   */
  public String validateBedtype(Integer bedType) {

    if(bedType == null && bedType == 0)
      return null;

    if(bedType == 1)
      return  "g";
    else if(bedType == 2)
      return  "o";
    else if(bedType == 3)
      return  "v";
    else
      return null;
  }

  /*
   * This helper method retrieves information about bed which is available
   * In specific category, this method traverses the database
   * Returns the bed if bed is available otherwise returns null
   */
  public Integer getDesiredBed(Integer bedType) {

    String bed;

    if(validateBedtype(bedType) == null)
      return null;
    else
      bed = validateBedtype(bedType);

    Connection conn = customConnection.Connect();

    Integer bedId = null;

    if (conn != null) {
      String fetchQuery = "select * from covid_beds where bed_type=\""+bed+"\" and bed_availability=0";

      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          bedId = resultSet.getInt("bed_id");
          break;
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
    return bedId;
  }

  /*
   * This helper method retrieves today's date
   * Hence if user registers for the bed
   * The system automatically considers today's date as a registration date
   */
  public Date getTodaysDate() {
    return  new java.sql.Date(System.currentTimeMillis());
  }

  /*
   * This helper method changes bed's availability from open to occupied
   * Returns true if availability is altered
   */
  public Boolean changeAvailability (Integer bedId) {

    Connection conn = customConnection.Connect();

    if(bedId == null)
      return null;

    if (conn != null) {
      String SQL = "update covid_beds SET bed_availability=1 where bed_id = '" + bedId + "';";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        return true;
      } catch (SQLException exception) {
        exception.printStackTrace();
        return false;
      } finally {
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
          }
        }
      }
    }
    return false;
  }
}
