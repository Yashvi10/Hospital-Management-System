package Interface;

import java.sql.SQLException;
import java.text.ParseException;

/*
* Name: AppointmentDAO.java
* Author: Sanket
* */
public interface AppointmentDAO {

  Boolean book_appointment();

  Boolean cancel_appointment() throws SQLException;

  void view_appointment();

  Boolean reschedule_appointment() throws ParseException, SQLException;
}
