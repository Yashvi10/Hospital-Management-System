package Service;

import Interface.CovidBadDAO;

/*
 *  Name of file: CovidBedService.java
 *  Author:  Kushang Mistry
 *  Purpose: Returns number of available beds based on user choice and registers bed to database
 *  Description: Fetches information from database and gives count of availability of bed.
 *               As well as registers bed as per user's choice and availability.
 * */
public class CovidBedService implements CovidBadDAO {

  @Override
  public Integer getTotalBeds(Integer bedType) {
    return null;
  }
}
