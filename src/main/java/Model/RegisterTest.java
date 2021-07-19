package Model;

import java.time.LocalDate;

public class RegisterTest {

  private int test_id;

  private int user_id;

  private String firstname;

  private String lastname;

  private String test_name;

  private String contact;

  private String email;

  private String gender;

  private LocalDate date_of_test;

  private LocalDate report_generation_date;

  public RegisterTest() {}

  public RegisterTest(int test_id, int user_id, String firstname, String lastname, String test_name, String contact,
                      String email, String gender, LocalDate date_of_test, LocalDate report_generation_date) {
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

  public String getTest_name() {
    return test_name;
  }

  public void setTest_name(String test_name) {
    this.test_name = test_name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
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

  public LocalDate getDate_of_test() {
    return date_of_test;
  }

  public void setDate_of_test(LocalDate date_of_test) {
    this.date_of_test = date_of_test; }

  public LocalDate getReport_generation_date() {
    return report_generation_date;
  }

  public void setReport_generation_date(LocalDate report_generation_date) {
    this.report_generation_date = report_generation_date;
  }

}
