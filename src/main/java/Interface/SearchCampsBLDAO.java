package Interface;

import Model.SearchCamps;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: SearchCampsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of NearBy Camps feature to search camp in specified location
 * */
public interface SearchCampsBLDAO {

  List<SearchCamps> getCamps(String camp_location) throws IOException;

}
