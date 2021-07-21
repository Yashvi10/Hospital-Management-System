package Interface;

import Model.BloodTestReports;

import java.util.List;

public interface BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReport(Integer user_id);

  void generatePDF(String data);

}
