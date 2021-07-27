package BL;

import Interface.VaccineBLInterface;
import Interface.VaccineDAO;
import Model.Vaccine;

import java.util.List;

/*
 *  Name of file: VaccineBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implemented Vaccine information related Business Logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class VaccineBL implements VaccineBLInterface {

  // Instance of Interface
  private VaccineDAO vaccineDAO;

  // A constructor of the class
  public VaccineBL(VaccineDAO vaccineDAO) {
    this.vaccineDAO = vaccineDAO;
  }

  /*
   * The method which gets vaccine information from the database
   * And returns the list.
   * Hence, user can get idea which vaccines are available
   **/
  public List<Vaccine> getVaccineData() {

    List<Vaccine> vaccineList = vaccineDAO.getVaccines();   // Fetches and stores all vaccine information

    return vaccineList;
  }
}
