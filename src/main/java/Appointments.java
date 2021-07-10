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
import java.time.LocalTime;
import java.sql.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.Timer;

public class Appointments {

    public void Menu() throws ParseException, SQLException {

        System.out.println("*************************************");
        System.out.println("Press b to Book an appointment\s" +
                "Press c to Cancel an existing appointment\s" +
                "Press r to Reschedule an existing appointment\s" +
                "Press v to View your appointments");
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        if (inputFromUser.equals("b") || inputFromUser.equals("B")){
            book_appointment();
        }else if (inputFromUser.equals("c") || inputFromUser.equals("C")){
            book_appointment();
        }else if (inputFromUser.equals("r") || inputFromUser.equals("R")){
            book_appointment();
        }else if (inputFromUser.equals("v") || inputFromUser.equals("V")){
            book_appointment();
        }else {
            invalid();
        }
    }

    public void book_appointment() throws ParseException {

        System.out.println("*************************************");
        System.out.println("Please enter Appointment Date(dd-mm-yyyy): \n");
        System.out.println("*************************************");
        Scanner scanner = new Scanner(System.in);
        String appointment_date = scanner.nextLine();
        System.out.println("*************************************");
        System.out.println("Please enter Appointment Time(hh:mm:ss): \n");
        System.out.println("*************************************");
        Scanner scanner1 = new Scanner(System.in);
        String appointment_time = scanner1.nextLine();
        AppointmentService appointmentService = new AppointmentService();
        AppointmentModel appointment = new AppointmentModel(123,appointment_date,appointment_time,"confirmed");
        if (appointmentService.book_appointment(appointment)){
            System.out.println("Appointment booked successfully!");
        }else {
            System.out.println("Booking failed!");
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

        System.out.println("Reschedule method called!");

    }

    public void view_appointments(){

        System.out.println("View method called!");

    }

    public void invalid(){
        System.out.println("Invalid input!");
    }

}
