package Interface;

import Model.HelpDeskRequestInformation;

/*
 *  Name of file: HelpDeskRequestRegisterBLInterface.java
 *  Author:  Kushang Mistry
 *  Description: The Interface is responsible for operations related to Help Desk request registration.
 *  Purpose: An Interface having methods of Business Logic to Register request.
 * */
public interface HelpDeskRequestRegisterBLInterface {

  Boolean registerRequest(HelpDeskRequestInformation helpDeskRequestInformation);

  Boolean getRequestInformation(String description);
}
