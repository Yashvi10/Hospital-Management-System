package Service;

import Interface.GenerateLabReportsDAO;
import Model.GenerateLabReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * File Name: GenerateLabReportsService.java
 * Author: Yashvi Lad
 * */
public class GenerateLabReportsService implements GenerateLabReportsDAO {

  CustomConnection customConnection = new CustomConnection();
  @Override
  public List<GenerateLabReports> generateReports(Integer user_id) {

    Connection conn = customConnection.Connect();

    List<GenerateLabReports> generateLabReportsList = new ArrayList<GenerateLabReports>();

    if (conn != null) {
      String SQL = "select * from registered_tests where user_id = " + user_id + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next()) {
          Integer test_id = rs.getInt("test_id");
          Integer tests = rs.getInt("user_id");
          String firstname = rs.getString("firstname");
          String lastname = rs.getString("lastname");
          String test_name = rs.getString("test_name");
          String contact = rs.getString("contact");
          String email = rs.getString("email");
          String gender = rs.getString("gender");
          String date_of_test = rs.getString("date_of_test");
          String report_generation_date = rs.getString("report_generation_date");
          String time_of_test = rs.getString("time_of_test");
          String report_generation_time = rs.getString("report_generation_time");

          GenerateLabReports generateLabReports = new GenerateLabReports(test_id, tests, firstname, lastname, test_name, contact, email, gender, date_of_test, report_generation_date, time_of_test, report_generation_time);
          generateLabReportsList.add(generateLabReports);
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
    return generateLabReportsList;
  }
}
