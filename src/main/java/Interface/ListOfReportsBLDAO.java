package Interface;

import Model.GenerateLabReports;

import java.util.List;

/*
 *  Name of file: ListOfReportsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Laboratory feature for listing number of reports of patient
 * */
public interface ListOfReportsBLDAO {

  List<GenerateLabReports> getReport(Integer user_id);

}
