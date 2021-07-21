/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is the model class for database tables mapping
 *  Description: This class is used for mapping variables to interact with database tables
 *  and to easily get and set these variables
 * */
package Model;

public class AppointmentModel {

    private Integer appointment_id;

    private Integer user_id;

    private Integer doctor_id;

    private String appointment_date;

    private String appointment_time;

    private String appointment_status;

    private Integer reschedule_counts;

    public AppointmentModel(Integer user_id,Integer doctor_id, String appointment_date, String appointment_time, String appointment_status, Integer reschedule_counts) {
        this.user_id = user_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.appointment_status = appointment_status;
        this.reschedule_counts = reschedule_counts;
    }

    public AppointmentModel() {
    }

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }

    public Integer getReschedule_counts() {
        return reschedule_counts;
    }

    public void setReschedule_counts(Integer reschedule_counts) {
        this.reschedule_counts = reschedule_counts;
    }
}
