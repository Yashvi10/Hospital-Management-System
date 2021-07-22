package Interface;
/*
 *  Name of file: RegisterTestDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to RegisterTest model
 *               split it into another interface to apply interface segregation
 * */
public interface RegisterTestDAO {

  void registerTest();

  Boolean addUserDetails();

  Boolean scheduler();
}
