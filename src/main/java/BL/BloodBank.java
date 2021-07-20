package BL;

import Interface.FeatureMenu;
import Model.BloodDonor;
import Model.BloodInventory;
import Model.BloodRequester;
import Service.BloodDonorService;
import Service.BloodRequesterService;
import Service.BloodService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: BL.BloodBank.java
 *  Author:  Yashvi Lad
 *  Purpose: This is the driver class of feature BL.BloodBank
 *  Description: This class will call methods from other files and runs functions based on requests.
 * */
public class BloodBank implements FeatureMenu {

    static String contact;
    static String firstname = null;
    static String lastname = null;
    static String middlename = null;
    static String blood_group = null;
    static int blood_bottles = 0;
    static float haemoglobin = 0;
    static float weight = 0;
    static int age = 0;
    static String date = null;
    static Scanner input;

    public String firstname(){
        String firstname = null;
        input = new Scanner(System.in);
        System.out.println("Enter your firstname: ");
        firstname = input.next();

        if(firstname.matches("^[0-9]")){
            System.out.println("Your input is not valid");
            return null;
        }

        if(firstname == null || firstname.isEmpty()){
            System.out.println("Input cannot be empty!");
            return null;
        }
        return firstname;
    }

    public String middlename(){
        input = new Scanner(System.in);
        System.out.println("Enter your middlename: ");
        middlename = input.next();

        if(middlename.matches("^[0-9]")){
            System.out.println("Your input is not valid");
            return null;
        }

        if(middlename == null || middlename.isEmpty()){
            System.out.println("Input cannot be empty!");
            return null;
        }
        return middlename;
    }

    public static String lastname(){
        input = new Scanner(System.in);
        System.out.println("Enter your lastname: ");
        lastname = input.next();

        if(lastname.matches("^[0-9]")){
            System.out.println("Your input is not valid");
            return null;
        }

        if(lastname == null || lastname.isEmpty()){
            System.out.println("Input cannot be empty!");
            return null;
        }
        return lastname;
    }

    public String blood_group(){
        input = new Scanner(System.in);
        System.out.println("Enter blood group: ");
        blood_group = input.next();

        if(blood_group.matches("^[0-9]")){
            System.out.println("Your input is not valid");
            return null;
        }

        if(blood_group == null || blood_group.isEmpty()){
            System.out.println("Input cannot be empty!");
            return null;
        }
        return blood_group;
    }

    public String contact(){
        input = new Scanner(System.in);
        System.out.println("Enter your contact details: ");
        contact = input.next();

        if(contact == null || contact.isEmpty()){
            System.out.println("Input cannot be empty!");
            return null;
        }

        if(contact.length() > 10){
            System.out.println("Contact number cannot be more than 10 digits!");
            return null;
        }
        return contact;
    }

    public String Date(){
        input = new Scanner(System.in);
        System.out.println("Enter date in format YYYY-MM-DD: ");
        date = input.next();
        return date;
    }

    public Integer Bloodbottles(){
        input = new Scanner(System.in);
        System.out.println("Enter required bottles of blood: ");
        blood_bottles = input.nextInt();

        if(blood_bottles == 0){
            System.out.println("Input cannot be empty!");
            return 0;
        }
        return blood_bottles;
    }

    public void listOfItems() {

        BloodService bloodService = new BloodService();
        List<BloodInventory> bloodInventoryList = bloodService.getAllBloodGroup();

        for(int i =0;i<bloodInventoryList.size();i++) {
            System.out.println(bloodInventoryList.get(i).getBlood_group() + " " + bloodInventoryList.get(i).getNumber_of_bottles());
        }

        menu();
    }

    public void listOfRequesters() {

        BloodRequesterService bloodRequesterService = new BloodRequesterService();
        List<BloodRequester> bloodRequesterList = bloodRequesterService.getAllRequesters();

        for(int i =0;i<bloodRequesterList.size();i++) {
            System.out.println(bloodRequesterList.get(i).getFirstname() + " " + bloodRequesterList.get(i).getMiddlename()
                    + " " + bloodRequesterList.get(i).getLastname() + " " + bloodRequesterList.get(i).getBlood_group()
                    + " " + bloodRequesterList.get(i).getContact() + " " + bloodRequesterList.get(i).getDate());
        }

        menu();
    }

    public void listOfDonors() {

        BloodDonorService bloodDonorService = new BloodDonorService();
        List<BloodDonor> bloodDonorList = bloodDonorService.getAllDonors();

        for(int i =0;i<bloodDonorList.size();i++) {
            System.out.println(bloodDonorList.get(i).getFirstname() + " " + bloodDonorList.get(i).getMiddlename()
                    + " " + bloodDonorList.get(i).getLastname() + " " + bloodDonorList.get(i).getBlood_group()
                    + " " + bloodDonorList.get(i).getContact() + " " + bloodDonorList.get(i).getDate());
        }

        menu();
    }

    public Integer donationTest_age(){
        input = new Scanner(System.in);
        System.out.println("Enter your age: ");
        age = input.nextInt();

        if(age == 0){
            System.out.println("Input cannot be empty!");
            return 0;
        }
        return age;
    }

    public Float donationTest_Weight(){
        input = new Scanner(System.in);
        System.out.println("Enter your weight: ");
        weight = input.nextFloat();

        if(weight == 0){
            System.out.println("Input cannot be empty!");
            return 0.0f;
        }
        return weight;
    }

    public Float donationTest_Haemoglobin(){
        input = new Scanner(System.in);
        System.out.println("Enter your haemoglobin: ");
        haemoglobin = input.nextFloat();

        if(haemoglobin == 0){
            System.out.println("Input cannot be empty!");
            return 0.0f;
        }
        return haemoglobin;
    }

    public void addToinventory(){

        BloodDonor bloodDonor = new BloodDonor(firstname,middlename,lastname,blood_group,contact,date);
        BloodDonorService bloodDonorService = new BloodDonorService();
        Boolean insert = bloodDonorService.addDonor(bloodDonor);
        if(insert){
            System.out.println("Data inserted successfully");
        }

        Boolean insert1 = bloodDonorService.updateDonor(blood_group);
        if(insert1){
            System.out.println("Bottle Update successfully");
        }

        menu();

    }

    public int donationTest() throws SQLException {
        String d = null;
        if(age<18 || age>65){
            System.out.println("You are not allowed to donate blood");
        }
        else{
            if(weight<45){
                System.out.println("Not eligible!");
            }
            else{
                if(haemoglobin<12.5){
                    System.out.println("Cannot donate blood.");
                }
                else{
                    System.out.println("You passed test!");
                    d = "Your age is: " + age + "\n" + "Your weight is: " + weight + "\n" + "Your haemoglobin is: " + haemoglobin;
                    System.out.println(d);
                }
            }
        }
        if(d != null){
            addToinventory();
        }
        return 1;
    }

    public void OnRequestUpdateInventory(){

        BloodRequesterService bloodRequesterService = new BloodRequesterService();
        System.out.println(bloodRequesterService.isBloodAvaiable(blood_group));

        Integer counter = bloodRequesterService.isBloodAvaiable(blood_group);
        if(counter == 0){
            System.out.println("Requested blood group is not available at present.");
        }
        else{

            BloodRequester bloodRequester = new BloodRequester(firstname,middlename,lastname,blood_group,contact,date);
            Boolean insert = bloodRequesterService.addRequester(bloodRequester);
            if(insert){
                System.out.println("Data inserted successfully");
            }

            Boolean insert1 = bloodRequesterService.updateRequester(blood_bottles,blood_group);
            if(insert1){
                System.out.println("Bottle Update successfully");
            }

        }
        menu();
    }

    @Override
    public void menu() {

        BloodBank b = new BloodBank();

        System.out.println("1 = Blood Request\n2 = Blood Donate\n3 = List all available items\n" +
                "4 = List of requesters\n5 = List of donors");
        System.out.println("Select one: 1,2,3,4 or 5");

        input = new Scanner(System.in);
        int choice = input.nextInt();

        switch(choice){
            case 1 :
                System.out.println("You selected request for blood option!");
                b.firstname();
                b.middlename();
                b.lastname();
                b.blood_group();
                b.contact();
                b.Date();
                b.Bloodbottles();
                b.OnRequestUpdateInventory();

                break;

            case 2 :
                System.out.println("You selected blood donation");
                System.out.println("Provide your details and you will need to give some tests done to donate blood.");
                b.firstname();
                b.middlename();
                b.lastname();
                b.blood_group();
                b.contact();
                b.Date();
                b.donationTest_age();
                b.donationTest_Weight();
                b.donationTest_Haemoglobin();
                try {
                    b.donationTest();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;

            case 3 :
                System.out.println("You selected listing all items in inventory!");
                System.out.println("============List of Inventory============");
                b.listOfItems();

                break;

            case 4 :
                System.out.println("You selected list of details of blood requesters!");
                System.out.println("============List of Requesters============");
                b.listOfRequesters();

                break;

            case 5 :
                System.out.println("You selected list of details of blood donors!");
                System.out.println("============List of Donors============");
                b.listOfDonors();

                break;
            default :
                System.out.println("Your input is not valid. Check for valid input!");

                break;

        }
    }
}
