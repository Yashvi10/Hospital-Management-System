package Interface;

import Model.BloodRequester;

import java.util.List;

/*
 *  Name of file: BloodRequesterBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Blood Bank feature for requesting blood
 * */
public interface BloodRequesterBLDAO {

  List<BloodRequester> listOfRequesters();

}
