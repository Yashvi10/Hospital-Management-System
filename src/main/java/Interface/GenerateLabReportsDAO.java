package Interface;

import Model.GenerateLabReports;

import java.util.List;
/*
 *  Name of file: GenerateLabReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to GenerateLabReports model
 *               split it into another interface to apply interface segregation
 * */public interface GenerateLabReportsDAO {

  List<GenerateLabReports> generateReports(Integer user_id);
}
