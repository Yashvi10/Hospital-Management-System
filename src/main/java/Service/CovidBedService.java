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

  CustomConnection customConnection = new CustomConnection();

  @Override
  public Integer getTotalBeds(Integer bedType) {

    Connection conn = customConnection.Connect();

    Integer availableBeds = 0;

    String dbBed;

    if(validateBedtype(bedType) == null)
      return null;

    dbBed = validateBedtype(1);

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

  public Date getTodaysDate() {
    return  new java.sql.Date(System.currentTimeMillis());
  }

  public Boolean changeAvailability (Integer bedId) {

    Connection conn = customConnection.Connect();

    if(bedId == null)
      return null;

    if (conn != null) {
      String SQL = "update CSCI5308_8_DEVINT.covid_beds SET bed_availability=1 where bed_id = '" + bedId + "';";
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
