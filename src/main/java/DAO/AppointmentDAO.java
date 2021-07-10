/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is an interface for appointment module
 *  Description: This class shows the behaviour of appointment module and the respective classes
 *  can implement it
 * */
package DAO;

import Model.AppointmentModel;

public interface AppointmentDAO {
    Boolean book_appointment(AppointmentModel appointment);
}
