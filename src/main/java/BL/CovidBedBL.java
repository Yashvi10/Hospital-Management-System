package BL;

import Interface.CovidBadDAO;
import Interface.CovidBedBLInterface;

/*
 *  Name of file: CovidBedBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements Covid related business logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class CovidBedBL implements CovidBedBLInterface {

  private CovidBadDAO covidBadDAO;

  public CovidBedBL(CovidBadDAO covidBadDAO) {
    this.covidBadDAO = covidBadDAO;
  }

  /*
   * This method retrieves information from DB layer methods about availability of beds
   */
  @Override
  public Integer getBedCount(Integer userChoice) {

    if(userChoice == null && userChoice == 0)
      return null;
    else
      return covidBadDAO.getTotalBeds(userChoice);
  }
}
