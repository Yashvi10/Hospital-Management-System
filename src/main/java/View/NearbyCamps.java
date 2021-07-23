package View;

import BL.ListOfCampsBL;
import Interface.*;
import Model.ListOfCamps;
import Model.SearchCamps;

import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: NearbyCamps.java
 *  Author:  Yashvi Lad
 *  Purpose: This is the driver class of feature NearbyCamps
 *  Description: It provides menu to users for selecting appropriate choice and perform their task.
 * */
public class NearbyCamps implements FeatureMenu {


  private static String camp_location;

  private AddCampsDAO addCampsDAO;

  private SearchCampsBLDAO searchCampsBLDAO;

  private ListOfCampsBLDAO listOfCampsBLDAO;

  public NearbyCamps() {
  }

  public NearbyCamps(AddCampsDAO addCampsDAO, SearchCampsBLDAO searchCampsBLDAO, ListOfCampsBLDAO listOfCampsBLDAO) {
    this.addCampsDAO = addCampsDAO;
    this.searchCampsBLDAO = searchCampsBLDAO;
    this.listOfCampsBLDAO = listOfCampsBLDAO;
  }

  @Override
  public void menu() {

    System.out.println("\nPress 1 for Adding camps to list\nPress 2 for Searching camps\nPress 3 for Checking list of all camps\n" + "Press 4 for Exited");

    Scanner input = new Scanner(System.in);
    String choice = input.next();

    if (choice == null || choice.isEmpty()) {
      System.out.println("Input cannot be empty!");
      return;
    }

    if (choice.equals("1")) {
    } else if (choice.equals("2")) {
    } else if (choice.equals("3")) {
    } else if (choice.equals("4")) {
    } else {
      System.out.println("Please select correct option");
    }

    switch (choice) {

      case "1":
        System.out.println("==========Adding camps to list==========");
        addCampsDAO.campDetails();
        menu();
        break;
      case "2":
        System.out.println("==========Searching camps==========");
        Scanner camp = new Scanner(System.in);
        System.out.println("Enter your location: ");
        camp_location = camp.next();

        if (camp_location.matches("^[0-9]")) {
          System.out.println("Your input is not valid");
          return;
        }

        if (camp_location == null || camp_location.isEmpty()) {
          System.out.println("Input cannot be empty!");
          return;
        }
        searchCampsBLDAO.getCamps(camp_location);
        break;
      case "3":
        System.out.println("==========List of all camps==========");
        listOfCampsBLDAO.getCampsData();
        break;
      case "4":
        System.out.println("Exited");
        break;
      default:
        System.out.println("Invalid!");
    }
  }

}
