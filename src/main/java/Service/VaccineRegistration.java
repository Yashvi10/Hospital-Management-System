package Service;

import Interface.VaccineRegisterUserDAO;
import Model.VaccineUserInformation;

/*
 *  Name of file: VaccineRegistration.java
 *  Author:  Kushang Mistry
 *  Purpose: Serves purpose of user registration for vaccine
 *  Description: This class adds user information about vaccination in the database
 * */
public class VaccineRegistration implements VaccineRegisterUserDAO {

  @Override
  public Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation) {
    return false;
  }

  @Override
  public Boolean isUserRegistered (Integer userId) {
    return false;
  }
}
