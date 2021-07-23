package Interface;

public interface MedicalHistoryDAO {

  Boolean view_medical_history();

  Boolean upload_medical_history(String path, String document_name);

  Boolean download_medical_history();

}
