package Model;

/*
 *  Name of file: BloodInventory.java
 *  Author:  Yashvi Lad
 *  Purpose: To list all the available items in blood inventory.
 *  Description: This file will fetch all the items from database table to check available items in inevntory.
 * */

public class BloodInventory {

    private Integer blood_id;
    private String blood_group;
    private Integer Number_of_bottles;

    public BloodInventory(Integer blood_id,String blood_group, Integer number_of_bottles) {
        this.blood_id = blood_id;
        this.blood_group = blood_group;
        Number_of_bottles = number_of_bottles;
    }

    public Integer getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(Integer blood_id) {
        this.blood_id = blood_id;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public Integer getNumber_of_bottles() {
        return Number_of_bottles;
    }

    public void setNumber_of_bottles(Integer number_of_bottles) {
        Number_of_bottles = number_of_bottles;
    }
}
