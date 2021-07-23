package BL;

import Service.MedicalHistoryService;
import Service.UserSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class MedicalHistoryBL {


  public Boolean file_write(File file){
    String new_path = "resources\\Uploads\\"+ UserSession.userId+file.getName();
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

  public Boolean file_download(String filename){
    String path = "resources\\Uploads\\"+ UserSession.userId+filename;
    File file = new File(path);
    String new_path = "resources\\Downloads\\"+file.getName();
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

  public Boolean view_medical_history(Integer document_id) {

    MedicalHistoryService medicalHistoryService = new MedicalHistoryService();
    if (medicalHistoryService.validateDocumentId(document_id)){
      return true;
    }else {
      return false;
    }

  }

  public void print_document(String filename){
    String path = "resources\\Uploads\\"+ UserSession.userId+filename;
    try {
      File file = new File(path);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String data = scanner.nextLine();
        System.out.println(data);
      }
    }catch (IOException io){
      io.printStackTrace();
    }

  }


}
