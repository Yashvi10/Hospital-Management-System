package View;

import Interface.AddCampsDAO;
import Interface.FeatureMenu;
import Interface.ListOfCampsDAO;
import Interface.SearchCampsDAO;
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

  private SearchCampsDAO searchCampsDAO;

  private ListOfCampsDAO listOfCampsDAO;

  public NearbyCamps() {
  }

  public NearbyCamps(AddCampsDAO addCampsDAO, SearchCampsDAO searchCampsDAO, ListOfCampsDAO listOfCampsDAO) {
    this.addCampsDAO = addCampsDAO;
    this.searchCampsDAO = searchCampsDAO;
    this.listOfCampsDAO = listOfCampsDAO;
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
        searchCamps();
        break;
      case "3":
        System.out.println("==========List of all camps==========");
        listOfCamps();
        break;
      case "4":
        System.out.println("Exited");
        break;
      default:
        System.out.println("Invalid!");
    }
  }

  /* This method returns list of camps based on the provided location by user
   */
  public void searchCamps() {
    List<SearchCamps> searchCampsList = searchCampsDAO.searchCamp(camp_location);

    System.out.println(String.format(Constant.STRING_FORMAT, "Camp_Type") + " " + String.format(Constant.STRING_FORMAT, "Camp_Description") + " " + String.format(Constant.STRING_FORMAT, "Camp_Location") + " " + String.format(Constant.STRING_FORMAT, "CampAddedBy") + "\n");

    for (int i = 0; i < searchCampsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_type().replace('-', ' ')) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_description().replace('-', ' ')) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_location()) + " " + String.format(Constant.STRING_FORMAT, searchCampsList.get(i).getCamp_addedBy().replace('-', ' ')));
    }
    menu();
  }

  /* This method returns list of all available camps
   */
  public void listOfCamps() {
    List<ListOfCamps> listOfCampsList = listOfCampsDAO.allCamps();

    System.out.println(String.format(Constant.STRING_FORMAT, "Camp_Type") + " " + String.format(Constant.STRING_FORMAT, "Camp_Description") + " " + String.format(Constant.STRING_FORMAT, "Camp_Location") + " " + String.format(Constant.STRING_FORMAT, "CampAddedBy") + "\n");

    for (int i = 0; i < listOfCampsList.size(); i++) {
      System.out.println(String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_type()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_description()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_location()) + " " + String.format(Constant.STRING_FORMAT, listOfCampsList.get(i).getCamp_addedBy()));
    }
    menu();
  }

}
