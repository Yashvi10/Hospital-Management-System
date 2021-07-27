package Interface;

import Model.VitaminDTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: VitaminDTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to VitaminDTests model
 * */
public interface VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDReports(Integer user_id) throws IOException;

}
