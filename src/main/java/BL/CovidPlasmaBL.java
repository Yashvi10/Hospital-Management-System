package BL;

import Interface.CovidBedDAO;
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

  private CovidPlasmaDAO covidPlasmaDAO;

  public CovidPlasmaBL(CovidPlasmaDAO covidPlasmaDAO) {
    this.covidPlasmaDAO = covidPlasmaDAO;
  }

  @Override
  public List<CovidPlasmaInformation> getPlasmaList() {
    List<CovidPlasmaInformation> covidPlasmaInformation = covidPlasmaDAO.showPlasmaAvailability();
    if(covidPlasmaInformation != null)
      return covidPlasmaInformation;
    else
      return null;
  }
}
