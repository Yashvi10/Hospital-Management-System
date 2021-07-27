package Interface;

import Model.HelpDeskFaq;

import java.util.List;

/*
 *  Name of file: HelpDeskFaqBLInterface.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having methods of Business Logic to get FAQ data
 *  Description: The Interface is responsible for Business Logic Operations methods
 * */
public interface HelpDeskFaqBLInterface {

  List<HelpDeskFaq> getFaqData();
}
