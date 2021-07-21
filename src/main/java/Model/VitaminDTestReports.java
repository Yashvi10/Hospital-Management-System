package Model;

/*
 * File Name: VitaminDTestReports.java
 * Author: Yashvi Lad
 * */
public class VitaminDTestReports {

  private Integer test_id;

  private Integer user_id;

  private Float hydroxy_VitaminD_serum;

  private String units;

  public VitaminDTestReports(Integer test_id, Integer user_id, Float hydroxy_VitaminD_serum, String units) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.hydroxy_VitaminD_serum = hydroxy_VitaminD_serum;
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

  public Float getHydroxy_VitaminD_serum() {
    return hydroxy_VitaminD_serum;
  }

  public void setHydroxy_VitaminD_serum(Float hydroxy_VitaminD_serum) {
    this.hydroxy_VitaminD_serum = hydroxy_VitaminD_serum;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

}
