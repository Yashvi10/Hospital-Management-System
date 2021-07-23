package BL;

import Interface.ListOfTestsBLDAO;
import Interface.ListOfTestsDAO;
import Model.ListOfTests;
import View.Constant;

import java.util.List;

/*
 *  Name of file: ListOfTestsBL.java
 *  Author:  Yashvi Lad
 *  Purpose & Description: This class hold the business logic for getting lists of test feature
 * */
public class ListOfTestsBL implements ListOfTestsBLDAO {

  private ListOfTestsDAO listOfTestsDAO;

  public ListOfTestsBL(ListOfTestsDAO listOfTestsDAO) {
    this.listOfTestsDAO = listOfTestsDAO;
  }

  /* This method returns list of all tests from lab_tests
   */
  @Override
  public List<ListOfTests> getTests() {

    List<ListOfTests> listOfTestsList = listOfTestsDAO.getListOfTests();

    System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
            String.format(Constant.STRING_FORMAT, "Test Name"));

    for(int i =0;i<listOfTestsList.size();i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_name()));
    }
    return listOfTestsList;
  }

}
