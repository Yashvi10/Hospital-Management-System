package Interface;

import Model.User;
/*
 *  Name of file: BillingOrderDAO.java
 *  Author:  Abimbola Babalola
 *  Purpose: handles the methods which interact with DB
 *  Description: This interface is responsible for handling DB
 * operation related to Registration to apply interface segregation
 * */

public interface IRegistration {
  boolean registerPatient(User user );
  boolean registerStaff(String role,User user);
  Integer getLastUserId();
}
