package Interface;

/*
 *  Name of file: VaccineDAO.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to get data from database
 *  Description: The Interface is responsible for DB operations related to Vaccine
 * */
public interface CovidBadDAO {

  Integer getTotalBeds(Integer bedType);
}
