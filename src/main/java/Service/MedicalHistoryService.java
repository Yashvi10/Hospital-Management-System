package Service;

import BL.MedicalHistoryBL;
import Interface.MedicalHistoryDAO;
import Model.MedicalHistoryModel;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/*
 *  Name of file: MedicalHistoryService.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class is for services offered by medical history feature
 *  Description: This class contains handling of medical records where users can upload view and download
 * their medical records
 * */

public class MedicalHistoryService implements MedicalHistoryDAO {

  MedicalHistoryModel medicalHistoryModel;
  CustomConnection cc = new CustomConnection();


  @Override
  public Boolean view_medical_history() {
    Connection con = cc.Connect();
    if (con != null) {
      try {
        String SQL = "SELECT * FROM medical_history where user_id = " +UserSession.userId;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        if (rs != null) {
          System.out.println("*************************************");
          System.out.println(String.format("%10s", "document_id") + "|" +
                  String.format("%10s", "document_name") + "|" +
                  String.format("%10s", "filename"));
          while (rs.next()) {
            System.out.println(String.format("%14s", rs.getString("document_id")) + "|" +
                    String.format("%16s", rs.getString("document_name")) + "|" +
                    String.format("%16s", rs.getString("filename")));
          }
          System.out.println("*************************************\n");
        } else {

          System.out.println("You don't have any medical records!");
          return false;
        }

        return true;
      } catch (SQLException | NullPointerException throwables) {
        throwables.printStackTrace();
      } finally{
        try  {
          con.close();
        }  catch  (SQLException throwables)  {
          throwables.printStackTrace();
        }
      }
    }
    return false;
  }

  @Override
  public Boolean upload_medical_history(String path, String document_name) {
    Connection con = cc.Connect();
    File file = new File(path);
    String filename = file.getName();
    try {
      medicalHistoryModel = new MedicalHistoryModel(UserSession.userId, document_name, filename);
      String SQL = "INSERT INTO medical_history " +
              "(user_id, document_name, filename)" +
              "VALUES (?,?,?)";
      PreparedStatement ps = con.prepareStatement(SQL);
      ps.setInt(1, medicalHistoryModel.getUser_id());
      ps.setString(2,medicalHistoryModel.getDocument_name());
      ps.setString(3, medicalHistoryModel.getFilename());
      ps.executeUpdate();
      MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
      if (medicalHistoryBL.file_write(file)){
        return true;
      }else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }finally  {
      try  {
        con.close();
      }  catch  (SQLException throwables)  {
        throwables.printStackTrace();
      }
    }
  }

  @Override
  public Boolean download_medical_history() {
    return view_medical_history();
  }

  public Boolean validateDocumentId(Integer document_id){
    Connection con = cc.Connect();
    MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
    ArrayList<ArrayList<String>> document_id_and_filename = new ArrayList<>();
    ArrayList<String> document_ids = new ArrayList<>();
    if (con != null) {
      try {
        String SQL = "SELECT * FROM medical_history";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        if (rs != null) {
          while (rs.next()) {
            document_ids.add(String.valueOf(rs.getInt("document_id")));
            document_ids.add(rs.getString("filename"));
            document_id_and_filename.add(document_ids);
          }
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }finally  {
        try  {
          con.close();
        }  catch  (SQLException throwables)  {
          throwables.printStackTrace();
        }
      }
    }
    for (int i = 0; i < document_id_and_filename.size(); i++) {
      if (document_id_and_filename.get(i).contains(document_id.toString())){
        medicalHistoryBL.print_document(document_id_and_filename.get(i).get(1));
        return true;
      }
    }
    System.out.println("Please enter a valid Document Id");
    return false;
  }

  public Boolean validateDocumentIdForDownload(Integer document_id){
    Connection con = cc.Connect();
    MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
    ArrayList<ArrayList<String>> document_id_and_filename = new ArrayList<>();
    ArrayList<String> document_ids = new ArrayList<>();
    if (con != null) {
      try {
        String SQL = "SELECT * FROM medical_history";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        if (rs != null) {
          while (rs.next()) {
            document_ids.add(String.valueOf(rs.getInt("document_id")));
            document_ids.add(rs.getString("filename"));
            document_id_and_filename.add(document_ids);
          }
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }finally  {
        try  {
          con.close();
        }  catch  (SQLException throwables)  {
          throwables.printStackTrace();
        }
      }
    }
    for (int i = 0; i < document_id_and_filename.size(); i++) {
      if (document_id_and_filename.get(i).contains(document_id.toString())){
        if (medicalHistoryBL.file_download(document_id_and_filename.get(i).get(1))){
          return true;
        }
        return false;
      }
    }
    System.out.println("Please enter a valid Document Id");
    return false;
  }


}
