package Interface;

import java.sql.SQLException;
import java.text.ParseException;

/*
 *  Name of file: AppointmentsDAO.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is an interface for appointment module
 *  Description: This class shows the behaviour of appointment module and the respective classes
 *  can implement it
 * */

public interface AppointmentDAO {

  Boolean book_appointment();

  Boolean cancel_appointment() throws SQLException;

  void view_appointment();

  Boolean reschedule_appointment() throws ParseException, SQLException;
}
