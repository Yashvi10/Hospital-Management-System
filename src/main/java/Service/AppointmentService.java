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
import java.util.Scanner;

public class AppointmentService implements AppointmentDAO {
    @Override
    public Boolean book_appointment(AppointmentModel appointmentModel) {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null){
            try  {
                String SQL = "INSERT INTO CSCI5308_8_DEVINT.appointment " +
                        "(user_id, appointment_date, appointment_time, appointment_status)" +
                        "VALUES (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1,appointmentModel.getUser_id());
                ps.setString(2, appointmentModel.getAppointment_date());
                ps.setString(3,appointmentModel.getAppointment_time());
                ps.setString(4, appointmentModel.getAppointment_status());
                ps.executeUpdate();
                con.close();
                return true;
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public Boolean cancel_appointment() throws SQLException {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null){
            try  {
                String SQL = "SELECT * FROM appointment where user_id = 123 and appointment_status = 'confirmed'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if  (rs != null)  {
                    System.out.println("*************************************");
                    System.out.println(String.format("%10s","appointment_id") + "|" +
                            String.format("%10s","appointment_date") + "|" +
                            String.format("%10s","appointment_time") );
                    while (rs.next()) {
                        System.out.println( String.format("%14d",rs.getInt("appointment_id")) + "|" +
                                String.format("%16s",rs.getString("appointment_date")) + "|" +
                                String.format("%16s",rs.getString("appointment_time")) );
                    }
                    System.out.println("*************************************\n");
                }

                System.out.println("Please enter the appointment_id for which you want to cancel the appointment:");
                Scanner scanner = new Scanner(System.in);
                int appointment_id = scanner.nextInt();
                String SQL1 = "DELETE FROM CSCI5308_8_DEVINT.appointment " +
                        "WHERE appointment_id = " + appointment_id ;
                Statement st1 = con.createStatement();
                st1.executeUpdate(SQL1);
                con.close();
                return true;
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            }

        }
        return false;
    }
}
