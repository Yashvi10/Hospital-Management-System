package BL;

import Interface.CovidPlasmaBLInterface;
import Interface.CovidPlasmaDAO;
import Model.CovidPlasmaInformation;

import java.util.List;

/*
 *  Name of file: CovidPlasmaBL.java
 *  Author:  Kushang Mistry
 *  Purpose: The class which implements Covid Plasma related business logic
 *  Description: The class is under Business Logic layer and
 *               provides all result to the presentation layer class.
 * */
public class CovidPlasmaBL implements CovidPlasmaBLInterface {

  // Instance of Interface - Data access object to retrieve data and call methods
  private CovidPlasmaDAO covidPlasmaDAO;

  // A constructor of the class
  public CovidPlasmaBL(CovidPlasmaDAO covidPlasmaDAO) {
    this.covidPlasmaDAO = covidPlasmaDAO;
  }

  /*
   * This method retrieves information from DB layer methods about - plasma
   * Returns the list of the model CovidPlasmaInformation through the service class
   * Else if something went wrong then it will return null
   */
  @Override
  public List<CovidPlasmaInformation> getPlasmaList() {
    List<CovidPlasmaInformation> covidPlasmaInformation = covidPlasmaDAO.showPlasmaAvailability();
    if(covidPlasmaInformation != null)
      return covidPlasmaInformation;
    else
      return null;
  }
}
