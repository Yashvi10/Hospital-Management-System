package Interface;

import Model.UricAcidTestReports;

import java.util.List;

public interface UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricacidReports(Integer user_id);
}
