package Service;

import Interface.BloodTestReportsDAO;
import Model.BloodTestReports;
import Model.GenerateLabReports;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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

/*
 * File Name: BloodTestReportsService.java
 * Author: Yashvi Lad
 * */
public class BloodTestReportsService implements BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReportsList = new ArrayList<BloodTestReports>();

  CustomConnection customConnection = new CustomConnection();

  @Override
  public List<BloodTestReports> bloodTestReport(Integer user_id) {

    Connection conn = customConnection.Connect();

    if (conn != null) {
      String SQL = "select * from bloodTest_reports where user_id = " + user_id + ";";
      Statement statement = null;
      try {
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next()) {
          Integer test_id = rs.getInt(2);
          Integer uid = rs.getInt(3);
          String blood_group = rs.getString(4);
          Float white_blood_cell = rs.getFloat(5);
          Integer platelet_count = rs.getInt(6);
          Float red_blood_cell = rs.getFloat(7);
          Float hemoglobin = rs.getFloat(8);
          Float hematocrit = rs.getFloat(9);

          BloodTestReports bloodTestReports = new BloodTestReports(test_id, uid, blood_group, white_blood_cell, platelet_count, red_blood_cell, hemoglobin, hematocrit);
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

}
