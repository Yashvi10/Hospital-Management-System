package Interface;

import Model.VitaminDTestReports;
import java.util.List;

public interface VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDReports(Integer user_id);

}
