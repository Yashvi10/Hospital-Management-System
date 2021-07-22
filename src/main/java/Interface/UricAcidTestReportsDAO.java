package Interface;

import Model.UricAcidTestReports;
import java.util.List;
/*
 *  Name of file: UricAcidTestReportsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to UricAcidTestReports model
 *               split it into another interface to apply interface segregation
 * */
public interface UricAcidTestReportsDAO {

  List<UricAcidTestReports> uricacidReports(Integer user_id);
}
