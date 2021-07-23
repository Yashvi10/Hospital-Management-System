package BL;

import Interface.UricAcidTestReportsDAO;
import Model.UricAcidTestReports;
import View.Constant;
import View.Laboratory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 *  Name of file: UricAcidReportBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for returning test report in a text file
 * */
public class UricAcidReportBL implements UricAcidTestReportsDAO {

  FileWriter fileWriter;

  Laboratory laboratory = new Laboratory();

  private UricAcidTestReportsDAO uricAcidTestReportsDAO;

  public UricAcidReportBL(UricAcidTestReportsDAO uricAcidTestReportsDAO) {
    this.uricAcidTestReportsDAO = uricAcidTestReportsDAO;
  }

  /* This method prints uric-acid report of the entered user_id.
   * */
  @Override
  public List<UricAcidTestReports> uricacidReports(Integer user_id) throws IOException {

    List<UricAcidTestReports> uricAcidTestReportsList = uricAcidTestReportsDAO.uricacidReports(user_id);

    fileWriter = new FileWriter("resources/UricAcidReport"+user_id+".txt");
    fileWriter.write(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
            String.format(Constant.STRING_FORMAT,"User_id") + " " +
            String.format(Constant.STRING_FORMAT, "Serum Uric Acid") + " " +
            String.format(Constant.STRING_FORMAT, "Units") + "\n");

    for(int i = 0; i < uricAcidTestReportsList.size(); i++) {
      fileWriter.write(String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getUser_id())
              + " " + String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getSerum_uric_acid()) + " " + String.format(uricAcidTestReportsList.get(i).getUnits(),uricAcidTestReportsList.get(i).getUnits()));
    }
    System.out.println("Report stored in a text file.");
    fileWriter.close();
    laboratory.menu();
    return uricAcidTestReportsList;
  }

}
