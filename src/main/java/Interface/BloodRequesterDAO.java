package Interface;

import Model.BloodRequester;

import java.util.List;

/*
 *  Name of file: BloodRequesterDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to BloodRequester model
 * */
public interface BloodRequesterDAO {

  Boolean addRequester(BloodRequester bloodRequester);

  Boolean updateRequester(Integer bottleQuantity, String blood_group);

  Integer isBloodAvaiable(String blood_group);

  List<BloodRequester> getAllRequesters();

}
