package Interface;

import Model.CovidPlasmaInformation;

import java.util.List;

/*
 *  Name of file: CovidPlasmaBLInterface.java
 *  Author:  Kushang Mistry
 *  Description: The Interface is responsible for declaration of methods which should be implemented in Business logic
 *  Purpose: An Interface having methods of Business Logic to view Plasma info, and register request for that.
 * */
public interface CovidPlasmaBLInterface {

  List<CovidPlasmaInformation>  getPlasmaList();
}
