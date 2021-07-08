import java.sql.*;
import java.util.*;

public class BloodBank {

    public static double contact = 0;
    public static String firstname = null;
    public static String lastname = null;
    public static String middlename = null;
    public static String blood_group = null;
    public static String pid;
    public static int unitsrequired = 0;
    public static float haemoglobin = 0;
    public static float weight = 0;
    public static double age = 0.0;
    public static String date = null;
    public static Scanner input;
    public static PreparedStatement ps = null;
    public static Connection connection = null;
    static Map<String, Integer> inventory = new HashMap<>();

    public static String pid(){
        input = new Scanner(System.in);
        System.out.println("Enter your personal identification number: ");
        pid = input.next();
        return pid;
    }

    public static String firstname(){
        input = new Scanner(System.in);
        System.out.println("Enter your firstname: ");
        firstname = input.next();
        return firstname;
    }

    public static String middlename(){
        input = new Scanner(System.in);
        System.out.println("Enter your middlename: ");
        middlename = input.next();
        return middlename;
    }

    public static String lastname(){
        input = new Scanner(System.in);
        System.out.println("Enter your lastname: ");
        lastname = input.next();
        return lastname;
    }

    public static String blood_group(){
        input = new Scanner(System.in);
        System.out.println("Enter blood group: ");
        blood_group = input.next();
        return blood_group;
    }

    public static Double contact(){
        input = new Scanner(System.in);
        System.out.println("Enter your contact details: ");
        contact = input.nextDouble();
        return contact;
    }

    public static String Date(){
        input = new Scanner(System.in);
        System.out.println("Enter date in format YYYY-MM-DD: ");
        date = input.next();
        return date;
    }

    public static Integer requiredBloodbottles(){
        input = new Scanner(System.in);
        System.out.println("Enter required units of blood: ");
        unitsrequired = input.nextInt();
        return unitsrequired;
    }
    public static void listOfItems(){
        System.out.println(inventory);
        for(Map.Entry<String, Integer> map : inventory.entrySet()){
            if(map.getValue() < 20 ){
                System.out.println("Needs blood of group - " + map.getKey());
            }
        }

    }

//    public void place_bloodRequest(){
//
//    }

    public static Double donationTest_age(){
        input = new Scanner(System.in);
        System.out.println("Enter your age: ");
        age = input.nextDouble();
//        System.out.println(age);
        return age;
    }

    public static Float donationTest_Weight(){
        input = new Scanner(System.in);
        System.out.println("Enter your weight: ");
        weight = input.nextFloat();
        System.out.println(weight);
        return weight;
    }

    public static Float donationTest_Haemoglobin(){
        input = new Scanner(System.in);
        System.out.println("Enter your haemoglobin: ");
        haemoglobin = input.nextFloat();
        return haemoglobin;
    }

    public static void addToinventory() throws SQLException {
        ps = connection.prepareStatement("insert into blood_donation(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) values(?,?,?,?,?,?,?)");
        ps.setString(1,pid);
        ps.setString(2, firstname);
        ps.setString(3,middlename);
        ps.setString(4,lastname);
        ps.setString(5,blood_group);
        ps.setDouble(6,contact);
        ps.setString(7,date);
        ps.executeUpdate();
    }

    public static int donationTest() throws SQLException {
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

    public static void updateInventory() throws SQLException {
        for(Map.Entry<String, Integer> map : inventory.entrySet()){
            if(map.getKey() == blood_group() && map.getValue() == requiredBloodbottles()){
                map.setValue(map.getValue() - 1);
                ps = connection.prepareStatement("update into blood_inventory SET No_of_bottles=No_of_bottles-1 where map.getKey() == blood_group()");
            }
        }
    }

    public static void main(String args[]) throws SQLException {

        BloodBank b = new BloodBank();
        inventory.put("A+", 10);
        inventory.put("A-", 50);
        inventory.put("B+", 150);
        inventory.put("B-", 200);
        inventory.put("AB+", 100);
        inventory.put("AB-", 250);
        inventory.put("O+", 10);
        inventory.put("O-", 250);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("JDBC Driver connection successfull.");
        }

        //handles jdbc connection errors
        catch (Exception ex) {

            System.out.println("Error in jdbc Driver");
        }
        // Establish a connection
        try {
            String LocalURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_8_DEVINT";

            connection = DriverManager.getConnection(LocalURL, "CSCI5308_8_DEVINT_USER", "cWhbaAs94FR");
            Statement stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("1 = Blood Request\n2 = Blood Donate\n3 = List all available items");
        System.out.println("Select one: 1,2 or 3");

        input = new Scanner(System.in);
        int choice = input.nextInt();

        switch(choice){
            case 1 :
                System.out.println("You selected request for blood option!");
                b.pid();
                b.firstname();
                b.middlename();
                b.lastname();
                b.blood_group();
                b.requiredBloodbottles();
                b.contact();
                b.Date();
                b.updateInventory();

//                ps = connection.prepareStatement("insert into blood_request(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) values(?,?,?,?,?,?,?)");
//                ps.setString(1,pid);
//                ps.setString(2, firstname);
//                ps.setString(3,middlename);
//                ps.setString(4,lastname);
//                ps.setString(5,blood_group);
//                ps.setDouble(6,contact);
//                ps.setString(7,date);
//                ps.executeUpdate();
                break;

            case 2 :
                System.out.println("You selected blood donation");
                System.out.println("Provide your details and you will need to give some tests done to donate blood.");
                b.pid();
                b.firstname();
                b.middlename();
                b.lastname();
                b.blood_group();
                b.contact();
                b.Date();
                b.donationTest_age();
                b.donationTest_Weight();
                b.donationTest_Haemoglobin();
                b.donationTest();

//                ps = connection.prepareStatement("insert into blood_donation(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact,Date) values(?,?,?,?,?,?,?)");
//                ps.setString(1,pid);
//                ps.setString(2, firstname);
//                ps.setString(3,middlename);
//                ps.setString(4,lastname);
//                ps.setString(5,blood_group);
//                ps.setDoublel(6,contact);
//                ps.setString(7,date);
//                ps.executeUpdate();
                break;

            case 3 :
                System.out.println("You selected listing all items in inventory!");
                b.listOfItems();
                break;
            default :
                System.out.println("Your input is not valid. Check for valid input!");
                break;

        }
    }
}
