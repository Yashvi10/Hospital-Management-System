package Model;

/*
 *  Name of file: UrineTestReports.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working by using getter and setter methods
 **/
public class UrineTestReports {

  private Integer test_id;

  private Integer user_id;

  private String color;

  private Float specific_gravity;

  private Float pH;

  private Float blood;

  private Float glucose;

  private Float urobilinogen;

  private Float protein;

  private Float rbc;

  private Float pus_cells;

  private String crystals;

  private Float casts;

  private String turbidity;

  private Float wbc;

  public UrineTestReports(Integer test_id, Integer user_id, String color, Float specific_gravity, Float pH, Float blood, Float glucose, Float urobilinogen, Float protein, Float rbc, Float pus_cells, String crystals, Float casts, String turbidity, Float wbc) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.color = color;
    this.specific_gravity = specific_gravity;
    this.pH = pH;
    this.blood = blood;
    this.glucose = glucose;
    this.urobilinogen = urobilinogen;
    this.protein = protein;
    this.rbc = rbc;
    this.pus_cells = pus_cells;
    this.crystals = crystals;
    this.casts = casts;
    this.turbidity = turbidity;
    this.wbc = wbc;
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Float getSpecific_gravity() {
    return specific_gravity;
  }

  public void setSpecific_gravity(Float specific_gravity) {
    this.specific_gravity = specific_gravity;
  }

  public Float getpH() {
    return pH;
  }

  public void setpH(Float pH) {
    this.pH = pH;
  }

  public Float getBlood() {
    return blood;
  }

  public void setBlood(Float blood) {
    this.blood = blood;
  }

  public Float getGlucose() {
    return glucose;
  }

  public void setGlucose(Float glucose) {
    this.glucose = glucose;
  }

  public Float getUrobilinogen() {
    return urobilinogen;
  }

  public void setUrobilinogen(Float urobilinogen) {
    this.urobilinogen = urobilinogen;
  }

  public Float getProtein() {
    return protein;
  }

  public void setProtein(Float protein) {
    this.protein = protein;
  }

  public Float getRbc() {
    return rbc;
  }

  public void setRbc(Float rbc) {
    this.rbc = rbc;
  }

  public Float getPus_cells() {
    return pus_cells;
  }

  public void setPus_cells(Float pus_cells) {
    this.pus_cells = pus_cells;
  }

  public String getCrystals() {
    return crystals;
  }

  public void setCrystals(String crystals) {
    this.crystals = crystals;
  }

  public Float getCasts() {
    return casts;
  }

  public void setCasts(Float casts) {
    this.casts = casts;
  }

  public String getTurbidity() {
    return turbidity;
  }

  public void setTurbidity(String turbidity) {
    this.turbidity = turbidity;
  }

  public Float getWbc() {
    return wbc;
  }

  public void setWbc(Float wbc) {
    this.wbc = wbc;
  }

}
