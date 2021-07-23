package View;

import Interface.FeatureMenu;
import Service.AppointmentService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class contains different menus and it will redirect based on their input
 *  Description: This class basically take input from user and validate them and
 *  call different Services which are created
 * */
public class Appointments implements FeatureMenu {

  @Override
  public void menu() {

    System.out.println("*************************************");
    System.out.println("Press b to Book an appointment\n" +
            "Press c to Cancel an existing appointment\n" +
            "Press r to Reschedule an existing appointment\n" +
            "Press v to View your appointments\nPress h for home menu");
    System.out.println("*************************************");

    Scanner scanner = new Scanner(System.in);
    String inputFromUser = scanner.nextLine();

    if (inputFromUser.equals("b") || inputFromUser.equals("B")){
      try {
        book_appointment();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }else if (inputFromUser.equals("c") || inputFromUser.equals("C")){
      try {
        cancel_appointment();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }else if (inputFromUser.equals("r") || inputFromUser.equals("R")){
      try {
        reschedule_appointment();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }else if (inputFromUser.equals("v") || inputFromUser.equals("V")){
      try {
        view_appointments();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else if (inputFromUser.equals("h") || inputFromUser.equals("H")){
      Dashboard dashboard = new Dashboard();
      try {
        dashboard.HomeMenu();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      invalid();
    }
  }

  public void book_appointment() throws SQLException, ParseException {

    AppointmentService appointmentService = new AppointmentService();
    if (appointmentService.book_appointment()) {
      System.out.println("Appointment booked successfully!");
      menu();
    } else {
      System.out.println("Booking failed!");
      menu();
    }
  }

  public void cancel_appointment() throws SQLException, ParseException {

    AppointmentService appointmentService = new AppointmentService();
    if (appointmentService.cancel_appointment()){
      System.out.println("Appointment cancelled successfully!");
      menu();
    }else {
      System.out.println("Cancellation failed!");
      menu();
    }
  }

  public void reschedule_appointment() throws SQLException, ParseException {
    AppointmentService appointmentService = new AppointmentService();
    if (appointmentService.reschedule_appointment()){
      System.out.println("Appointment rescheduled successfully!");
      menu();
    }else {
      System.out.println("Cancellation failed!");
      menu();
    }
  }

  public void view_appointments() throws SQLException, ParseException {

    AppointmentService appointmentService = new AppointmentService();
    appointmentService.view_appointment();
    menu();
  }

  public void invalid(){
    System.out.println("Invalid input!");
  }

}
