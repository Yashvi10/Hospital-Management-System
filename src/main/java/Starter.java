import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Starter {
    //a
    public static void main(String[] args) {
        System.out.println("Working");

        BillingMenuPage billingPage = new BillingMenuPage();
        billingPage.showUserOrder();


    }

}
