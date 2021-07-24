package Interface;

import Model.VitaminDTestReports;

import java.io.IOException;
import java.util.List;
/*
 * File Name: VitaminDTestReportsDAO.java
 * Author: Yashvi Lad
 * */
public interface VitaminDTestReportsDAO {

  List<VitaminDTestReports> vitaminDReports(Integer user_id) throws IOException;

}
