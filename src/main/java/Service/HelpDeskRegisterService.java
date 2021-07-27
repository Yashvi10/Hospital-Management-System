package Service;

import Interface.HelpDeskRegisterRequest;
import Model.HelpDeskRequestInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 *  Name of file: HelpDeskRegisterService.java
 *  Author:  Kushang Mistry
 *  Purpose: Serves purpose of Help Desk request registration.
 *  Description: This class handles database operations for request registration
 * */
public class HelpDeskRegisterService implements HelpDeskRegisterRequest {

  // Initialization of the object of custom connection class
  CustomConnection customConnection = new CustomConnection();

  /*
   * Registers the request from the user and assign to the staff
   * This method uses other method getValidStaffId() to figure out which staff is available
   */
  @Override
  public Boolean registerHelpDeskRequest(HelpDeskRequestInformation helpDeskRequestInformation) {

    Connection conn = customConnection.Connect();

    Integer staffId = getValidStaffId();

    if(staffId == null)
      return false;

    if (conn != null) {
      String insertQuery = "insert into helpdesk_request(description, status, staff_id, user_id)"
              + " values(\" "+ helpDeskRequestInformation.getDescription() + "\","+ helpDeskRequestInformation.getStatus()
              + "," + staffId + "," + helpDeskRequestInformation.getUserId() +")";

      Statement statement;
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
   * This method returns integer count of total active requests of particular staff using staff ID
   * this will helpful to determine and taking decision if requests are > 5
   * then no new request will be assigned to the staff
   */
  @Override
  public Integer getActiveRequestsOfStaffId(Integer staffId) {

    Connection conn = customConnection.Connect();

    ArrayList<Integer> staffMember = null;

    if (conn != null) {
      String fetchQuery = "select * from helpdesk_request where status=1 and staff_id="+staffId;

      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        staffMember = new ArrayList<>();

        while (resultSet.next()) {
          staffMember.add(resultSet.getInt("staff_id"));
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
    if(staffMember != null)
      return staffMember.size();
    else
      return null;
  }

  /*
   * This method returns random staff id whose responsibility is listed as a helper
   */
  @Override
  public Integer getRandomStaffId() {

    Connection conn = customConnection.Connect();

    Integer randomStaffId = null;

    if (conn != null) {
      String fetchQuery = "select staffID from hospitalStaff where designation='helper' ORDER BY RAND() LIMIT 1";

      Statement queryStatement = null;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          randomStaffId = resultSet.getInt("staffID");
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
    return randomStaffId;
  }

  /*
   * This method will find the staff who has total requests less than 5
   */
  private Integer getValidStaffId() {

    Integer randomStaffId = getRandomStaffId();
    if(getActiveRequestsOfStaffId(randomStaffId) < 5)
      return randomStaffId;
    else
      getValidStaffId();
    return null;
  }
}
