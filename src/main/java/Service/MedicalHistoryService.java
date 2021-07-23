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
      file_write(file, filename);
      return true;
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

  public void file_write(File file, String filename){
    String new_path = "resources\\Uploads\\"+UserSession.userId+filename;
    try {
      File new_file = new File(new_path);
      Files.copy(file.toPath(), new_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }catch (IOException io){
      io.printStackTrace();
    }

  }

}
