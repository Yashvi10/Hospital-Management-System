package Model;

public class RegisterTest {

  private int test_id;

  private int user_id;

  private String firstname;

  private String lastname;

  private String contact;

  private String email;

  private String gender;

  private String test_date;

  public RegisterTest(int test_id, int user_id, String firstname, String lastname, String contact,
                      String email, String gender, String test_date) {
    this.test_id = test_id;
    this.user_id = user_id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.contact = contact;
    this.email = email;
    this.gender = gender;
    this.test_date = test_date;
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

  public String getTest_date() {
    return test_date;
  }

  public void setTest_date(String test_date) {
    this.test_date = test_date;
  }

}
