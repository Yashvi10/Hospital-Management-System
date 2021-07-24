import Service.AppointmentService;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentsTest {

  @Test
    //test to validate and invalid input of date for booking appointment
  void validateDateWIthInvalidDate() {
    AppointmentService appointmentService = new AppointmentService();
    assertEquals(false,appointmentService.validateDate("12-12-12"),"date is not validated");
  }

  @Test
    //test to validate that booking date is any date which is after current date
  void validateDateWIthDateAfterCurrentDate() {
    AppointmentService appointmentService = new AppointmentService();
    Date date = new Date();
    long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    long nextDayMilliSeconds = date.getTime() + ONE_DAY_MILLI_SECONDS;
    Date nextDate = new Date(nextDayMilliSeconds);
    String nextDateStr = new SimpleDateFormat("dd-MM-yyyy").format(nextDate);
    assertEquals(true,appointmentService.validateDate(nextDateStr),"date is not validated");
  }

  @Test
    //test to validate if the date is in past
  void validateDateWIthDateBeforeCurrentDate() {
    AppointmentService appointmentService = new AppointmentService();
    Date date = new Date();
    long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    long previousDayMilliSeconds = date.getTime() - ONE_DAY_MILLI_SECONDS;
    Date previousDate = new Date(previousDayMilliSeconds);
    String previousDateStr = new SimpleDateFormat("dd-MM-yyyy").format(previousDate);
    assertEquals(false, appointmentService.validateDate(previousDateStr),"date is not validated");
  }

  @Test
    //test to validate if the date is more than months from now
  void validateDateWIthDateAfter3Months() {
    AppointmentService appointmentService = new AppointmentService();
    Date date = new Date();
    long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    //passing date after 93 days(2 months having 31 days)
    long nextDayMilliSeconds = date.getTime() + (93 * ONE_DAY_MILLI_SECONDS);
    Date nextDate = new Date(nextDayMilliSeconds);
    String nextDateStr = new SimpleDateFormat("dd-MM-yyyy").format(nextDate);
    assertEquals(false,appointmentService.validateDate(nextDateStr),"date is not validated");
  }

  @Test
    //test to validate if the time is in correct format
  void validateTime() {
    AppointmentService appointmentService = new AppointmentService();
    //time should not accept hh:mm:ss format and should be hh:mm
    String time = "12:12:12";
    //it should be in two digits
    String time1 = "2:12";
    //there cannot be 24:00 time possible
    String time2 = "24:00";
    //minutes cannot exceed 59
    String time3 = "01:60";
    //string to validate a proper time
    String time4 = "12:23";
    assertEquals(false, appointmentService.validateTime(time),"time is not validated");
    assertEquals(false, appointmentService.validateTime(time1),"time is not validated");
    assertEquals(false, appointmentService.validateTime(time2),"time is not validated");
    assertEquals(false, appointmentService.validateTime(time3),"time is not validated");
    assertEquals(true, appointmentService.validateTime(time4),"time is not validated");

  }
}
