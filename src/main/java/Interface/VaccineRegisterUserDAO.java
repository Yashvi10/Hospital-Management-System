package Interface;

import Model.VaccineUserInformation;

import java.sql.Date;
import java.util.List;

/*
 *  Name of file: VaccineRegisterUser.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to Register User for vaccination
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 * */
public interface VaccineRegisterUserDAO {

  Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation);

  List<VaccineUserInformation> getVaccinationInfo(Integer userId);

  VaccineUserInformation getUserVaccineData(Integer userId);

  Boolean updateSlotAvailability(Date date);

  Integer getAvailableSlots(Date date);

  Boolean updateVaccineDoses(Integer vaccineId);

  //Integer getTotalVaccineDoses(Integer vaccineId);
}
