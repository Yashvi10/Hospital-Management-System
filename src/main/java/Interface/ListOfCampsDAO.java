package Interface;

import Model.ListOfCamps;

import java.util.List;

/*
 *  Name of file: ListOfCampsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to ListOfCamps model
 *               split it into another interface to apply interface segregation
 * */
public interface ListOfCampsDAO {

  List<ListOfCamps> allCamps();

}
