package BL;

import Interface.VitaminDTestReportsDAO;
import Model.VitaminDTestReports;
import View.Constant;
import View.Laboratory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 *  Name of file: VitaminDReportBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for returning test report in a text file
 * */
public class VitaminDReportBL implements VitaminDTestReportsDAO {

  FileWriter fileWriter;

  Laboratory laboratory = new Laboratory();

  private VitaminDTestReportsDAO vitaminDTestReportsDAO;

  public VitaminDReportBL(VitaminDTestReportsDAO vitaminDTestReportsDAO) {
    this.vitaminDTestReportsDAO = vitaminDTestReportsDAO;
  }

  /* This method prints Vitamin D report of the entered user_id.
   * */
  @Override
  public List<VitaminDTestReports> vitaminDReports(Integer user_id) throws IOException {

    List<VitaminDTestReports> vitaminDTestReportsList = vitaminDTestReportsDAO.vitaminDReports(user_id);

    fileWriter = new FileWriter("resources/VitaminDReport"+user_id+".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
            String.format(Constant.STRING_FORMAT,"User_id") + " " +
            String.format(Constant.STRING_FORMAT, "Hydroxy VitaminD Serum") + " " +
            String.format(Constant.STRING_FORMAT, "Units") + "\n");

    for(int i = 0 ; i < vitaminDTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getUser_id())
              + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getHydroxy_VitaminD_serum()) + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getUnits()));
    }
    System.out.println("Report stored in a text file.");
    fileWriter.close();
    laboratory.menu();

    return vitaminDTestReportsList;
  }

}
