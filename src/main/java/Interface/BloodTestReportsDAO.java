package Interface;

import Model.BloodTestReports;

import java.io.IOException;
import java.util.List;

/*
 *  Name of file: BloodTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to BloodTestReport model
 * */
public interface BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReport(Integer user_id) throws IOException;
}
