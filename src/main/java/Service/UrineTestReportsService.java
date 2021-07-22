package Service;

import Interface.UrineTestReportsDAO;
import Model.GenerateLabReports;
import Model.UrineTestReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * File Name: UrineTestReportsService.java
 * Author: Yashvi Lad
 * */
public class UrineTestReportsService implements UrineTestReportsDAO {


  List<UrineTestReports> urineTestReportsList = new ArrayList<UrineTestReports>();

  CustomConnection customConnection = new CustomConnection();
  @Override
  public List<UrineTestReports> urineReports(Integer user_id) {
    Connection conn = customConnection.Connect();

    GenerateLabReports generateLabReports = new GenerateLabReports();
    if (conn != null) {
      String SQL = "select * from urineTest_reports where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          String color = rs.getString(4);
          Float specific_gravity = rs.getFloat(5);
          Float pH = rs.getFloat(6);
          Float blood = rs.getFloat(7);
          Float glucose = rs.getFloat(8);
          Float urobilinogen = rs.getFloat(9);
          Float protein = rs.getFloat(10);
          Float rbc = rs.getFloat(11);
          Float pus_cells = rs.getFloat(12);
          String crystals = rs.getString(13);
          Float casts = rs.getFloat(14);
          String turbidity = rs.getString(15);
          Float wbc = rs.getFloat(16);

          UrineTestReports urineTestReports = new UrineTestReports(test_id, uid, color, specific_gravity, pH, blood, glucose, urobilinogen, protein, rbc, pus_cells, crystals, casts, turbidity, wbc);
          urineTestReportsList.add(urineTestReports);
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
    return urineTestReportsList;
  }
}
