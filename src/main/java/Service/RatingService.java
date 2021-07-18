package Service;

import Interface.RatingDAO;
import Model.AppointmentModel;
import Model.Feedback;
import Model.Pharmacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RatingService implements RatingDAO {
    @Override
    public List<AppointmentModel> userAppointments(String user_Id) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();

        List<AppointmentModel> appointmentList = new ArrayList<AppointmentModel>();

        if(conn != null) {
            String SQL = "SELECT * FROM appointment where user_id = '" +user_Id +"';";
            Statement statement = null;
            try  {
                statement = conn.createStatement();
                ResultSet result = statement.executeQuery(SQL);
                while(result.next()) {
                    Integer appointment_id = result.getInt(1);
                    String appointment_Date = result.getString(3);
                    String appointment_Time = result.getString(4);
                    String status = result.getString(5);

                    AppointmentModel appointmentModel = new AppointmentModel();
                    appointmentModel.setAppointment_id(appointment_id);
                    appointmentModel.setAppointment_date(appointment_Date);
                    appointmentModel.setAppointment_time(appointment_Time);
                    appointmentModel.setAppointment_status(status);
                    appointmentList.add(appointmentModel);
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

        return appointmentList;
    }

    @Override
    public Boolean addFeedback(Feedback feedback) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        if  (conn != null)  {
            try  {
                Statement st = conn.createStatement();
                String SQL = "INSERT INTO feedback " +
                        "(appointment_id,user_id,feedback,rating) VALUES ('"
                        +feedback.getAppointment_id() +"','"
                        +feedback.getUser_id() +"','"
                        +feedback.getFeedback() +"','"
                        +feedback.getRating()
                        +"')";
                st.executeUpdate(SQL);

                conn.close();
                return true;
            }  catch (SQLException throwables)  {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean feedbackExists(Integer user_id, Integer appointment_id) {
        CustomConnection connection = new CustomConnection();
        Connection conn = connection.Connect();
        Boolean result = false;

        if(conn != null) {
            Statement st = null;
            try {
                st = conn.createStatement();
                String SQL = "select count(*) from feedback where user_id = '" +user_id +"' " +
                        "AND appointment_id = '" +appointment_id +"';";
                ResultSet rs = st.executeQuery(SQL);
                rs.next();

                if(rs.getInt("count(*)") > 0) {
                    return true;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return result;
    }
}
