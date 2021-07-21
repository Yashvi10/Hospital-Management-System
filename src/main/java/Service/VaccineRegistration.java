package Service;

import Interface.VaccineRegisterUserDAO;
import Model.VaccineUserInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: VaccineRegistration.java
 *  Author:  Kushang Mistry
 *  Purpose: Serves purpose of user registration for vaccine
 *  Description: This class adds user information about vaccination in the database
 * */
public class VaccineRegistration implements VaccineRegisterUserDAO {

  CustomConnection customConnection = new CustomConnection();

  /*
   * This method registers user for vaccination based on information provided
   * Returns true if user added successfully false otherwise
   */
  @Override
  public Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation) {

    Connection conn = customConnection.Connect();

    if (conn != null) {
      String insertQuery = "insert into vaccination_patients(userId, mail_id, age, gender, government_id_number, preferred_next_date, preferred_timing) "
              + "values(" + vaccineUserInformation.getUserId() + ",'" + vaccineUserInformation.getMailId()
              + "'," + vaccineUserInformation.getAge() + ",'" + vaccineUserInformation.getGender()
              + "','" + vaccineUserInformation.getGovernmentId() + "','" + vaccineUserInformation.getPreferredDate()
              + "','" + vaccineUserInformation.getPreferredTiming() +"')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(insertQuery);
        conn.close();

        return true;

      } catch (SQLException exception) {
        exception.printStackTrace();
        return false;
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
    return false;
  }

  /*
   * This method retrieves vaccine registration information
   * Returns list of Vaccine Information class object (Maximum 2 - As only two doses are allowed)
   */
  @Override
  public List<VaccineUserInformation> getVaccinationInfo(Integer userId) {

    Connection conn = customConnection.Connect();

    List<VaccineUserInformation> userVaccineDetails = new ArrayList<>();

    if (conn != null) {
      String fetchQuery = "select * from vaccination_patients where userId = "+userId;
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          String mailId = resultSet.getString("mail_id");
          Integer age = resultSet.getInt("age");
          String gender = resultSet.getString("gender");
          String governmentId = resultSet.getString("government_id_number");
          Date date = resultSet.getDate("dose_date");
          String preferred_timing = resultSet.getString("preferred_timing");
          Integer vaccineId = resultSet.getInt("vaccine_id");

          VaccineUserInformation userInformation = new VaccineUserInformation(userId, mailId, age, gender, governmentId, date, preferred_timing, vaccineId);
          userVaccineDetails.add(userInformation);
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
    return userVaccineDetails;
  }


  /*
   * This method retrieves single vaccine information about user
   * Returns Vaccine Information class object - In form of fist dose registration
   */
  @Override
  public VaccineUserInformation getUserVaccineData(Integer userId) {

    Connection conn = customConnection.Connect();

    VaccineUserInformation userVaccineInformation = null;

    if (conn != null) {
      String fetchQuery = "select * from vaccination_patients where userId = "+userId;
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          String mailId = resultSet.getString("mail_id");
          Integer age = resultSet.getInt("age");
          String gender = resultSet.getString("gender");
          String governmentId = resultSet.getString("government_id_number");
          Date date = resultSet.getDate("preferred_next_date");
          String preferred_timing = resultSet.getString("preferred_timing");
          Integer vaccineId = resultSet.getInt("vaccine_id");

          userVaccineInformation = new VaccineUserInformation(userId, mailId, age, gender, governmentId,date, preferred_timing, vaccineId);
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
    return userVaccineInformation;
  }

  @Override
  public Boolean updateSlotAvailability(Date date) {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Boolean result = false;

    Date changeDate = date;

    if(conn != null) {
      String SQL = "update CSCI5308_8_DEVINT.vaccine_available_slots SET available_slots=available_slots - 1 where dose_date = '" + changeDate + "';";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        result = true;
        return result;

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
    return result;
  }

  @Override
  public Integer getAvailableSlots(Date date) {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    Integer availableSlots = null;

    Date doseDate = date;

    if (conn != null) {
      String fetchQuery = "select * from vaccine_available_slots where dose_date = '" + doseDate + "';";
      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          availableSlots = resultSet.getInt("available_slots");
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
    return availableSlots;
  }
}
