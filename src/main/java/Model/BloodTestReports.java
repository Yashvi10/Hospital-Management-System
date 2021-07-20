package Model;

public class BloodTestReports {

  private Integer test_id;

  private Integer user_id;

  private String Blood_group;

  private Float White_blood_cell_count;

  private Integer Platelet_count;

  private Float Red_blood_cell_count;

  private Float Hemoglobin;

  private Float Hematocrit;

  public BloodTestReports(Integer test_id, Integer user_id, String Blood_group, Float White_blood_cell_count,
                          Integer Platelet_count, Float Red_blood_cell_count, Float Hemoglobin, Float Hematocrit) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.Blood_group = Blood_group;
    this.White_blood_cell_count = White_blood_cell_count;
    this.Platelet_count = Platelet_count;
    this.Red_blood_cell_count = Red_blood_cell_count;
    this.Hemoglobin = Hemoglobin;
    this.Hematocrit = Hematocrit;
  }

  public Integer getTest_id() {
    return test_id;
  }

  public void setTest_id(Integer test_id) {
    this.test_id = test_id;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getBlood_group() {
    return Blood_group;
  }

  public void setBlood_group(String Blood_group) {
    this.Blood_group = Blood_group;
  }

  public Float getWhite_blood_cell_count() {
    return White_blood_cell_count;
  }

  public void setWhite_blood_cell_count(Float White_blood_cell_count) {
    this.White_blood_cell_count = White_blood_cell_count;
  }

  public Integer getPlatelet_count() {
    return Platelet_count;
  }

  public void setPlatelet_count(Integer Platelet_count) {
    this.Platelet_count = Platelet_count;
  }

  public Float getRed_blood_cell_count() {
    return Red_blood_cell_count;
  }

  public void setRed_blood_cell_count(Float red_blood_cell_count) {
    this.Red_blood_cell_count = Red_blood_cell_count;
  }

  public Float getHemoglobin() {
    return Hemoglobin;
  }

  public void setHemoglobin(Float Hemoglobin) {
    this.Hemoglobin = Hemoglobin;
  }

  public Float getHematocrit() {
    return Hematocrit;
  }

  public void setHematocrit(Float Hematocrit) {
    this.Hematocrit = Hematocrit;
  }

}
