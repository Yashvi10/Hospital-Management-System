package Interface;

import Model.ListOfTests;

import java.util.List;

/*
 *  Name of file: ListOfTestsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to ListOfTests model
 * */
public interface ListOfTestsBLDAO {

  List<ListOfTests> getTests();

}
