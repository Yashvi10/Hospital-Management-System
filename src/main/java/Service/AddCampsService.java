package Service;

import Interface.AddCampsDAO;
import Model.AddCamps;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * File Name: AddCampsService.java
 * Author: Yashvi Lad
 * */
public class AddCampsService implements AddCampsDAO {

  CustomConnection customConnection = new CustomConnection();

  Scanner input;

  String camp_type;

  String camp_description;

  String camp_location;

  String added_by;

  @Override
  public Boolean campDetails() {

    input = new Scanner(System.in);
    System.out.println("Enter camp type: ");
    camp_type = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter camp description: ");
    camp_description = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter camp location: ");
    camp_location = input.next();

    input = new Scanner(System.in);
    System.out.println("Enter your fullname: ");
    added_by = input.next();

    AddCamps addCamps = new AddCamps(camp_type,camp_description,camp_location,added_by);

    addToDatabase(addCamps);

    return true;
  }

  @Override
  public Boolean addToDatabase(AddCamps addCamps) {

    Connection conn = customConnection.Connect();

    Boolean result = false;

    if(conn != null) {
      String SQL = "insert into AddCamps(camp_type,camp_description,camp_location,camp_adddedBy) " +
              "values('" +addCamps.getCamp_type() +"','" +addCamps.getCamp_description()
              +"','" +addCamps.getCamp_location() +"','" +addCamps.getCamp_addedBy() +"')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        result = true;
        return result;

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
    return result;
  }

}
