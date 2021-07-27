package Interface;

import Model.BloodTestReports;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: BloodTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Laboratory feature for blood test reports
 * */
public interface BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReport(Integer user_id) throws IOException;
}
