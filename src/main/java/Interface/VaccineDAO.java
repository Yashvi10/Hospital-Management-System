package Interface;

import Model.Vaccine;

import java.util.List;

/*
 *  Name of file: VaccineDAO.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to get data from database
 *  Description: The Interface is responsible for DB operations related to Vaccine
 * */
public interface VaccineDAO {

  List<Vaccine> getVaccines();
}
