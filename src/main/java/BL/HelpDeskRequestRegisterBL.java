package BL;

import Interface.HelpDeskRegisterRequest;
import Interface.HelpDeskRequestRegisterBLInterface;
import Model.HelpDeskRequestInformation;
import Service.HelpDeskRegisterService;
import Service.UserSession;

import java.util.Scanner;

/*
 *  Name of file: HelpDeskRequestRegisterBL.java
 *  Author:  Kushang Mistry
 *  Purpose: This class implements business logic and methods described in
 *            the interface named HelpDeskRequestRegisterBLInterface
 *  Description: The class is under Business Logic layer and
 *               provides registration to the presentation layer class.
 * */
public class HelpDeskRequestRegisterBL implements HelpDeskRequestRegisterBLInterface {

  HelpDeskRegisterRequest helpDeskRegisterRequest;

  public HelpDeskRequestRegisterBL(HelpDeskRegisterRequest helpDeskRegisterRequest) {
    this.helpDeskRegisterRequest = helpDeskRegisterRequest;
  }


  @Override
  public Boolean registerRequest(HelpDeskRequestInformation helpDeskRequestInformation) {
    if(helpDeskRegisterRequest.registerHelpDeskRequest(helpDeskRequestInformation))
      return true;
    else
      return false;
  }

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
    }
    else
      return false;
  }
}
