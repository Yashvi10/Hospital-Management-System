/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class is for services related to appointments
 *  Description: This class will contain logic for all the functionalities included in appointment module
 * */
package Service;

import DAO.AppointmentDAO;
import Model.AppointmentModel;

import java.sql.*;

public class AppointmentService implements AppointmentDAO {
    @Override
    public Boolean book_appointment(AppointmentModel appointment) {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null){
            try  {
                String SQL = "INSERT INTO CSCI5308_8_DEVINT.appointment " +
                        "(user_id, appointment_date, appointment_time, appointment_status)" +
                        "VALUES (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1,appointment.getUser_id());
                ps.setString(2, appointment.getAppointment_date());
                ps.setString(3,appointment.getAppointment_time());
                ps.setString(4, appointment.getAppointment_status());
                ps.executeUpdate();
                con.close();
                return true;
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }

        }
        return false;
    }
}
