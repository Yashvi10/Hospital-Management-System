package Service;

import Interface.DoctorRatingDAO;
import Model.DoctorRating;
import Model.Doctors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: DoctorRatingService.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like service which will implement the DoctorRatingDAO
 *  Description: This class will implement all the actual logic define in DoctorRatingDAO
 * */
public class DoctorRatingService implements DoctorRatingDAO {
    @Override
    public List<Doctors> getAllDoctors() {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        List<Doctors> doctorsList = new ArrayList<Doctors>();

        if  (conn != null)  {
            String sql = "SELECT * FROM doctors d left join doctorRating dr on d.doctor_id = dr.doctor_id;";
            Statement statement = null;
            try  {
                statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    Integer d_id = result.getInt(1);
                    String name = result.getString(2);
                    String specialization = result.getString(3);
                    Double rating = result.getDouble(6);

                    Doctors doctors = new Doctors(d_id, name, specialization);
                    doctors.setRating(rating);
                    doctorsList.add(doctors);
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }  finally  {
                try  {
                    conn.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
            }
        }
        return doctorsList;
    }

    @Override
    public DoctorRating isDoctorAlreadyInDB(Integer doctor_id) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();

        if  (conn != null)  {
            String sql = "Select * from doctorRating where doctor_id = " +doctor_id +";";
            Statement statement = null;
            try  {
                statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    Integer d_id = result.getInt(2);
                    Double rating = result.getDouble(3);
                    Integer count = result.getInt(4);
                    Double total = result.getDouble(5);

                    DoctorRating doctorRating = new DoctorRating(d_id,rating, count, total);
                    return doctorRating;
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }  finally  {
                try  {
                    conn.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Boolean AddFirstRating(DoctorRating doctorRating) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        if  (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO doctorRating " +
                        "(doctor_id,rating,count,total) VALUES ('"
                        +doctorRating.getDoctor_id() +"','"
                        +doctorRating.getRating() +"','"
                        +doctorRating.getCount() +"','"
                        +doctorRating.getTotal()
                        +"')";
                st.executeUpdate(SQL);

                conn.close();
                return true;
            }  catch (SQLException throwables)  {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateRating(Integer doctor_id, Double rating, Integer count, Double total) {
        CustomConnection customConnection = new CustomConnection();
        Connection conn = customConnection.Connect();

        Boolean result = false;

        if(conn != null) {
            String SQL = "update doctorRating SET rating=" +rating +", count= "+count +",total=" +total +
                    "where doctor_id = " + doctor_id + ";";
            Statement statement = null;
            try {
                statement = conn.createStatement();
                statement.executeUpdate(SQL);
                conn.close();
                result = true;
                return result;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return result;
    }
}
