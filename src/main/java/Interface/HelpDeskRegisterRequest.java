package Interface;

import Model.HelpDeskRequestInformation;

/*
 *  Name of file: HelpDeskRegisterRequest.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to Register Help Desk Requests
 *  Description: The Interface is responsible for DB operations related User Registration for Help Desk
 * */
public interface HelpDeskRegisterRequest {

  Boolean registerHelpDeskRequest(HelpDeskRequestInformation helpDeskRequestInformation);

  Integer getActiveRequestsOfStaffId(Integer staffId);

  Integer getRandomStaffId();
}
