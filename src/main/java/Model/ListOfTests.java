package Model;

/*
 *  Name of file: ListOfTests.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working by using getter and setter methods
 **/
public class ListOfTests {

  private Integer test_id;

  private String test_name;

  public ListOfTests(Integer test_id, String test_name) {
    this.test_id = test_id;
    this.test_name = test_name;
  }

  public Integer getTest_id() {
    return test_id;
  }

  public void setTest_id(Integer test_id) {
    this.test_id = test_id;
  }

  public String getTest_name() {
    return test_name;
  }

  public void setTest_name(String test_name) {
    this.test_name = test_name;
  }

}
