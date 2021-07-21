package Service;

import Interface.BloodTestReportsDAO;
import Model.BloodTestReports;
import Model.GenerateLabReports;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloodTestReportsService implements BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReportsList = new ArrayList<BloodTestReports>();

  @Override
  public List<BloodTestReports> bloodTestReport(Integer user_id) {

    CustomConnection customConnection = new CustomConnection();
    Connection conn = customConnection.Connect();

    GenerateLabReports generateLabReports = new GenerateLabReports();
    if(conn != null) {
      String SQL = "select * from bloodTest_reports where user_id = " + generateLabReports.getUser_id() + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while(rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          String blood_group = rs.getString(4);
          Float white_blood_cell = rs.getFloat(5);
          Integer platelet_count = rs.getInt(6);
          Float red_blood_cell = rs.getFloat(7);
          Float hemoglobin = rs.getFloat(8);
          Float hematocrit = rs.getFloat(9);

          BloodTestReports bloodTestReports = new BloodTestReports(test_id, uid,blood_group,white_blood_cell,platelet_count,red_blood_cell,
                  hemoglobin,hematocrit);
          bloodTestReportsList.add(bloodTestReports);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
    return bloodTestReportsList;
  }

  @Override
  public void generatePDF(String data) {

//    data = bloodTestReportsList.toString();
//    Document doc = new Document();
//    try{
//      PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("bloodreport.pdf"));
//      com.itextpdf.text.List list = new com.itextpdf.text.List();
//      for(BloodTestReports bloodTestReports : bloodTestReportsList) {
//        list.add(bloodTestReports.toString());
//        doc.open();
//        doc.add(list);
//        doc.close();
//        writer.close();
////      }
//      doc.open();
//      doc.add(new Paragraph(data));
//      doc.close();
//      writer.close();
//    } catch (DocumentException e) {
//      e.printStackTrace();
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }


    Document document = new Document();
    try  {
      Random rand = new Random();
      int random_number= rand.nextInt(85000);
      PdfWriter writer = PdfWriter.getInstance(document,
              new FileOutputStream("reports/report" +random_number +".pdf"));
      document.open();
      document.add(new Paragraph(data));
      document.close();
      writer.close();
      System.out.println("PDF SAVED");
    }  catch  (DocumentException e)  {
      e.printStackTrace();
    }  catch  (FileNotFoundException e)  {
      e.printStackTrace();
    }


  }

}
