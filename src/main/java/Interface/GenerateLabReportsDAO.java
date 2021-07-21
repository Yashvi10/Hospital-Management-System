package Interface;

import Model.GenerateLabReports;

import java.util.List;
/*
 * File Name: GenerateLabReportsDAO.java
 * Author: Yashvi Lad
 * */
public interface GenerateLabReportsDAO {

  List<GenerateLabReports> generateReports(Integer user_id);
}
