/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class contains different menus and it will redirect based on their input
 *  Description: This class basically take input from user and validate them and
 *  call different Services which are created
 * */

import Model.AppointmentModel;
import Service.AppointmentService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Appointments {

    public void Menu() throws SQLException {

        System.out.println("*************************************");
        System.out.println("Press b to Book an appointment\n" +
                "Press c to Cancel an existing appointment\n" +
                "Press r to Reschedule an existing appointment\n" +
                "Press v to View your appointments");
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if (inputFromUser.equals("b") || inputFromUser.equals("B")){
            book_appointment();
        }else if (inputFromUser.equals("c") || inputFromUser.equals("C")){
            cancel_appointment();
        }else if (inputFromUser.equals("r") || inputFromUser.equals("R")){
            reschedule_appointment();
        }else if (inputFromUser.equals("v") || inputFromUser.equals("V")){
            view_appointments();
        }else {
            invalid();
        }
    }

    public void book_appointment() {

        AppointmentService appointmentService = new AppointmentService();
        appointmentService.getDoctors();
        System.out.println("*************************************");
        System.out.println("Please enter Doctor Id for whom you want to book appointment: \n");
        System.out.println("*************************************");
        Scanner scanner = new Scanner(System.in);
        String doctor_id = scanner.nextLine();
        if (appointmentService.validateDoctorID(doctor_id)) {
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
                    AppointmentModel appointment = new AppointmentModel(1, Integer.parseInt(doctor_id), appointment_date, appointment_time, "confirmed");
                    if (appointmentService.book_appointment(appointment)) {
                        System.out.println("Appointment booked successfully!");
                    } else {
                        System.out.println("Booking failed!");
                    }
                }
            }
        }else {
            System.out.println("Please enter a valid doctor id!");
        }
    }

    public void cancel_appointment() throws SQLException {

        AppointmentService appointmentService = new AppointmentService();
        if (appointmentService.cancel_appointment()){
            System.out.println("Appointment cancelled successfully!");
        }else {
            System.out.println("Cancellation failed!");
        }
    }

    public void reschedule_appointment(){
        AppointmentService appointmentService = new AppointmentService();
        if (appointmentService.reschedule_appointment()){
            System.out.println("Appointment rescheduled successfully!");
        }else {
            System.out.println("Cancellation failed!");
        }
    }

    public void view_appointments(){

        AppointmentService appointmentService = new AppointmentService();
        appointmentService.view_appointment();
    }

    public void invalid(){
        System.out.println("Invalid input!");
    }

    public Boolean validateDate(String date) {
        LocalDate max_allowed_date = LocalDate.now().plusMonths(3);
        // Getting system timezone
        ZoneId systemTimeZone = ZoneId.systemDefault();

        // converting LocalDateTime to ZonedDateTime with the system timezone
        ZonedDateTime zonedDateTime = LocalDate.now().plusMonths(3).atStartOfDay(systemTimeZone);

        // converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
        Date utilDate = Date.from(zonedDateTime.toInstant());
        Date date1 = new Date();
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
