package Interface;

import Model.UricAcidTestReports;

import java.io.IOException;
import java.util.List;
/*
 * File Name: UricAcidTestReportsDAO.java
 * Author: Yashvi Lad
 * */
public interface UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricacidReports(Integer user_id) throws IOException;
}
