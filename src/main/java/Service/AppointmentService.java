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
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentService implements AppointmentDAO {

    @Override
    public Boolean book_appointment(AppointmentModel appointmentModel) {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null) {
            try {
                String SQL = "INSERT INTO CSCI5308_8_DEVINT.appointment " +
                        "(user_id, doctor_id, appointment_date, appointment_time, appointment_status)" +
                        "VALUES (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1, appointmentModel.getUser_id());
                ps.setInt(2,appointmentModel.getDoctor_id());
                ps.setString(3, appointmentModel.getAppointment_date());
                ps.setString(4, appointmentModel.getAppointment_time());
                ps.setString(5, appointmentModel.getAppointment_status());
                ps.executeUpdate();
                con.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean cancel_appointment() throws SQLException {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM appointment where user_id = 123 and appointment_status = 'confirmed'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    System.out.println("*************************************");
                    System.out.println(String.format("%10s", "appointment_id") + "|" +
                            String.format("%10s", "appointment_date") + "|" +
                            String.format("%10s", "appointment_time"));
                    while (rs.next()) {
                        System.out.println(String.format("%14d", rs.getInt("appointment_id")) + "|" +
                                String.format("%16s", rs.getString("appointment_date")) + "|" +
                                String.format("%16s", rs.getString("appointment_time")));
                    }
                    System.out.println("*************************************\n");
                }

                System.out.println("Please enter the appointment_id for which you want to cancel the appointment:");
                Scanner scanner = new Scanner(System.in);
                int appointment_id = scanner.nextInt();
                String SQL1 = "DELETE FROM CSCI5308_8_DEVINT.appointment " +
                        "WHERE appointment_id = " + appointment_id;
                Statement st1 = con.createStatement();
                st1.executeUpdate(SQL1);
                con.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public void view_appointment() {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM appointment where user_id = 123 and appointment_status = 'confirmed'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    System.out.println("*************************************");
                    System.out.println(String.format("%10s", "appointment_id") + "|" +
                            String.format("%10s", "appointment_date") + "|" +
                            String.format("%10s", "appointment_time"));
                    while (rs.next()) {
                        System.out.println(String.format("%14d", rs.getInt("appointment_id")) + "|" +
                                String.format("%16s", rs.getString("appointment_date")) + "|" +
                                String.format("%16s", rs.getString("appointment_time")));
                    }
                    System.out.println("*************************************\n");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Boolean reschedule_appointment() {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM appointment where user_id = 1 and appointment_status = 'confirmed'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    System.out.println("*************************************");
                    System.out.println(String.format("%10s", "appointment_id") + "|" +
                            String.format("%10s", "appointment_date") + "|" +
                            String.format("%10s", "appointment_time"));
                    while (rs.next()) {
                        System.out.println(String.format("%14d", rs.getInt("appointment_id")) + "|" +
                                String.format("%16s", rs.getString("appointment_date")) + "|" +
                                String.format("%16s", rs.getString("appointment_time")));
                    }
                    System.out.println("*************************************\n");
                }


                System.out.println("Please enter the appointment_id for which you want to rescedule the appointment:");
                Scanner scanner = new Scanner(System.in);
                int appointment_id = scanner.nextInt();
                System.out.println("*************************************");
                System.out.println("Please enter new Appointment Date(dd-mm-yyyy): \n");
                System.out.println("*************************************");
                Scanner scanner1 = new Scanner(System.in);
                String new_date = scanner1.nextLine();
                System.out.println("*************************************");
                System.out.println("Please enter new Appointment Time(hh:mm:ss): \n");
                System.out.println("*************************************");
                Scanner scanner2 = new Scanner(System.in);
                String new_time = scanner2.nextLine();

                String SQL1 = "UPDATE CSCI5308_8_DEVINT.appointment " +
                        "SET appointment_date = " + "'" + new_date + "'" +
                        ", appointment_time = " + "'" + new_time + "'" +
                        " WHERE appointment_id = " + appointment_id + ";";
                Statement st1 = con.createStatement();
                st1.executeUpdate(SQL1);
                con.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    public void getDoctors() {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM doctors";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    System.out.println("*************************************");
                    System.out.println(String.format("%10s", "doctor_id") + "|" +
                            String.format("%10s", "name") + "|" +
                            String.format("%10s", "specialization"));
                    while (rs.next()) {
                        System.out.println(String.format("%14d", rs.getInt("doctor_id")) + "|" +
                                String.format("%16s", rs.getString("name")) + "|" +
                                String.format("%16s", rs.getString("specialization")));
                    }
                    System.out.println("*************************************\n");
                }
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean validateDoctorID(String doctor_id) {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        ArrayList<Integer> doctor_ids = new ArrayList<>();
        if (con != null) {
            try {
                String SQL = "SELECT * FROM doctors";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    while (rs.next()) {
                        doctor_ids.add(rs.getInt("doctor_id"));
                    }
                }
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return doctor_ids.contains(Integer.parseInt(doctor_id));
    }

}
