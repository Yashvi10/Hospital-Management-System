package Model;

import javax.print.Doc;

public class Doctors {

    private Integer doctor_id;

    private String name;

    private String specialization;

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
}
