package Interface;

import Model.ListOfCamps;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: ListOfCampsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Nearby Camps feature
 * */
public interface ListOfCampsBLDAO {

  List<ListOfCamps> getCampsData() throws IOException;

}
