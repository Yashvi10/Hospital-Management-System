package Interface;

import Model.GenerateLabReports;

import java.util.List;
/*
 *  Name of file: GenerateLabReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to GenerateReport model
 * */
public interface GenerateLabReportsDAO {

  List<GenerateLabReports> generateReports(Integer user_id);
}
