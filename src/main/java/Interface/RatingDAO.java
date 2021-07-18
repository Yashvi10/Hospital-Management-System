package Interface;

import Model.AppointmentModel;

import java.util.List;

public interface RatingDAO {

    List<AppointmentModel> userAppointments(String user_Id);
}
