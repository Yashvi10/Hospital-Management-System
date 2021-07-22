package Interface;

import Model.SearchCamps;

import java.util.List;

/*
 *  Name of file: SearchCampsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to SearchCamps model
 *               split it into another interface to apply interface segregation
 * */
public interface SearchCampsDAO {

  List<SearchCamps> searchCamp(String camp_location);
}
