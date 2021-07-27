package Interface;

import java.io.IOException;

/*
 *  Name of file: RegisterTestDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Contains method to register a test for user
 * */
public interface RegisterTestDAO {

  void registerTest() throws IOException;

  Boolean addUserDetails();

  Boolean scheduler();
}
