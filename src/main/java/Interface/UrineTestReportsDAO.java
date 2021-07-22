package Interface;

import Model.UrineTestReports;

import java.util.List;
/*
 *  Name of file: UrineTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to UrineTestReports model
 *               split it into another interface to apply interface segregation
 * */
public interface UrineTestReportsDAO {

  List<UrineTestReports> urineReports(Integer user_id);

}
