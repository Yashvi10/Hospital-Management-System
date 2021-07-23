package Interface;

import Model.UrineTestReports;

import java.io.IOException;
import java.util.List;
/*
 * File Name: UrineTestReportsDAO.java
 * Author: Yashvi Lad
 * */
public interface UrineTestReportsDAO {

  List<UrineTestReports> urineReports(Integer user_id) throws IOException;

}
