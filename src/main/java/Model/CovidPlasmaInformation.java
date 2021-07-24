package Model;

/*
 *  Name of file: CovidPlasmaInformation.java
 *  Author:  Kushang Mistry
 *  Purpose: Responsible for storing - sending information about plasma
 *  Description: The class is responsible to fetch all details about single FAQ
 *               and store information in it.
 * */
public class CovidPlasmaInformation {

  private Integer plasmaId;

  private String bloodType;

  private Integer plasmaAvailability;

  public CovidPlasmaInformation(Integer plasmaId, String bloodType, Integer plasmaAvailability) {
    this.plasmaId = plasmaId;
    this.bloodType = bloodType;
    this.plasmaAvailability = plasmaAvailability;
  }

  public Integer getPlasmaId() {
    return plasmaId;
  }

  public void setPlasmaId(Integer plasmaId) {
    this.plasmaId = plasmaId;
  }

  public String getBloodType() {
    return bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public Integer getPlasmaAvailability() {
    return plasmaAvailability;
  }

  public void setPlasmaAvailability(Integer plasmaAvailability) {
    this.plasmaAvailability = plasmaAvailability;
  }
}
