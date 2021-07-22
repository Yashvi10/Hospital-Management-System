package Interface;

import Model.BloodTestReports;

import java.util.List;

/*
 *  Name of file: BloodTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to BloodTestReports model
 *               split it into another interface to apply interface segregation
 * */
public interface BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReport(Integer user_id);
}
