package Interface;

import Model.Vaccine;

import java.util.List;

/*
 *  Name of file: VaccineBlInterface.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having methods of Business Logic to Register User for vaccination
 *  Description: The Interface is responsible for DB operations related User Registration for Vaccination
 * */
public interface VaccineBLInterface {

  List<Vaccine> getVaccineData();
}
