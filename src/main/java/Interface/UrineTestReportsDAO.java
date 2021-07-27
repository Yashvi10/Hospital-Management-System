package Interface;

import Model.UrineTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: UrineTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to UrineTest model
 * */
public interface UrineTestReportsDAO {

  List<UrineTestReports> urineReports(Integer user_id) throws IOException;

}
