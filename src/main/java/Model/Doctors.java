package Model;

import javax.print.Doc;
/*
 *  Name of file: Doctors.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */
public class Doctors {

    private Integer doctor_id;

    private String name;

    private String specialization;

    private Double rating;

    public Doctors(){}

    public Doctors(Integer doctor_id,String name, String specialization) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.specialization = specialization;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
