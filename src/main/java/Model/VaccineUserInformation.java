package Model;

import java.sql.Date;

/*
 *  Name of file: VaccineUserInformation.java
 *  Author:  Kushang Mistry
 *  Purpose: Getter and Setter methods for Vaccine User Registration
 *  Description: This class responsible for storing and providing information
 *               stored while user input.
 * */
public class VaccineUserInformation {

  private Integer userId;

  private String mailId;

  private Integer age;

  private String gender;

  private String governmentId;

  private Date preferredDate;

  private String preferredTiming;

  public VaccineUserInformation () {}

  public VaccineUserInformation(Integer userId, String mailId, Integer age, String gender,
                                String governmentId, Date preferredDate, String preferredTiming) {
    this.userId = userId;
    this.mailId = mailId;
    this.age = age;
    this.gender = gender;
    this.governmentId = governmentId;
    this.preferredDate = preferredDate;
    this.preferredTiming = preferredTiming;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getMailId() {
    return mailId;
  }

  public void setMailId(String mailId) {
    this.mailId = mailId;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getGovernmentId() {
    return governmentId;
  }

  public void setGovernmentId(String governmentId) {
    this.governmentId = governmentId;
  }

  public Date getPreferredDate() {
    return preferredDate;
  }

  public void setPreferredDate(Date preferredDate) {
    this.preferredDate = preferredDate;
  }

  public String getPreferredTiming() {
    return preferredTiming;
  }

  public void setPreferredTiming(String preferredTiming) {
    this.preferredTiming = preferredTiming;
  }
}
