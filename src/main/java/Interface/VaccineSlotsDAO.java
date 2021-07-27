package Interface;

import java.sql.Date;

public interface VaccineSlotsDAO {

  Boolean updateSlotAvailability(Date date);

  Integer getAvailableSlots(Date date);
}
