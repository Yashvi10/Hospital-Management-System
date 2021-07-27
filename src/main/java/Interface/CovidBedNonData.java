package Interface;

import java.sql.Date;

public interface CovidBedNonData {

  String validateBedtype(Integer bedType);

  Date getTodaysDate();
}
