import Interface.VaccineBlInterface;
import Interface.VaccineDAO;
import Model.Vaccine;

import java.util.List;

public class VaccineBL implements VaccineBlInterface {

  private VaccineDAO vaccineDAO;

  public VaccineBL(VaccineDAO vaccineDAO){
    this.vaccineDAO = vaccineDAO;
  }
  /*
   *  1 - The method which gets vaccine information from the database
   *      And displays it to the console.
   *      Hence, user can get idea which vaccines are available
   **/
  public List<Vaccine> getVaccineData() {
    List<Vaccine> vaccineList = vaccineDAO.getVaccines();   // Fetches and stores all vaccine information

    return vaccineList;
  }
}
