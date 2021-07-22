package Interface;

import Model.VitaminDTestReports;

import java.util.List;
/*
 *  Name of file: VitaminDTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to VitaminDTestReports model
 *               split it into another interface to apply interface segregation
 * */
public interface VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDReports(Integer user_id);

}
