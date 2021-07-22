package BL;

import Interface.HelpDeskFaqBLInterface;
import Interface.HelpDeskFaqDAO;
import Model.HelpDeskFaq;
import Model.Vaccine;

import java.util.List;

/*
 *  Name of file: HelpDeskFaqBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements HelpDesk FAQ related Business Logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class HelpDeskFaqBL implements HelpDeskFaqBLInterface {

  private HelpDeskFaqDAO helpDeskFaqDAO;

  public HelpDeskFaqBL(HelpDeskFaqDAO helpDeskFaqDAO) {
    this.helpDeskFaqDAO = helpDeskFaqDAO;
  }

  public List<HelpDeskFaq> getFaqData() {

    List<HelpDeskFaq> helpDeskFaqList = helpDeskFaqDAO.getHelpDeskFaq();   // Fetches and stores all FAQ information

    return helpDeskFaqList;
  }
}
