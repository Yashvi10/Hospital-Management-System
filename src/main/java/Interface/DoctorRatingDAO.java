package Interface;

import Model.DoctorRating;
import Model.Doctors;

import javax.print.Doc;
import java.util.List;

/*
 *  Name of file: DoctorRatingDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Rate doctors
 * */
public interface DoctorRatingDAO {

    List<Doctors> getAllDoctors();

    DoctorRating isDoctorAlreadyInDB(Integer doctor_id);

    Boolean AddFirstRating(DoctorRating doctorRating);

    Boolean updateRating(Integer doctor_id, Double rating, Integer count, Double total);
}
