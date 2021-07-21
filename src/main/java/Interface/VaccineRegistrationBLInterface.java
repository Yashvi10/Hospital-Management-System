package Interface;

import Model.VaccineUserInformation;

/*
 *  Name of file: VaccineBlInterface.java
 *  Author:  Kushang Mistry
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 *  Purpose: An Interface having methods of Business Logic to Register User for vaccination
 * */
public interface VaccineRegistrationBLInterface {

  Boolean registerUserVaccine(VaccineUserInformation vaccineUserInformation);

  Boolean isUserRegistered(Integer userId);
}
