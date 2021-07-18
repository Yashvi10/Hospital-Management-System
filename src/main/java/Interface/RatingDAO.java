package Interface;

import Model.AppointmentModel;

import java.util.List;

/*
 *  Name of file: RatingDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Rating model
 * */
public interface RatingDAO {

    List<AppointmentModel> userAppointments(String user_Id);
}
