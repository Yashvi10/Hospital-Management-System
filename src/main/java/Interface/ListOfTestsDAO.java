package Interface;

import Model.ListOfTests;
import java.util.List;
/*
 *  Name of file: ListOfTestsDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to ListOfTests model
 *               split it into another interface to apply interface segregation
 * */
public interface ListOfTestsDAO {

  List<ListOfTests> getListOfTests();
}
