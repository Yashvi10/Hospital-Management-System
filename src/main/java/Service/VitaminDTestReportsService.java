package Service;

import Interface.VitaminDTestReportsDAO;
import Model.GenerateLabReports;
import Model.VitaminDTestReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * File Name: VitaminDTestReportsService.java
 * Author: Yashvi Lad
 * */
public class VitaminDTestReportsService implements VitaminDTestReportsDAO {


  List<VitaminDTestReports> vitaminDTestReportsList = new ArrayList<VitaminDTestReports>();

  CustomConnection customConnection = new CustomConnection();
  @Override
  public List<VitaminDTestReports> vitaminDReports(Integer user_id) {

    Connection conn = customConnection.Connect();

    GenerateLabReports generateLabReports = new GenerateLabReports();
    if (conn != null) {
      String SQL = "select * from vitaminDTest_reports where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          Float hydroxy_VitaminD_serum = rs.getFloat(4);
          String units = rs.getString(5);

          VitaminDTestReports vitaminDTestReports = new VitaminDTestReports(test_id, uid, hydroxy_VitaminD_serum, units);
          vitaminDTestReportsList.add(vitaminDTestReports);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }finally {
              try {
                conn.close();
              } catch (SQLException throwables) {
                throwables.printStackTrace();
              }
            }
    }
    return vitaminDTestReportsList;
  }
}
