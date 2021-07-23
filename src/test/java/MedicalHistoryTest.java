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
    File file_to_upload = new File("Dummy files\\Medical1.txt");
    MedicalHistoryService medicalHistoryService = new MedicalHistoryService();
    medicalHistoryService.file_write(file_to_upload);
    File uploaded_file = new File("resources\\Uploads\\"+UserSession.userId+file_to_upload.getName());
    assertEquals(true,uploaded_file.exists(),"file uploading fails!");
  }

}
