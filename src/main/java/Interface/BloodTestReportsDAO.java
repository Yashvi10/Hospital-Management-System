package Interface;

import Model.BloodTestReports;

import java.io.IOException;
import java.util.List;

/*
* File Name: BloodTestReportsDAO.java
* Author: Yashvi Lad
* */
public interface BloodTestReportsDAO {

  List<BloodTestReports> bloodTestReport(Integer user_id) throws IOException;
}
