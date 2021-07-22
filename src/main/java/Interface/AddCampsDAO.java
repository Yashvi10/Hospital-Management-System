package Interface;

import Model.AddCamps;

/*
 *  Name of file: AddCampsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to AddCamps model
 *               split it into another interface to apply interface segregation
 * */
public interface AddCampsDAO {

  Boolean campDetails();

  Boolean addToDatabase(AddCamps addCamps);
}
