package Model;

import Interface.IRegistration;

/*
 *  Name of file: BloodDonor.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class handles the mapping of the inputs to the models.
 *  Description: The file will set and get the inputs for related models
 * */

public class User {

  private Integer userid;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private String email;
  private String confirmEmail;
  private String pswd;
  private String confirmPswd;
  private int checkrecord;

  public User(){}

  public User(String firstName, String lastName, String address, String phone, String email, String confirmEmail,
         String pswd,String confirmPswd, IRegistration register ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.pswd = pswd;
    this.confirmEmail = confirmEmail;
    this.confirmPswd = confirmPswd;
    checkrecord=register.loadRecord(this);
  }

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public String getfirstName() {
    return firstName;
  }

  public void setfirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getlastName() {
    return lastName;
  }

  public void setlastName(String lastName) {
    this.lastName = lastName;
  }

  public String getphone() {
    return phone;
  }

  public void setphone(String phone) {
    this.phone = phone;
  }

  public String getaddress() {
    return address;
  }

  public void setaddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getconfirmEmail() {
    return confirmEmail;
  }

  public void setconfirmEmail(String confirmEmail) {
    this.confirmEmail = confirmEmail;
  }

  public String getPswd() {
    return pswd;
  }

  public void setPswd(String pswd) {
    this.pswd = pswd;
  }

  public String getconfirmPswd() {
    return confirmPswd;
  }

  public void setconfirmPswd(String confirmPswd) {
    this.confirmPswd = confirmPswd;
  }

  public   int  getcheckUser() {
    return checkrecord;
  }

  public void setcheckUser( int  checkrecord) {
    this.checkrecord = checkrecord;
  }
}
