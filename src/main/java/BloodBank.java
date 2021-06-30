import java.math.*;
import java.sql.*;
import java.util.Scanner;

public class BloodBank {
    public static void main(String args[]) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
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

        int choice;
        BigDecimal contact;
        String firstname,lastname,middlename, blood_group;
        double pid;
        Scanner input = new Scanner(System.in);
        System.out.println("1 = Blood Request\n2 = Blood Donate");
        System.out.println("Select one: 1 or 2");
        choice = input.nextInt();

        switch(choice){
            case 1 :
                System.out.println("You selected request for blood option!");
                System.out.println("Enter your personal identification number: ");
                pid = input.nextDouble();
                System.out.println("Enter your firstname: ");
                firstname = input.next();
                System.out.println("Enter your middlename: ");
                middlename = input.next();
                System.out.println("Enter your lastname: ");
                lastname = input.next();
                System.out.println("Enter required blood group: ");
                blood_group = input.next();
                System.out.println("Enter your contact details: ");
                contact = input.nextBigDecimal();
                ps = connection.prepareStatement("insert into blood_request(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact) values(?,?,?,?,?,?)");
                ps.setDouble(1,pid);
                ps.setString(2, firstname);
                ps.setString(3,middlename);
                ps.setString(4,lastname);
                ps.setString(5,blood_group);
                ps.setBigDecimal(6,contact);
                ps.executeUpdate();
                break;

            case 2 :
                System.out.println("You selected blood donation");
                System.out.println("Enter your personal identification number: ");
                pid = input.nextDouble();
                System.out.println("Enter your firstname: ");
                firstname = input.next();
                System.out.println("Enter your middlename: ");
                middlename = input.next();
                System.out.println("Enter your lastname: ");
                lastname = input.next();
                System.out.println("Enter your blood group: ");
                blood_group = input.next();
                System.out.println("Enter your contact details: ");
                contact = input.nextBigDecimal();
                ps = connection.prepareStatement("insert into blood_donation(PIN, First_Name, Middle_Name, Last_Name,Blood_Group,Contact) values(?,?,?,?,?,?)");
                ps.setDouble(1,pid);
                ps.setString(2, firstname);
                ps.setString(3,middlename);
                ps.setString(4,lastname);
                ps.setString(5,blood_group);
                ps.setBigDecimal(6,contact);
                ps.executeUpdate();
                break;

            default :
                System.out.println("Invalid input");
                break;

        }




    }
}
