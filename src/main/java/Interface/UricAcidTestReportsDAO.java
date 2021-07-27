package Interface;

import Model.UricAcidTestReports;

import java.io.IOException;
import java.util.List;
/*
 *  Name of file: UricAcidTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to UricAcidTest model
 * */
public interface UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricacidReports(Integer user_id) throws IOException;
}
