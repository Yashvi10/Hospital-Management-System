package Interface;

import Model.GenerateLabReports;

import java.util.List;

public interface GenerateLabReportsDAO {

  List<GenerateLabReports> generateReports(Integer user_id);

}
