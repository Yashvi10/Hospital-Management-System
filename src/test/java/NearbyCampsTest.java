import Model.ListOfCamps;
import Model.SearchCamps;
import Service.ListOfCampsService;
import Service.SearchCampsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;

/*
 *  Name of file: NearbyCampsTest.java
 *  Author:  Yashvi Lad
 *  Purpose: This class test cases related to camps logic
 * */
public class NearbyCampsTest {

  @Mock
  ListOfCampsService listOfCampsService;

  @Mock
  SearchCampsService searchCampsService;

  @BeforeEach
  void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void listOfCamps() {
    //FAKE OBJECT
    List<ListOfCamps> listOfCampsList = new ArrayList<>();
    ListOfCamps listOfCamps1 = new ListOfCamps("dental","checkups","halifax","Doctor");
    ListOfCamps listOfCamps2 = new ListOfCamps("eye","checkups","dartmouth","nurse");
    listOfCampsList.add(listOfCamps1);
    listOfCampsList.add(listOfCamps2);
    Mockito.when(listOfCampsService.allCamps()).thenReturn(listOfCampsList);
    assertEquals(listOfCampsList, listOfCampsService.allCamps());
  }

  @Test
  void searchCamps() {
    //FAKE OBJECT
    List<SearchCamps> searchCampsList = new ArrayList<>();
    SearchCamps searchCamps1 = new SearchCamps("dental","checkups","halifax","Doctor");
    SearchCamps searchCamps2 = new SearchCamps("eye","checkups","dartmouth","nurse");
    searchCampsList.add(searchCamps1);
    searchCampsList.add(searchCamps2);
    Mockito.when(searchCampsService.searchCamp(anyString())).thenReturn(searchCampsList);
    assertEquals(searchCampsList, searchCampsService.searchCamp("halifax"));
  }

}
