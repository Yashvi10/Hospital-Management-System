package Interface;

import Model.SearchCamps;

import java.util.List;

public interface SearchCampsBLDAO {

  List<SearchCamps> getCamps(String camp_location);

}
