package Model;


/*
 *  Name of file: MedicalHistoryModel.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is the model class for database tables mapping
 *  Description: This class is used for mapping variables to interact with database tables
 *  and to easily get and set these variables
 * */
public class MedicalHistoryModel {

  private Integer document_id;

  private Integer user_id;

  private String document_name;

  private String filename;

  public MedicalHistoryModel(Integer userId, String document_name, String filename) {
    this.user_id = userId;
    this.document_name = document_name;
    this.filename = filename;
  }

  public Integer getDocument_id() {
    return document_id;
  }

  public void setDocument_id(Integer document_id) {
    this.document_id = document_id;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getDocument_name() {
    return document_name;
  }

  public void setDocument_name(String document_name) {
    this.document_name = document_name;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

}
