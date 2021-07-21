package Interface;

import Model.VaccineUserInformation;

import java.util.List;

/*
 *  Name of file: VaccineRegisterUser.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to Register User for vaccination
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 * */
public interface VaccineRegisterUserDAO {

  Boolean registerUserVaccination(VaccineUserInformation vaccineUserInformation);

  List<VaccineUserInformation> getUserInfo(Integer userId);
}
