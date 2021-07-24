package Model;

/*
 * File Name: UricAcidTestReports.java
 * Author: Yashvi Lad
 * */
public class UricAcidTestReports {

  private Integer test_id;

  private Integer user_id;

  private Float serum_uric_acid;

  private String units;

  public UricAcidTestReports(Integer test_id, Integer user_id, Float serum_uric_acid, String units) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.serum_uric_acid = serum_uric_acid;
    this.units = units;
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

  public Float getSerum_uric_acid() {
    return serum_uric_acid;
  }

  public void setSerum_uric_acid(Float serum_uric_acid) {
    this.serum_uric_acid = serum_uric_acid;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

}
