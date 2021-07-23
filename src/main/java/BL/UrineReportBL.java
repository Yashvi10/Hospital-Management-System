package BL;

import Interface.UrineTestReportsDAO;
import Model.UrineTestReports;
import View.Constant;
import View.Laboratory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 *  Name of file: UrineReportBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for returning test report in a text file
 * */
public class UrineReportBL implements UrineTestReportsDAO {

  FileWriter fileWriter;

  Laboratory laboratory = new Laboratory();

  private UrineTestReportsDAO urineTestReportsDAO;

  public UrineReportBL(UrineTestReportsDAO urineTestReportsDAO) {
    this.urineTestReportsDAO = urineTestReportsDAO;
  }

  /* This method prints urine report of the entered user_id.
   * */
  @Override
  public List<UrineTestReports> urineReports(Integer user_id) throws IOException {

    List<UrineTestReports> urineTestReportsList = urineTestReportsDAO.urineReports(user_id);

    fileWriter = new FileWriter("resources/UrineReport" + user_id + ".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT, "Test_id") + " " + String.format(Constant.STRING_FORMAT, "User_id") + " " + String.format(Constant.STRING_FORMAT, "Colour") + " " + String.format(Constant.STRING_FORMAT, "Specific Gravity") + " " + String.format(Constant.STRING_FORMAT, "pH") + " " + String.format(Constant.STRING_FORMAT, "Blood") + " " + String.format(Constant.STRING_FORMAT, "Glucose") + " " + String.format(Constant.STRING_FORMAT, "Urobilinogen") + " " + String.format(Constant.STRING_FORMAT, "Protein") + " " + String.format(Constant.STRING_FORMAT, "Red blood cells") + " " + String.format(Constant.STRING_FORMAT, "Pus Cells") + " " + String.format(Constant.STRING_FORMAT, "Crystals") + " " + String.format(Constant.STRING_FORMAT, "Casts") + " " + String.format(Constant.STRING_FORMAT, "Turbidity") + " " + String.format(Constant.STRING_FORMAT, "White blood cells") + "\n");

    for (int i = 0; i < urineTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getUser_id()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getColor()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getSpecific_gravity()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getpH()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getBlood()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getGlucose()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getUrobilinogen()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getProtein()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getRbc()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getPus_cells()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getCrystals()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getCasts()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getTurbidity()) + " " + String.format(Constant.STRING_FORMAT, urineTestReportsList.get(i).getWbc()));
    }
    System.out.println("Report stored in a text file.");
    fileWriter.close();
    laboratory.menu();

    return urineTestReportsList;
  }

}
