package Interface;

import Model.GenerateLabReports;

import java.util.List;
/*
 *  Name of file: GenerateLabReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Laboratory feature for report generation
 * */
public interface GenerateLabReportsDAO {

  List<GenerateLabReports> generateReports(Integer user_id);
}
