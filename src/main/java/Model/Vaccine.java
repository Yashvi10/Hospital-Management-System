package Model;

/*
 *  Name of file: Vaccine.java
 *  Author:  Kushang Mistry
 *  Purpose: Lists name of all approved vaccines
 *  Description: The class is responsible to fetch all details about available vaccines
 *               and total available doses
 * */
public class Vaccine {

  private Integer vaccineId;

  private String vaccineName;

  private Integer availableDoses;

  public Vaccine(Integer vaccineId, String vaccineName, Integer availableDoses) {
    this.vaccineId = vaccineId;
    this.vaccineName = vaccineName;
    this.availableDoses = availableDoses;
  }

  public Integer getVaccineId() {
    return vaccineId;
  }

  public void setVaccineId(Integer vaccineId) {
    this.vaccineId = vaccineId;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public Integer getAvailableDoses() {
    return availableDoses;
  }

  public void setAvailableDoses(Integer availableDoses) {
    this.availableDoses = availableDoses;
  }
}
