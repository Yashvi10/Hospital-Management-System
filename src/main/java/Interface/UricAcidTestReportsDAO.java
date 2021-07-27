package Interface;

import Model.UricAcidTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: UricAcidTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Consists of method to get reports of uric acid test
 * */
public interface UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricacidReports(Integer user_id) throws IOException;
}
