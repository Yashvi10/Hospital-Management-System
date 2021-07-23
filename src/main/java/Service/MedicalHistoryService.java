package Service;

import Interface.MedicalHistoryDAO;
import Model.MedicalHistoryModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *  Name of file: MedicalHistoryService.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class is for services offered by medical history feature
 *  Description: This class contains logic and handling of medical records where users can upload view and download
 * their medical records
 * */

public class MedicalHistoryService implements MedicalHistoryDAO {

  MedicalHistoryModel medicalHistoryModel;
  CustomConnection cc = new CustomConnection();
  Connection con = cc.Connect();

  @Override
  public Boolean view_medical_history() {

    return true;
  }

  @Override
  public Boolean upload_medical_history(String path, String document_name) {
//    String[] get_filename = path.split("\\\\");
//    String filename = get_filename[get_filename.length-1];
    File file = new File("D:\\CSCI 5308(Advance Software Development Conecpts)\\Project\\Medical1.txt");
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
      if (file_write(file)){
        return true;
      }else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally  {
      try  {
        con.close();
      }  catch  (SQLException throwables)  {
        throwables.printStackTrace();
      }
    }
    return true;
  }

  @Override
  public Boolean download_medical_history() {
    return true;
  }

  public Boolean file_write(File file){
    String new_path = "resources\\Uploads\\"+UserSession.userId+file.getName();
    try {
      File new_file = new File(new_path);
      if (!new_file.exists()){
        Files.copy(file.toPath(), new_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      return true;
    }catch (IOException io){
      io.printStackTrace();
    }
    return false;
  }

}
