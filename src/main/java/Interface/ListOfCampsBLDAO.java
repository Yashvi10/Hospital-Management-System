package Interface;

import Model.ListOfCamps;

import java.util.List;

/*
 *  Name of file: ListOfCampsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to ListOfCamps model
 * */
public interface ListOfCampsBLDAO {

  List<ListOfCamps> getCampsData();

}
