import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Starter {
    //a
    public static void main(String[] args) {
        UserManagementPage userManagementPage = new UserManagementPage();
        userManagementPage.MainMenu();
    }
}
