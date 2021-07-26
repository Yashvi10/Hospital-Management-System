package Interface;

import Model.User;
import Service.CustomConnection;
/*
 *  Name of file: BillingOrderDAO.java
 *  Author:  Abimbola Babalola
 *  Purpose: handles the methods which interact with DB
 *  Description: This interface is responsible for handling DB
 * operation related to Registration to apply interface segregation
 * */

public interface IRegistration {
  boolean registerPatient(CustomConnection db , User user );
  boolean registerStaff(CustomConnection db,String role,User user);
}
