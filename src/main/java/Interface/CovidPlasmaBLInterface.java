package Interface;

import Model.CovidPlasmaInformation;

import java.util.List;

/*
 *  Name of file: CovidPlasmaBLInterface.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having methods of Business Logic to view Plasma info, and register request for that.
 *  Description: The Interface is responsible for declaration of methods which should be implemented in Business logic
 * */
public interface CovidPlasmaBLInterface {

  List<CovidPlasmaInformation>  getPlasmaList();
}
