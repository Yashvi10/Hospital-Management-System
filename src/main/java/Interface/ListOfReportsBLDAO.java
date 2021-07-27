package Interface;

import Model.GenerateLabReports;

import java.util.List;

/*
 *  Name of file: ListOfReportsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to ListOfReports model
 * */
public interface ListOfReportsBLDAO {

  List<GenerateLabReports> getReport(Integer user_id);

}
