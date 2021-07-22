package Interface;

import Model.Vaccine;

import java.util.List;

/*
 *  Name of file: HelpDeskFaqBLInterface.java
 *  Author:  Kushang Mistry
 *  Description: The Interface is responsible for Business Logic Operations methods
 *  Purpose: An Interface having methods of Business Logic to get FAQ data
 * */
public interface HelpDeskFaqBLInterface {

  List<Vaccine> getFaqData();
}
