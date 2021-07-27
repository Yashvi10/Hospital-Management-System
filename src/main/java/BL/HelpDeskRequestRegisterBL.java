package BL;

import Interface.HelpDeskRegisterRequest;
import Interface.HelpDeskRequestRegisterBLInterface;
import Model.HelpDeskRequestInformation;
import Service.UserSession;

/*
 *  Name of file: HelpDeskRequestRegisterBL.java
 *  Author:  Kushang Mistry
 *  Purpose: This class implements business logic and methods described in
 *            the interface named HelpDeskRequestRegisterBLInterface
 *  Description: The class is under Business Logic layer and
 *               provides registration to the presentation layer class.
 * */
public class HelpDeskRequestRegisterBL implements HelpDeskRequestRegisterBLInterface {

  // Instance of Interface - Data access object to set the data to database
  HelpDeskRegisterRequest helpDeskRegisterRequest;

  // A constructor of the class
  public HelpDeskRequestRegisterBL(HelpDeskRegisterRequest helpDeskRegisterRequest) {
    this.helpDeskRegisterRequest = helpDeskRegisterRequest;
  }

  /*
   * This method serves as a bridge method for other class and calls the method of the class
   * using an interface named HelpDeskRegisterRequest
   * Returns the true if request registered successfully and a helper is assigned to the request
   * Else if something went wrong then it will return false
   */
  @Override
  public Boolean registerRequest(HelpDeskRequestInformation helpDeskRequestInformation) {
    if(helpDeskRegisterRequest.registerHelpDeskRequest(helpDeskRequestInformation))
      return true;
    else
      return false;
  }

  /*
   * This method accepts data sent by the presentation layer class
   * Calls registerRequest method of this class to register a request
   * Returns the true if user request get registered
   * Else if something went wrong then it will return false
   */
  @Override
  public Boolean getRequestInformation(String description) {

    HelpDeskRequestInformation helpDeskRequestInformation = new HelpDeskRequestInformation();

    if(description != null) {
      helpDeskRequestInformation.setDescription(description);
      helpDeskRequestInformation.setStatus(1);
      helpDeskRequestInformation.setUserId(UserSession.userId);

      if(registerRequest(helpDeskRequestInformation))
        return true;
      else
        return false;
    } else
      return false;
  }
}
