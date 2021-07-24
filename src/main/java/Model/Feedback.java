package Model;

/*
 *  Name of file: Feedback.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper and its only responsible for handling Feedback data
 * */
public class Feedback {

    private Integer id;

    private Integer appointment_id;

    private Integer user_id;

    private String feedback;

    private Integer rating;

    public Feedback(){}
    public Feedback(Integer appointment_id, Integer user_id, String feedback, Integer rating) {
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.feedback = feedback;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
