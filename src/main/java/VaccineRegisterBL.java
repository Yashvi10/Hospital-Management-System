import Interface.VaccineRegisterUserDAO;
import Interface.VaccineRegistrationBLInterface;
import Model.VaccineUserInformation;

import java.util.List;

/*
 *  Name of file: VaccineRegisterBL.java
 *  Author:  Kushang Mistry
 *  Purpose: This class implements business logic and methods described in
 *            the interface named VaccineRegistrationBLInterface
 *  Description: The class is under Business Logic layer and
 *               provides vaccine user registration to the presentation layer class.
 * */
public class VaccineRegisterBL implements VaccineRegistrationBLInterface {

  VaccineRegisterUserDAO vaccineRegisterUserDAO;

  public VaccineRegisterBL(VaccineRegisterUserDAO vaccineRegisterUserDAO) {
    this.vaccineRegisterUserDAO = vaccineRegisterUserDAO;
  }

  public Boolean registerUserVaccine(VaccineUserInformation vaccineUserInformation) {
    if (vaccineRegisterUserDAO.registerUserVaccination(vaccineUserInformation))
      return true;
    else
      return false;
  }

  public Boolean isUserRegistered(Integer userId) {
    List<VaccineUserInformation> userInformation = vaccineRegisterUserDAO.getUserInfo(userId);
    if(userInformation.size() > 0)
      return true;
    else
      return false;
  }
}
