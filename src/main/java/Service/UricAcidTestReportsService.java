package Service;

import Interface.UricAcidTestReportsDAO;
import Model.GenerateLabReports;
import Model.UricAcidTestReports;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UricAcidTestReportsService implements UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricAcidTestReportsList = new ArrayList<UricAcidTestReports>();

  @Override
  public List<UricAcidTestReports> uricacidReports(Integer user_id) {
    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    GenerateLabReports generateLabReports = new GenerateLabReports();
    if(conn != null) {
      String SQL = "select * from uric_acidTest_Reports where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while(rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          Float serum_uric_acid = rs.getFloat(4);
          String  units = rs.getString(5);

          UricAcidTestReports uricAcidTestReports = new UricAcidTestReports(test_id, uid,serum_uric_acid,units);
          uricAcidTestReportsList.add(uricAcidTestReports);
          //          generatePDF(bloodTestReportsList.toString());
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return uricAcidTestReportsList;
  }

}
