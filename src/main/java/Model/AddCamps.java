package Model;

/*
 *  Name of file: AddCamps.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working by using getter and setter methods
 * */
public class AddCamps {

  private String camp_type;

  private String camp_description;

  private String camp_location;

  private String camp_addedBy;

  public AddCamps(String camp_type, String camp_description, String camp_location, String camp_addedBy) {
    this.camp_type = camp_type;
    this.camp_description = camp_description;
    this.camp_location = camp_location;
    this.camp_addedBy = camp_addedBy;
  }

  public String getCamp_type() {
    return camp_type;
  }

  public void setCamp_type(String camp_type) {
    this.camp_type = camp_type;
  }

  public String getCamp_description() {
    return camp_description;
  }

  public void setCamp_description(String camp_description) {
    this.camp_description = camp_description;
  }

  public String getCamp_location() {
    return camp_location;
  }

  public void setCamp_location(String camp_location) {
    this.camp_location = camp_location;
  }

  public String getCamp_addedBy() {
    return camp_addedBy;
  }

  public void setCamp_addedBy(String camp_addedBy) {
    this.camp_addedBy = camp_addedBy;
  }

}
