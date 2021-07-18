package Model;

public class AppointmentModel {
    private Integer appointment_id;

    private Integer user_id;

    private String appointment_date;

    private String appointment_time;

    private String appointment_status;

    public AppointmentModel(Integer user_id, String appointment_date, String appointment_time, String appointment_status) {
        this.user_id = user_id;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.appointment_status = appointment_status;
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
}
