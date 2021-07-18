package Service;

import Interface.GenerateLabReportsDAO;
import Model.BloodInventory;
import Model.GenerateLabReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenerateLabReportsService implements GenerateLabReportsDAO {

  GenerateLabReports generateLabReports = new GenerateLabReports();

//  public GenerateLabReportsService(GenerateLabReports generateLabReports) {
//    this.generateLabReports = generateLabReports;
//  }

  @Override
  public List<GenerateLabReports> generateReports() {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    List<GenerateLabReports> generateLabReportsList = new ArrayList<GenerateLabReports>();

    System.out.println(generateLabReports.getUser_id());
    if(conn != null) {
      String SQL = "select * from registered_tests where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while(rs.next()) {
          Integer user_id = rs.getInt(2);

          generateLabReportsList.add(generateLabReports);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    System.out.println(generateLabReportsList);
    return generateLabReportsList;
  }

}
