package Interface;

import Model.VaccineUserInformation;

import java.util.List;

/*
 *  Name of file: VaccineBlInterface.java
 *  Author:  Kushang Mistry
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 *  Purpose: An Interface having methods of Business Logic to Register User for vaccination
 * */
public interface VaccineRegistrationBLInterface {

  Boolean registerUserVaccine(VaccineUserInformation vaccineUserInformation);

  Integer checkUserRegistration(Integer userId);

  Boolean getUserInformation();

  VaccineUserInformation getUserDetails(Integer userId);

  List<VaccineUserInformation> getDosageInformation();
}
