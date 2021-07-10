package Model;

/*
 *  Name of file: BloodRequester.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like mapper help us in interacting with DB models.
 *  Description: The file will work as a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */

public class BloodRequester {

    private Integer request_id;

    private String firstname;

    private String middlename;

    private String lastname;

    private String blood_group;

    private String contact;

    private String date;

    public BloodRequester(String firstname, String middlename, String lastname, String blood_group, String contact, String date) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.blood_group = blood_group;
        this.contact = contact;
        this.date = date;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
