/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class contains different menus and it will redirect based on their input
 *  Description: This class basically take input from user and validate them and
 *  call different Services which are created
 * */

import Model.AppointmentModel;
import Service.AppointmentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.sql.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.Timer;

public class Appointments {

    public void Menu() throws ParseException {

        System.out.println("*************************************");
        System.out.println("""
                Press b to Book an appointment
                Press c to Cancel an existing appointment
                Press r to Reschedule an existing appointment
                Press v to View your appointments """);
        System.out.println("*************************************");

        Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();

        switch (inputFromUser) {
            case "b", "B":
                book_appointment();
                break;
            case "c", "C":
                cancel_appointment();
                break;
            case "r", "R":
                reschedule_appointment();
                break;
            case "v", "V":
                view_appointments();
                break;
            default:
                invalid();
                break;
        }
    }

    public void book_appointment() throws ParseException {

        System.out.println("*************************************");
        System.out.println("Please enter Appointment Date(dd-mm-yyyy): \n");
        System.out.println("*************************************");
        Scanner scanner = new Scanner(System.in);
        String appointment_date = scanner.nextLine();
//        long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
//        Date appointment_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        java.sql.Date appointment_date = new java.sql.Date(date1);
        System.out.println("*************************************");
        System.out.println("Please enter Appointment Time(hh:mm:ss): \n");
        System.out.println("*************************************");
        Scanner scanner1 = new Scanner(System.in);
        String appointment_time = scanner1.nextLine();
//        LocalTime appointment_time = LocalTime.parse(time);
        AppointmentService appointmentService = new AppointmentService();
        AppointmentModel appointment = new AppointmentModel(123,appointment_date,appointment_time,"confirmed");
        if (appointmentService.book_appointment(appointment)){
            System.out.println("Appointment booked successfully!");
        }else {
            System.out.println("Booking failed!");
        }

    }

    public void cancel_appointment(){

        System.out.println("Cancel method called!");

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
