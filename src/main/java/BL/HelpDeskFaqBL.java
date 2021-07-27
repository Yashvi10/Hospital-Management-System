package BL;

import Interface.HelpDeskFaqBLInterface;
import Interface.HelpDeskFaqDAO;
import Model.HelpDeskFaq;

import java.util.List;

/*
 *  Name of file: HelpDeskFaqBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements HelpDesk FAQ related Business Logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class HelpDeskFaqBL implements HelpDeskFaqBLInterface {

  // Instance of Interface - Data access object to retrieve data and call methods
  private HelpDeskFaqDAO helpDeskFaqDAO;

  // A constructor of the class
  public HelpDeskFaqBL(HelpDeskFaqDAO helpDeskFaqDAO) {
    this.helpDeskFaqDAO = helpDeskFaqDAO;
  }

  /*
   * An implementation of the declared method in the interface
   * Returns the list of the model HelpDeskFaq through it's service class
   */
  @Override
  public List<HelpDeskFaq> getFaqData() {

    List<HelpDeskFaq> helpDeskFaqList = helpDeskFaqDAO.getHelpDeskFaq();   // Fetches and stores all FAQ information

    return helpDeskFaqList;
  }
}
