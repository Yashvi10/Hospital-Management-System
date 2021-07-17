package Service;

import Interface.ListOfTestsDAO;
import Model.ListOfTests;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListOfTestsService implements ListOfTestsDAO {

    @Override
    public List<ListOfTests> getListOfTests() {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        List<ListOfTests> listOfTestsList = new ArrayList<ListOfTests>();

        if(conn != null) {
            String SQL = "select * from lab_tests";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);
                while(rs.next()) {
                    Integer test_id = rs.getInt(1);
                    String test_name = rs.getString(2);

                    ListOfTests listOfTests = new ListOfTests(test_id, test_name);
                    listOfTestsList.add(listOfTests);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listOfTestsList;
    }

}
