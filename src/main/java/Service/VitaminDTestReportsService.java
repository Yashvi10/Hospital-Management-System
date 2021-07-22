package Service;

import Interface.VitaminDTestReportsDAO;
import Model.VitaminDTestReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 *  Name of file: VitaminDTestReportsService.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like service which will implement the VitaminDTestReportsDAO
 *  Description: This class will implement all the actual logic define in VitaminDTestReportsDAO
 * */
public class VitaminDTestReportsService implements VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDTestReportsList = new ArrayList<VitaminDTestReports>();

  CustomConnection customConnection = new CustomConnection();
  @Override
  public List<VitaminDTestReports> vitaminDReports(Integer user_id) {

    Connection conn = customConnection.Connect();

    if (conn != null) {
      String SQL = "select * from vitaminDTest_reports where user_id = '" + user_id + "';";
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
