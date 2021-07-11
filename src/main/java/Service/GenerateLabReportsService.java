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

    @Override
    public List<GenerateLabReports> getListOfTests() {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        List<GenerateLabReports> generateLabReportsList = new ArrayList<GenerateLabReports>();

        if(conn != null) {
            String SQL = "select * from lab_tests";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);
                while(rs.next()) {
                    Integer test_id = rs.getInt(1);
                    String test_name = rs.getString(2);

                    GenerateLabReports generateLabReports = new GenerateLabReports(test_id, test_name);
                    generateLabReportsList.add(generateLabReports);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return generateLabReportsList;
    }

}
