package Service;

import Interface.VaccineRegisterUserDAO;
import Model.VaccineUserInformation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *  Name of file: VaccineRegistration.java
 *  Author:  Kushang Mistry
 *  Purpose: Serves purpose of user registration for vaccine
 *  Description: This class adds user information about vaccination in the database
 * */
public class VaccineRegistration implements VaccineRegisterUserDAO {

  @Override
  public Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation) {

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    if (conn != null) {
      String insertQuery = "insert into vaccination_patients(userId, mail_id, age, gender, government_id_number, preferred_next_date, preferred_timing) " +
              "values(" + vaccineUserInformation.getUserId() + ",'" + vaccineUserInformation.getMailId()
              +"'," + vaccineUserInformation.getAge() + ",'" + vaccineUserInformation.getGender()
              +"','" + vaccineUserInformation.getGovernmentId() + "','" + vaccineUserInformation.getPreferredDate()
              +"','" + vaccineUserInformation.getPreferredTiming() +"')";

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

  @Override
  public Boolean isUserRegistered (Integer userId) {
    return false;
  }
}
