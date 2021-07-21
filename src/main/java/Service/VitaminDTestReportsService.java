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
 *  Name of file: VitaminDTestReportsService.java
 *  Author:  Yashvi Lad
 *  Purpose: Gets report of Vitamin-D test.
 *  Description: This service class fetches all the details of report of the given user_id and provides it
 *                to the user.
 * */

public class VitaminDTestReportsService implements VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDTestReportsList = new ArrayList<VitaminDTestReports>();

  @Override
  public List<VitaminDTestReports> vitaminDReports(Integer user_id) {

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    GenerateLabReports generateLabReports = new GenerateLabReports();
    if(conn != null) {
      String SQL = "select * from vitaminDTest_reports where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while(rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          Float hydroxy_VitaminD_serum = rs.getFloat(4);
          String  units = rs.getString(5);

          VitaminDTestReports vitaminDTestReports = new VitaminDTestReports(test_id, uid,hydroxy_VitaminD_serum,units);
          vitaminDTestReportsList.add(vitaminDTestReports);
//          generatePDF(bloodTestReportsList.toString());
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
//      finally {
//        try {
//          conn.close();
//        } catch (SQLException throwables) {
//          throwables.printStackTrace();
//        }
//      }
    }
    return vitaminDTestReportsList;
  }

}
