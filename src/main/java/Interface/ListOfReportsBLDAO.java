package Interface;

import Model.GenerateLabReports;

import java.util.List;

public interface ListOfReportsBLDAO {

  List<GenerateLabReports> getReport(Integer user_id);

}
