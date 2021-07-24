package Model;

/*
 * File Name: RegisterTest.java
 * Author: Yashvi Lad
 * */
public class RegisterTest {

  private int test_id;

  private int user_id;

  private String firstname;

  private String lastname;

  private String test_name;

  private String contact;

  private String email;

  private String gender;

  private String date_of_test;

  private String report_generation_date;

  private String time_of_test;

  private String report_generation_time;

  public RegisterTest() {
  }

  public RegisterTest(int test_id, int user_id, String firstname, String lastname, String test_name, String contact, String email, String gender, String date_of_test, String report_generation_date, String time_of_test, String report_generation_time) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.test_name = test_name;
    this.contact = contact;
    this.email = email;
    this.gender = gender;
    this.date_of_test = date_of_test;
    this.report_generation_date = report_generation_date;
    this.time_of_test = time_of_test;
    this.report_generation_time = report_generation_time;
  }

  public int getTest_id() {
    return test_id;
  }

  public void setTest_id(int test_id) {
    this.test_id = test_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getTest_name() {
    return test_name;
  }

  public void setTest_name(String test_name) {
    this.test_name = test_name;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDate_of_test() {
    return date_of_test;
  }

  public void setDate_of_test(String date_of_test) {
    this.date_of_test = date_of_test;
  }

  public String getReport_generation_date() {
    return report_generation_date;
  }

  public void setReport_generation_date(String report_generation_date) {
    this.report_generation_date = report_generation_date;
  }

  public String getTime_of_test() {
    return time_of_test;
  }

  public void setTime_of_test(String time_of_test) {
    this.time_of_test = time_of_test;
  }

  public String getReport_generation_time() {
    return report_generation_time;
  }

  public void setReport_generation_time(String report_generation_time) {
    this.report_generation_time = report_generation_time;
  }
}
