package Interface;

import Model.VitaminDTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: VitaminDTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Consists of method to get reports of vitaminD test
 * */
public interface VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDReports(Integer user_id) throws IOException;

}
