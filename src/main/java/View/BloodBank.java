package View;

import Interface.*;
import Model.BloodDonor;
import Model.BloodInventory;
import Model.BloodRequester;
import Service.BloodDonorService;
import Service.BloodRequesterService;
import Service.BloodService;

import java.io.IOException;
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

    private BloodServiceBLDAO bloodServiceBLDAO;

    private BloodRequesterBLDAO bloodRequesterBLDAO;

    private BloodDonorBLDAO bloodDonorBLDAO;

    public BloodBank() {}

    public BloodBank(BloodServiceBLDAO bloodServiceBLDAO, BloodRequesterBLDAO bloodRequesterBLDAO,
                     BloodDonorBLDAO bloodDonorBLDAO) {
        this.bloodServiceBLDAO = bloodServiceBLDAO;
        this.bloodRequesterBLDAO = bloodRequesterBLDAO;
        this.bloodDonorBLDAO = bloodDonorBLDAO;
    }

    /*
     *  This method takes user input of firstname
     * */
    public String firstname(){
        String firstname = null;
        input = new Scanner(System.in);
        System.out.println("Enter your firstname: ");
        firstname = input.nextLine();

        if(firstname.matches("[^a-zA-Z]+")){
            System.out.println("Your input is not valid");
            menu();
        }

        if(firstname == null || firstname.isEmpty()){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return firstname;
    }

    /*
     *  This method takes user input of middlename
     * */
    public String middlename(){
        input = new Scanner(System.in);
        System.out.println("Enter your middlename: ");
        middlename = input.nextLine();

        if(middlename.matches("[^a-zA-Z]+")){
            System.out.println("Your input is not valid");
            menu();
        }

        if(middlename == null || middlename.isEmpty()){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return middlename;
    }

    /*
     *  This method takes user input of lastname
     * */
    public String lastname(){
        input = new Scanner(System.in);
        System.out.println("Enter your lastname: ");
        lastname = input.nextLine();

        if(lastname.matches("[^a-zA-Z]+")){
            System.out.println("Your input is not valid");
            menu();
        }

        if(lastname == null || lastname.isEmpty()){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return lastname;
    }

    /*
     *  This method takes user input of blood group
     * */
    public String blood_group(){
        input = new Scanner(System.in);
        System.out.println("Enter blood group: ");
        blood_group = input.nextLine();

        if(blood_group.matches("[^a-zA-Z+-]+")){
            System.out.println("Your input is not valid");
            menu();
        }

        if(blood_group == null || blood_group.isEmpty()){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return blood_group;
    }

    /*
     *  This method takes user input of contact
     * */
    public String contact(){

        input = new Scanner(System.in);
        System.out.println("Enter your contact details: ");
        contact = input.nextLine();

        if(contact == null || contact.isEmpty()){
            System.out.println("Input cannot be empty!");
            menu();
        }

        if(contact.matches("[^0-9]+")) {
            System.out.println("Invalid Input");
            menu();
        }

        if(contact.length() > 10 || contact.length() < 10){
            System.out.println("Contact number can be only 10 digits!");
            menu();
        }
        return contact;
    }

    /*
     *  This method takes user input of date
     * */
    public String Date(){
        input = new Scanner(System.in);
        System.out.println("Enter date in format YYYY-MM-DD: ");
        date = input.nextLine();
        return date;
    }

    /*
     *  This method takes user input of required blood bottles
     * */
    public Integer Bloodbottles(){
        input = new Scanner(System.in);
        System.out.println("Enter required bottles of blood: ");
        blood_bottles = input.nextInt();

        if(blood_bottles == 0){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return blood_bottles;
    }

    /*
     *  This method takes user input of age
     * */
    public Integer donationTest_age(){
        input = new Scanner(System.in);
        System.out.println("Enter your age: ");
        age = input.nextInt();

        if(age == 0){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return age;
    }

    /*
     *  This method takes user input of weight
     * */
    public Float donationTest_Weight(){
        input = new Scanner(System.in);
        System.out.println("Enter your weight: ");
        weight = input.nextFloat();

        if(weight == 0){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return weight;
    }

    /*
     *  This method takes user input of haemoglobin
     * */
    public Float donationTest_Haemoglobin(){
        input = new Scanner(System.in);
        System.out.println("Enter your haemoglobin: ");
        haemoglobin = input.nextFloat();

        if(haemoglobin == 0){
            System.out.println("Input cannot be empty!");
            menu();
        }
        return haemoglobin;
    }

    /*
     *  This method adds details of donors and updates blood bottles into database
     * */
    public Boolean addToinventory(){

        BloodDonor bloodDonor = new BloodDonor(firstname,middlename,lastname,blood_group,contact,date);
        BloodDonorService bloodDonorService = new BloodDonorService();
        Boolean insertData = bloodDonorService.addDonor(bloodDonor);
        if(insertData){
            System.out.println("Data inserted successfully!");
        }

        Boolean insertBottle = bloodDonorService.updateDonor(blood_group);
        if(insertBottle){
            System.out.println("Bottle Update successfully!\n");
        }

        menu();
        return insertBottle;
    }

    /*
     *  This method consists test logic to check health of blood donors
     * */
    public Integer donationTest() throws SQLException {
        String user_details = null;
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
                    user_details = "Your age is: " + age + "\n" + "Your weight is: " + weight + "\n" + "Your haemoglobin is: " + haemoglobin;
                }
            }
        }
        if(user_details != null){
            addToinventory();
        }
        return 1;
    }

    /*
     *  This method adds details of requesters and updates blood bottles based on the request in database
     * */
    public Integer OnRequestUpdateInventory(){

        BloodRequesterService bloodRequesterService = new BloodRequesterService();
        System.out.println(bloodRequesterService.isBloodAvaiable(blood_group));

        Integer counter = bloodRequesterService.isBloodAvaiable(blood_group);
        if(counter == 0){
            System.out.println("Requested blood group is not available at present.");
        }
        else{

            BloodRequester bloodRequester = new BloodRequester(firstname,middlename,lastname,blood_group,contact,date);
            Boolean insertData = bloodRequesterService.addRequester(bloodRequester);
            if(insertData){
                System.out.println("Data inserted successfully!");
            }

            Boolean updateBottle = bloodRequesterService.updateRequester(blood_bottles,blood_group);
            if(updateBottle){
                System.out.println("Bottle Update successfully!\n");
            }

        }
        menu();
        return counter;
    }

    /*
     *  This method offers menu to user
     * */
    @Override
    public void menu() {

        BloodBank b = new BloodBank();

        System.out.println("Press 1 for Blood Request\nPress 2 for Blood Donate\nPress 3 for List all available items\n" +
                "Press 4 for List of requesters\nPress 5 for List of donors\nPress 6 for Home Menu");

        input = new Scanner(System.in);
        String choice = input.next();

        if (choice.equals("1")) {
        } else if (choice.equals("2")) {
        } else if (choice.equals("3")) {
        } else if (choice.equals("4")) {
        } else if (choice.equals("5")) {
        } else if (choice.equals("6")) {
        } else {
            System.out.println("Please select correct option");
        }

        switch(choice){
            case "1" :
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

            case "2" :
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

            case "3" :
                System.out.println("You selected listing all items in inventory!");
                System.out.println("============List of Inventory============");
                bloodServiceBLDAO.listOfItems();

                break;

            case "4" :
                System.out.println("You selected list of details of blood requesters!");
                System.out.println("============List of Requesters============");
                bloodRequesterBLDAO.listOfRequesters();

                break;

            case "5" :
                System.out.println("You selected list of details of blood donors!");
                System.out.println("============List of Donors============");
                bloodDonorBLDAO.listOfDonors();

                break;
            case "6" :
                Dashboard dashboard = new Dashboard();
                try {
                    dashboard.homeMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default :
                System.out.println("Your input is not valid. Check for valid input!");

                break;

        }
    }
}
