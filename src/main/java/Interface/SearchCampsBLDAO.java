package Interface;

import Model.SearchCamps;

import java.util.List;

/*
 *  Name of file: SearchCampsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to SearchCamps model
 * */
public interface SearchCampsBLDAO {

  List<SearchCamps> getCamps(String camp_location);

}
