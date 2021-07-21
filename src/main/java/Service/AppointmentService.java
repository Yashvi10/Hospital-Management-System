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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.Date;

public class AppointmentService implements AppointmentDAO {

    @Override
    public Boolean book_appointment() {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        AppointmentService appointmentService = new AppointmentService();
        AppointmentModel appointmentModel;
        if (con != null) {
            appointmentService.getDoctors();
            System.out.println("*************************************");
            System.out.println("Please enter Doctor Id for whom you want to book appointment: \n");
            System.out.println("*************************************");
            Scanner scanner = new Scanner(System.in);
            String doctor_id = scanner.nextLine();
            if (validateDoctorID(doctor_id)) {
                System.out.println("*************************************");
                System.out.println("Please enter Appointment Date(dd-mm-yyyy): \n");
                System.out.println("*************************************");
                Scanner scanner1 = new Scanner(System.in);
                String appointment_date = scanner1.nextLine();
                if (validateDate(appointment_date)) {
                    System.out.println("*************************************");
                    System.out.println("Please enter Appointment Time(hh:mm): \n");
                    System.out.println("*************************************");
                    String appointment_time = scanner1.nextLine();
                    if (validateTime(appointment_time)) {
                        try {
                            appointmentModel = new AppointmentModel(1, Integer.parseInt(doctor_id), appointment_date, appointment_time, "confirmed", 0);
                            String SQL = "INSERT INTO CSCI5308_8_DEVINT.appointment " +
                                    "(user_id, doctor_id, appointment_date, appointment_time, appointment_status, reschedule_counts)" +
                                    "VALUES (?,?,?,?,?,?)";
                            PreparedStatement ps = con.prepareStatement(SQL);
                            ps.setInt(1, appointmentModel.getUser_id());
                            ps.setInt(2,appointmentModel.getDoctor_id());
                            ps.setString(3, appointmentModel.getAppointment_date());
                            ps.setString(4, appointmentModel.getAppointment_time());
                            ps.setString(5, appointmentModel.getAppointment_status());
                            ps.setInt(6, appointmentModel.getReschedule_counts());
                            ps.executeUpdate();
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally  {
                            try  {
                                con.close();
                            }  catch  (SQLException throwables)  {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Boolean cancel_appointment() {
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

                System.out.println("Please enter the appointment_id for which you want to cancel the appointment:");
                Scanner scanner = new Scanner(System.in);
                int appointment_id = scanner.nextInt();
                String SQL1 = "DELETE FROM CSCI5308_8_DEVINT.appointment " +
                        "WHERE appointment_id = " + appointment_id;
                Statement st1 = con.createStatement();
                st1.executeUpdate(SQL1);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally  {
                try  {
                    con.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally  {
                try  {
                    con.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public Boolean reschedule_appointment() throws ParseException {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        int reschedule_counts = 0;
        Map<Integer, ArrayList<String>> appointment_details = new HashMap<>();
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
                        reschedule_counts = rs.getInt("reschedule_counts");
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(String.valueOf(reschedule_counts));
                        arrayList.add(rs.getString("appointment_date"));
                        appointment_details.put(rs.getInt("appointment_id"),arrayList);
                        System.out.println(String.format("%14d", rs.getInt("appointment_id")) + "|" +
                                String.format("%16s", rs.getString("appointment_date")) + "|" +
                                String.format("%16s", rs.getString("appointment_time")));
                    }
                    System.out.println("*************************************\n");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Please enter the appointment_id for which you want to reschedule the appointment:");
            Scanner scanner = new Scanner(System.in);
            int appointment_id = scanner.nextInt();
            int this_reschedule_counts = Integer.parseInt(appointment_details.get(appointment_id).get(0));
            if (this_reschedule_counts >= 2){
                System.out.println("Sorry you cannot reschedule anymore as you have already done it twice!");
                return false;
            }else{
                this_reschedule_counts++;
            }
            if (validateAppointmentIDAndDate(appointment_id, appointment_details)) {
                System.out.println("*************************************");
                System.out.println("Please enter Appointment Date(dd-mm-yyyy): \n");
                System.out.println("*************************************");
                Scanner scanner1 = new Scanner(System.in);
                String new_date = scanner1.nextLine();
                if (validateDate(new_date)) {
                    System.out.println("*************************************");
                    System.out.println("Please enter Appointment Time(hh:mm): \n");
                    System.out.println("*************************************");
                    String new_time = scanner1.nextLine();
                    if (validateTime(new_time)) {
                        try {
                            String SQL1 = "UPDATE CSCI5308_8_DEVINT.appointment " +
                                    "SET appointment_date = " + "'" + new_date + "'" +
                                    ", appointment_time = " + "'" + new_time + "'" +
                                    ", reschedule_counts = " + "'" + this_reschedule_counts + "'" +
                                    " WHERE appointment_id = " + appointment_id + ";";
                            Statement st1 = con.createStatement();
                            st1.executeUpdate(SQL1);
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally  {
                            try  {
                                con.close();
                            }  catch  (SQLException throwables)  {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean validateAppointmentIDAndDate(int appointment_id, Map<Integer, ArrayList<String>> appointment_details) throws ParseException {
        CustomConnection cc = new CustomConnection();
        Connection con = cc.Connect();
        ArrayList<Integer> appointment_ids = new ArrayList<>();

        //check if the current appointment date is greater than 48 hours
        String appointment_date = appointment_details.get(appointment_id).get(1);

        // Getting system timezone
        ZoneId systemTimeZone = ZoneId.systemDefault();

        // converting LocalDateTime to ZonedDateTime with the system timezone
        ZonedDateTime zonedDateTime = LocalDate.now().plusDays(2).atStartOfDay(systemTimeZone);

        // converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
        java.util.Date date_after_2_days = java.util.Date.from(zonedDateTime.toInstant());
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(appointment_date);
        if (!date1.after(date_after_2_days)){
            System.out.println("Sorry but you cannot reschedule your appointment before 2 days!");
        }
        if (con != null) {
            try {
                String SQL = "SELECT * FROM appointment";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs != null) {
                    while (rs.next()) {
                        appointment_ids.add(rs.getInt("appointment_id"));
                    }
                }
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (appointment_ids.contains(appointment_id)){
            return true;
        }else {
            System.out.println("Please enter a valid Appointment Id");
            return false;
        }
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally  {
                try  {
                    con.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally  {
                try  {
                    con.close();
                }  catch  (SQLException throwables)  {
                    throwables.printStackTrace();
                }
            }
        }
        if (doctor_ids.contains(Integer.parseInt(doctor_id))){
            return true;
        }else {
            System.out.println("Please enter a valid Doctor Id");
            return false;
        }
    }

    public Boolean validateDate(String date) {
        LocalDate max_allowed_date = LocalDate.now().plusMonths(3);
        // Getting system timezone
        ZoneId systemTimeZone = ZoneId.systemDefault();

        // converting LocalDateTime to ZonedDateTime with the system timezone
        ZonedDateTime zonedDateTime = LocalDate.now().plusMonths(3).atStartOfDay(systemTimeZone);

        // converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
        java.util.Date utilDate = java.util.Date.from(zonedDateTime.toInstant());
        java.util.Date date1 = new java.util.Date();
        try {
            Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            if (date2.after(utilDate)){
                System.out.println("You can only enter dates within 3 months of period!");
                return false;
            }
            if (date2.after(date1)) {
                return true;
            } else {
                System.out.println("Please select a valid date!");
                return false;
            }
        }catch (ParseException pe){
            System.out.println("Please enter a valid date in the given format!");
            return false;
        }
    }

    public boolean validateTime(String appointment_time) {
        DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalTime.parse(appointment_time,strictTimeFormatter);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Please enter a valid time in the given format!");
            return false;
        }
    }

}
