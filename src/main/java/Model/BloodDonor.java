package Model;

/*
 *  Name of file: BloodDonor.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like mapper help us in interacting with DB models.
 *  Description: The file will work as a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */
public class BloodDonor {

    private Integer donation_id;

    private String firstname;

    private String middlename;

    private String lastname;

    private String blood_group;

    private String contact;

    private String date;

    public Integer getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(Integer donation_id) {
        this.donation_id = donation_id;
    }

    public BloodDonor(String firstname, String middlename, String lastname,
                      String blood_group, String contact, String date) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.blood_group = blood_group;
        this.contact = contact;
        this.date = date;
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

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
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
