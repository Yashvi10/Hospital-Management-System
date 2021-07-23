package Interface;

/*
 *  Name of file: MedicalHistoryDAO.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is an interface for medical history module
 *  Description: This class shows the behaviour of medical history module and the respective classes
 *  can implement it
 * */

public interface MedicalHistoryDAO {

  Boolean view_medical_history();

  Boolean upload_medical_history(String path, String document_name);

  Boolean download_medical_history();

}
