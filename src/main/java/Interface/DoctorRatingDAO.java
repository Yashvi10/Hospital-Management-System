package Interface;

import Model.DoctorRating;
import Model.Doctors;

import javax.print.Doc;
import java.util.List;

public interface DoctorRatingDAO {

    List<Doctors> getAllDoctors();

    DoctorRating isDoctorAlreadyInDB(Integer doctor_id);

    Boolean AddFirstRating(DoctorRating doctorRating);

    Boolean updateRating(Integer doctor_id, Double rating, Integer count, Double total);
}
