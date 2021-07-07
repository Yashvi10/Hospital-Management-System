import java.sql.*;
import java.util.*;

public class BloodBank {

    public static double contact = 0;
    public static String firstname = null;
    public static String lastname = null;
    public static String middlename = null;
    public static String blood_group = null;
    public static String pid;
    public static Scanner input;

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

    public static void main(String args[]) throws SQLException {

        BloodBank b = new BloodBank();

//        PreparedStatement ps = null;
//        Connection connection = null;
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            System.out.println("JDBC Driver connection successfull.");
//        }
//
//        //handles jdbc connection errors
//        catch (Exception ex) {
//
//            System.out.println("Error in jdbc Driver");
//        }
//        // Establish a connection
//        try {
//            String LocalURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_8_DEVINT";
//
//            connection = DriverManager.getConnection(LocalURL, "CSCI5308_8_DEVINT_USER", "cWhbaAs94FR");
//            Statement stmt = connection.createStatement();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        System.out.println("1 = Blood Request\n2 = Blood Donate");
        System.out.println("Select one: 1 or 2");

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
                b.contact();
//                ps = connection.prepareStatement("insert into blood_request(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact) values(?,?,?,?,?,?)");
//                ps.setDouble(1,pid);
//                ps.setString(2, firstname);
//                ps.setString(3,middlename);
//                ps.setString(4,lastname);
//                ps.setString(5,blood_group);
//                ps.setBigDecimal(6,contact);
//                ps.executeUpdate();
                break;

            case 2 :
                System.out.println("You selected blood donation");
                b.pid();
                b.firstname();
                b.middlename();
                b.lastname();
                b.blood_group();
                b.contact();
//                ps = connection.prepareStatement("insert into blood_donation(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact) values(?,?,?,?,?,?)");
//                ps.setDouble(1,pid);
//                ps.setString(2, firstname);
//                ps.setString(3,middlename);
//                ps.setString(4,lastname);
//                ps.setString(5,blood_group);
//                ps.setBigDecimal(6,contact);
//                ps.executeUpdate();
                break;

            default :
                System.out.println("Your input is not valid. Check for valid input!");
                break;

        }
    }
}
