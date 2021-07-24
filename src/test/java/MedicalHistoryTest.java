import BL.MedicalHistoryBL;
import Service.MedicalHistoryService;
import Service.UserSession;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: MedicalHistoryTest.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class is for testing the working of medical history feature
 *  Description: This class contains different test cases required to make sure that medical history feature works fine
 * */

public class MedicalHistoryTest {

  @Test
  void upload_medical_history() {
    File file_to_upload = new File("Dummy files/Medical1.txt");
    MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
    medicalHistoryBL.file_write(file_to_upload);
    File uploaded_file = new File("resources/Uploads/"+0+file_to_upload.getName());
    assertEquals(true,uploaded_file.exists(),"file uploading fails!");
  }

  @Test
  void download_medical_history() {
    File file_to_download = new File("Dummy files/Medical1.txt");
    MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
    medicalHistoryBL.file_download(file_to_download.getName());
    File downloaded_file = new File("resources/Downloads/"+0+file_to_download.getName());
    assertEquals(true,downloaded_file.exists(),"file uploading fails!");
  }

}
