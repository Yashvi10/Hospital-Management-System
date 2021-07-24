package BL;

import Interface.GenerateLabReportsDAO;
import Interface.ListOfReportsBLDAO;
import Model.GenerateLabReports;
import View.Constant;
import View.Laboratory;

import java.util.List;

/*
 *  Name of file: ListOfReportsBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for getting lists of reports feature
 * */
public class ListOfReportsBL implements ListOfReportsBLDAO {

  Laboratory laboratory = new Laboratory();

  private GenerateLabReportsDAO generateLabReportsDAO;

  public ListOfReportsBL(GenerateLabReportsDAO generateLabReportsDAO) {
    this.generateLabReportsDAO = generateLabReportsDAO;
  }

  /* This method returns list of all reports one specified user_id have.
   * */
  @Override
  public List<GenerateLabReports> getReport(Integer user_id) {

    List<GenerateLabReports> generateLabReportsList = generateLabReportsDAO.generateReports(user_id);

    System.out.println(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Firstname") + " " + String.format(Constant.STRING_FORMAT, "Lastname") + " " + String.format(Constant.STRING_FORMAT, "Test Name") + " " + String.format(Constant.STRING_FORMAT, "Contact") + " " + String.format(Constant.STRING_FORMAT, "Email") + " " + String.format(Constant.STRING_FORMAT, "Gender") + " " + String.format(Constant.STRING_FORMAT, "Date of test") + " " + String.format(Constant.STRING_FORMAT, "Report Generation Date") + " " + String.format(Constant.STRING_FORMAT, "Time of test") + " " + String.format(Constant.STRING_FORMAT, "Report Generation Time"));

    for (int i = 0; i < generateLabReportsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getFirstname()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getLastname()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTest_name()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getContact()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getEmail()) + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getGender()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getDate_of_test()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getReport_generation_date()) + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getTime_of_test() + " " + String.format(Constant.STRING_FORMAT, generateLabReportsList.get(i).getReport_generation_time())));
    }
    return generateLabReportsList;
  }

}
