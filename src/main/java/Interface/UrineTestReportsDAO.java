package Interface;

import Model.UrineTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: UrineTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Consists of method to get reports of urine test
 * */
public interface UrineTestReportsDAO {

  List<UrineTestReports> urineReports(Integer user_id) throws IOException;

}
