package Interface;

import Model.BloodDonor;

import java.util.List;

/*
 *  Name of file: BloodDonorBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Blood Bank feature for donating blood
 * */
public interface BloodDonorBLDAO {

  List<BloodDonor> listOfDonors();

}
