import Model.*;
import Service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: LaboratoryTest.java
 *  Author:  Yashvi Lad
 *  Purpose: This class test cases related to laboratory logic
 * */
public class LaboratoryTest {
  @Mock
  UricAcidTestReportsService uricAcidTestReportsService;

  @Mock
  UrineTestReportsService urineTestReportsService;

  @Mock
  VitaminDTestReportsService vitaminDTestReportsService;

  @Mock
  BloodTestReportsService bloodTestReportsService;

  @Mock
  ListOfTestsService listOfTestsService;

  @BeforeEach
  void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void uricacidReports() {
    //FAKE OBJECT
    List<UricAcidTestReports> uricAcidTestReportsList = new ArrayList<>();
    UricAcidTestReports uricAcidTestReport1 = new UricAcidTestReports(1,10, 25.0F,"10");
    UricAcidTestReports uricAcidTestReport2 = new UricAcidTestReports(2,10, 29.0F,"2");
    uricAcidTestReportsList.add(uricAcidTestReport1);
    uricAcidTestReportsList.add(uricAcidTestReport2);
    Mockito.when(uricAcidTestReportsService.uricacidReports(anyInt())).thenReturn(uricAcidTestReportsList);
    assertEquals(uricAcidTestReportsList, uricAcidTestReportsService.uricacidReports(6));
  }

  @Test
  void UrineTestReports() {
    //FAKE OBJECT
    List<UrineTestReports> urineTestReportsList = new ArrayList<>();
    UrineTestReports urineTestReports1 = new UrineTestReports(1,10, "Pale yellow",30.0F, 6.0F,
            30.0F, 15.0F,64.5F,80.1F,32.9F,10.9F, "Crystals", 90.0F,
            "turbidity", 18.9F);

    UrineTestReports urineTestReports2 = new UrineTestReports(2,11, "colourless",32.0F, 7.0F,
            35.0F, 18.0F,81.5F,64.1F,33.9F,17.9F, "Crystals1", 90.0F,
            "turbidity1", 18.9F);
    urineTestReportsList.add(urineTestReports1);
    urineTestReportsList.add(urineTestReports2);
    Mockito.when(urineTestReportsService.urineReports(anyInt())).thenReturn(urineTestReportsList);
    assertEquals(urineTestReportsList, urineTestReportsService.urineReports(9));
  }

  @Test
  void vitaminDTestReports() {
    //FAKE OBJECT
    List<VitaminDTestReports> vitaminDTestReportsList = new ArrayList<>();
    VitaminDTestReports vitaminDTestReports1 = new VitaminDTestReports(1,10, 25.0F,"10");
    VitaminDTestReports vitaminDTestReports2 = new VitaminDTestReports(2,10, 29.0F,"2");
    vitaminDTestReportsList.add(vitaminDTestReports1);
    vitaminDTestReportsList.add(vitaminDTestReports2);
    Mockito.when(vitaminDTestReportsService.vitaminDReports(anyInt())).thenReturn(vitaminDTestReportsList);
    assertEquals(vitaminDTestReportsList, vitaminDTestReportsService.vitaminDReports(2));
  }

  @Test
  void BloodTestReports() {
    //FAKE OBJECT
    List<BloodTestReports> bloodTestReportsList = new ArrayList<>();
    BloodTestReports bloodTestReports1 = new BloodTestReports(1,10, "B+",25.2F, 12,34.0F,45.9F, 90.0F);
    BloodTestReports bloodTestReports2 = new BloodTestReports(3,10, "A+",30.6F, 20,89.0F,5.9F, 4.0F);
    bloodTestReportsList.add(bloodTestReports1);
    bloodTestReportsList.add(bloodTestReports2);
    Mockito.when(bloodTestReportsService.bloodTestReport(anyInt())).thenReturn(bloodTestReportsList);
    assertEquals(bloodTestReportsList, bloodTestReportsService.bloodTestReport(4));
  }

  @Test
  void ListOfTests() {
    //FAKE OBJECT
    List<ListOfTests> listOfTestsList = new ArrayList<>();
    ListOfTests listOfTests1 = new ListOfTests(1,"xyz");
    ListOfTests listOfTests2 = new ListOfTests(2,"abc");
    listOfTestsList.add(listOfTests1);
    listOfTestsList.add(listOfTests2);
    Mockito.when(listOfTestsService.getListOfTests()).thenReturn(listOfTestsList);
    assertEquals(listOfTestsList, listOfTestsService.getListOfTests());
  }

}

