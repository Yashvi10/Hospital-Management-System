package BL;

import Interface.BloodTestReportsDAO;
import Model.BloodTestReports;
import View.Constant;
import View.Laboratory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 *  Name of file: BloodReportBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for report generation feature
 * */
public class BloodReportBL implements BloodTestReportsDAO {

  FileWriter fileWriter;

  Laboratory laboratory = new Laboratory();

  private BloodTestReportsDAO bloodTestReportsDAO;

  public BloodReportBL(BloodTestReportsDAO bloodTestReportsDAO) {
    this.bloodTestReportsDAO = bloodTestReportsDAO;
  }

  /* This method prints blood test report of the entered user_id.
   * */
  @Override
  public List<BloodTestReports> bloodTestReport(Integer user_id) throws IOException {
    List<BloodTestReports> bloodTestReportsList = bloodTestReportsDAO.bloodTestReport(user_id);

    fileWriter = new FileWriter("resources/bloodReport"+user_id+".txt");

    fileWriter.write(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
            String.format(Constant.STRING_FORMAT,"User_id") + " " +
            String.format(Constant.STRING_FORMAT, "Blood Group") + " " +
            String.format(Constant.STRING_FORMAT, "White Blood Cells") + " " +
            String.format(Constant.STRING_FORMAT,"Platelet Count") + " " +
            String.format(Constant.STRING_FORMAT, "Blood Cell Count") + " " +
            String.format(Constant.STRING_FORMAT, "Hemoglobin")+ " " +
            String.format(Constant.STRING_FORMAT,"Hematocrit") + "\n");

    for(int i = 0; i < bloodTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getUser_id())
              + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getBlood_group()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getWhite_blood_cell_count())
              + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getPlatelet_count()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getRed_blood_cell_count())
              + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getHemoglobin()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getHematocrit()));
    }
    System.out.println("Report stored in a text file.");
    fileWriter.close();
    laboratory.menu();
    return bloodTestReportsList;
  }

}
