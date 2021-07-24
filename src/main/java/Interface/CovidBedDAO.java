package Interface;

/*
 *  Name of file: CovidBadDAO.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having method to get bed count and set data to database
 *  Description: The Interface is responsible for DB operations related to Covid Section
 * */
public interface CovidBedDAO {

  Integer getTotalBeds(Integer bedType);

  Integer registerBed(Integer bedType);
}
