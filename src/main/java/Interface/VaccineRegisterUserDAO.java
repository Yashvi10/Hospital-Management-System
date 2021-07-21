package Interface;

import Model.VaccineUserInformation;

/*
 *  Name of file: VaccineRegisterUser.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to Register User for vaccination
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 * */
public interface VaccineRegisterUserDAO {

  Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation);

  Boolean isUserRegistered(Integer userId);
}
