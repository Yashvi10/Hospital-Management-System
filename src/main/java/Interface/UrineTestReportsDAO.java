package Interface;

import Model.UrineTestReports;

import java.util.List;

public interface UrineTestReportsDAO {

  List<UrineTestReports> urineReports(Integer user_id);

}
