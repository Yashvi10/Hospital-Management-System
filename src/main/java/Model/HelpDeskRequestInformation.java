package Model;

/*
 *  Name of file: HelpDeskRequestInformation.java
 *  Author:  Kushang Mistry
 *  Purpose: Getter and Setter methods for Help Desk request registration
 *  Description: This class responsible for storing and providing information
 *               stored while user input.
 * */
public class HelpDeskRequestInformation {

  private String description;

  private Integer status;

  private Integer userId;

  public HelpDeskRequestInformation () {}

  public HelpDeskRequestInformation(String description, Integer status, Integer userId) {
    this.description = description;
    this.status = status;
    this.userId = userId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
