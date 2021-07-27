package Interface;

import Model.ListOfTests;

import java.util.List;

/*
 *  Name of file: ListOfTestsBLDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate the business logic methods of Laboratory feature for listing tests
 * */
public interface ListOfTestsBLDAO {

  List<ListOfTests> getTests();

}
