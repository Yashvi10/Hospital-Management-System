package View;

import Interface.AddCampsDAO;
import Interface.FeatureMenu;
import java.util.Scanner;

/*
 *  Name of file: NearbyCamps.java
 *  Author:  Yashvi Lad
 *  Purpose: This is the driver class of feature NearbyCamps
 *  Description: It provides menu to users for selecting appropriate choice and perform their task.
 * */
public class NearbyCamps implements FeatureMenu {

  private AddCampsDAO addCampsDAO;

  public NearbyCamps(AddCampsDAO addCampsDAO) {
    this.addCampsDAO = addCampsDAO;
  }

  @Override
  public void menu() {

    System.out.println("Press 1 for Adding camps to list\nPress 2 for Searching camps\nPress 3 for Checking list of all camps\n" +
            "Press 4 for List of donors");

    Scanner input = new Scanner(System.in);
    String choice = input.next();

    switch(choice) {

      case "1":
        System.out.println("Adding camps");
        addCampsDAO.campDetails();
        break;
      case "2":
        System.out.println("Searching camps");
        break;
      case "3":
        System.out.println("List of all camps");
        break;
      case "4":
        System.out.println("Exited");
        break;
      default:
        System.out.println("Invalid!");
    }
  }

}
