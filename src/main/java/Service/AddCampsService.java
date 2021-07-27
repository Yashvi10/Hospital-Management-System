package Service;

import Interface.AddCampsDAO;
import Model.AddCamps;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 *  Name of file: AddCampsService.java
 *  Author:  Yashvi Lad
 *  Purpose: This class is like service which will implement the AddCampsDAO
 *  Description: This class will implement all the actual logic define in AddCampsDAO
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
    camp_type = input.nextLine();

    if (camp_type.matches("[^a-zA-Z]+")) {
      System.out.println("Your input is not valid");
      return false;
    }

    if (camp_type == null || camp_type.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return false;
    }

    input = new Scanner(System.in);
    System.out.println("Enter camp description: ");
    camp_description = input.nextLine();

    if (camp_description.matches("[^a-zA-Z-]+")) {
      System.out.println("Your input is not valid");
      return false;
    }

    if (camp_description == null || camp_description.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return false;
    }

    input = new Scanner(System.in);
    System.out.println("Enter camp location: ");
    camp_location = input.nextLine();

    if (camp_location.matches("[^a-zA-Z]+")) {
      System.out.println("Your input is not valid");
      return false;
    }

    if (camp_location == null || camp_location.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return false;
    }

    input = new Scanner(System.in);
    System.out.println("Enter your fullname: ");
    added_by = input.nextLine();

    if (added_by.matches("[^a-zA-Z]+")) {
      System.out.println("Your input is not valid");
      return false;
    }

    if (added_by == null || added_by.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return false;
    }

    AddCamps addCamps = new AddCamps(camp_type, camp_description, camp_location, added_by);

    addToDatabase(addCamps);

    return true;
  }

  @Override
  public Boolean addToDatabase(AddCamps addCamps) {

    Connection conn = customConnection.Connect();

    Boolean result = false;

    if (conn != null) {
      String SQL = "insert into AddCamps(camp_type,camp_description,camp_location,camp_adddedBy) " + "values('" + addCamps.getCamp_type() + "','" + addCamps.getCamp_description() + "','" + addCamps.getCamp_location() + "','" + addCamps.getCamp_addedBy() + "')";

      Statement statement = null;
      try {
        statement = conn.createStatement();
        statement.executeUpdate(SQL);
        conn.close();
        result = true;
        return result;

      } catch (SQLException throwables) {
        throwables.printStackTrace();
      } finally {
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
