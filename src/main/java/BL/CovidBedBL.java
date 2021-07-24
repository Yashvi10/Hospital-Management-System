package BL;

import Interface.CovidBedDAO;
import Interface.CovidBedBLInterface;

/*
 *  Name of file: CovidBedBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements Covid related business logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class CovidBedBL implements CovidBedBLInterface {

  private CovidBedDAO covidBedDAO;

  public CovidBedBL(CovidBedDAO covidBedDAO) {
    this.covidBedDAO = covidBedDAO;
  }

  /*
   * This method retrieves information from DB layer methods about availability of beds
   */
  @Override
  public Integer getBedCount(Integer userChoice) {

    if(userChoice == null && userChoice == 0)
      return null;
    else
      return covidBedDAO.getTotalBeds(userChoice);
  }

  /*
   * This method retrieves information from DB layer methods about availability of beds
   */
  @Override
  public Integer registerBed(Integer userChoice) {

    if(userChoice == null && userChoice == 0)
      return null;
    else
      return covidBedDAO.registerBed(userChoice);
  }
}
