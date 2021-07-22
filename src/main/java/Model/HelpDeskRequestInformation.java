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

  private String priority_type;

  private String userId;

  public HelpDeskRequestInformation(String description, String priority_type, String userId) {
    this.description = description;
    this.priority_type = priority_type;
    this.userId = userId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority_type() {
    return priority_type;
  }

  public void setPriority_type(String priority_type) {
    this.priority_type = priority_type;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
