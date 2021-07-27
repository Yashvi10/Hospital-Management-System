package BL;

import Interface.CovidBedDAO;
import Interface.CovidBedBLInterface;

/*
 *  Name of file: CovidBedBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements Covid bed related business logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class CovidBedBL implements CovidBedBLInterface {

  // Instance of Interface - Data access object to retrieve data about covid bed and call methods
  private CovidBedDAO covidBedDAO;

  // A constructor of the class
  public CovidBedBL(CovidBedDAO covidBedDAO) {
    this.covidBedDAO = covidBedDAO;
  }

  /*
   * This method retrieves information from DB layer methods about availability of beds
   * Returns the availability of beds in numbers (Integer) if all right
   * Else if something went wrong then it will return null
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
   * Returns the bed number user registered to
   * Else returns null in other case
   */
  @Override
  public Integer registerBed(Integer userChoice) {

    if(userChoice == null && userChoice == 0)
      return null;
    else
      return covidBedDAO.registerBed(userChoice);
  }
}
