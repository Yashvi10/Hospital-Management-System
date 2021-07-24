package Interface;

import Model.CovidPlasmaInformation;

import java.util.List;

/*
 *  Name of file: CovidPlasmaDAO.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to get plasma related operations and set request
 *  Description: The Interface is responsible for DB operations related to Covid Section - Plasma
 * */
public interface CovidPlasmaDAO {

  List<CovidPlasmaInformation> showPlasmaAvailability();
}
