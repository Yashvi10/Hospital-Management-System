package Service;

import Interface.HelpDeskFaqDAO;
import Model.HelpDeskFaq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: HelpDeskFaqService.java
 *  Author:  Kushang Mistry
 *  Purpose: Lists all available FAQs.
 *  Description: Fetches information from database and lists all FAQs available.
 * */
public class HelpDeskFaqService implements HelpDeskFaqDAO {

  @Override
  public List<HelpDeskFaq> getHelpDeskFaq() {

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    List<HelpDeskFaq> helpDeskFaqs = new ArrayList<HelpDeskFaq>();

    if (conn != null) {
      String fetchQuery = "select * from helpdesk_faq";
      Statement queryStatement;
      try {
        queryStatement = conn.createStatement();
        ResultSet resultSet = queryStatement.executeQuery(fetchQuery);

        while (resultSet.next()) {
          String question = resultSet.getString(2);
          String answer = resultSet.getString(3);

          helpDeskFaqs.add(new HelpDeskFaq(question, answer));
        }
      } catch (SQLException exception) {
        exception.printStackTrace();
      } finally {
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }

    return helpDeskFaqs;
  }
}
