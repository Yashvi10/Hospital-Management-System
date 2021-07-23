package Interface;

import Model.HelpDeskFaq;

import java.util.List;

/*
 *  Name of file: HelpDeskFaqDAO.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to get data from database
 *  Description: The Interface is responsible for DB operations related to HelpDesk FAQ
 * */
public interface HelpDeskFaqDAO {

  List<HelpDeskFaq> getHelpDeskFaq();
}
