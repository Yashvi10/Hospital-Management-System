package View;

import Interface.CovidBedBLInterface;
import Interface.CovidPlasmaBLInterface;
import Interface.FeatureMenu;
import Interface.IDashboard;
import Model.CovidPlasmaInformation;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: CovidPage.java
 *  Author:  Kushang Mistry
 *  Purpose: The primary class which provides all Covid Section related options to end-user
 *  Description: The class calls methods based on user's choice
 *               Sub-menu (Covid menu) iterates itself until user selects
 * */
public class CovidPage implements FeatureMenu {

  private CovidBedBLInterface covidBedBLInterface;

  private CovidPlasmaBLInterface covidPlasmaBLInterface;

  private IDashboard dashboard;

  public  CovidPage(CovidBedBLInterface covidBedBLInterface,
                    CovidPlasmaBLInterface covidPlasmaBLInterface,
                    IDashboard dashboard) {
    this.covidBedBLInterface = covidBedBLInterface;
    this.covidPlasmaBLInterface = covidPlasmaBLInterface;
    this.dashboard = dashboard;
  }

  @Override
  public void menu() throws IOException {

    System.out.println("\n==========================");
    System.out.println("Select options from below");
    System.out.println("==========================");

    System.out.println("1 = View Available Beds");
    System.out.println("2 = Request for a Bed");
    System.out.println("3 = Explore availability of plasma");
    System.out.println("\nPress 0 (Zero) to for Main-Menu");
    System.out.println("\nEnter your choice: ");

    // Takes input from the user about their preference
    Scanner scanner = new Scanner(System.in);
    int userChoice = 0;

    try {
      userChoice = scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("WARNING: Please select from the given numbers only");
      menu();
    }

    // Loop iterates until user enters number 0 (Zero)
    while (userChoice != 0) {
      switch (userChoice) {
        case 1:
          System.out.println("\n==========================");
          System.out.println("Select options from below");
          System.out.println("==========================");

          System.out.println("1 = View Availability of General Beds");
          System.out.println("2 = View Availability of Oxygen Supply Beds");
          System.out.println("3 = View Availability of Ventilator Attached Beds");
          System.out.println("\nEnter your choice: ");

          // Takes input from the user about their preference
          Scanner scanner1 = new Scanner(System.in);
          int userChoiceSubMenu = 0;

          try {
            userChoice = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("WARNING: Please select from the given numbers only");
            menu();
          }

          if(userChoice != 0) {
            Integer count = covidBedBLInterface.getBedCount(userChoice);
            if(count == null) {
              System.out.println("We are sorry to inform you currently beds are not available in this category");
            } else {
              if (userChoice == 1) {
                System.out.println("There are "+count+" General beds available.");
              }
              else if (userChoice == 2) {
                System.out.println("There are "+count+" Oxygen beds available.");
              }
              else if (userChoice == 3) {
                System.out.println("There are "+count+" Vaccine beds available.");
              }
            }
          }
          break;

        case 2 :
          System.out.println("\n==========================");
          System.out.println("Select options from below");
          System.out.println("==========================");

          System.out.println("1 = Register a General Bed");
          System.out.println("2 = Register an Oxygen Supply Bed");
          System.out.println("3 = Register a Ventilator Attached Bed");
          System.out.println("\nEnter your choice: ");

          // Takes input from the user about their preference
          Scanner scannerRegister = new Scanner(System.in);
          int userChoiceRegister = 0;

          try {
            userChoiceRegister = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("WARNING: Please select from the given numbers only");
            menu();
          }

          if(userChoiceRegister != 0) {
            Integer bedNumber = covidBedBLInterface.registerBed(userChoiceRegister);
            if(bedNumber == null) {
              System.out.println("We are sorry to inform you currently beds are not available in this category - Bed not registered");
            } else {
              if (userChoiceRegister == 1) {
                System.out.println("You have successfully registered, note the number G-"+bedNumber);
              }
              else if (userChoiceRegister == 2) {
                System.out.println("You have successfully registered, note the number O-"+bedNumber);
              }
              else if (userChoiceRegister == 3) {
                System.out.println("You have successfully registered, note the number V-"+bedNumber);
              }
            }
          }
          break;

        case 3 :
          System.out.println("\n==========================");
          System.out.println("Here is information about plasma");
          System.out.println("==========================");

          List<CovidPlasmaInformation> covidPlasmaInformation = covidPlasmaBLInterface.getPlasmaList();

          if(covidPlasmaInformation == null || covidPlasmaInformation.size() == 0) {
            System.out.println("Currently no information available about plasma - try later.");
            break;
          } else {
            System.out.println("\n--------------------------");
            System.out.println("Number\tBlood Group\tAvailability");
            System.out.println("--------------------------");
            for (int i = 0; i < covidPlasmaInformation.size(); i++) {
              System.out.println(covidPlasmaInformation.get(i).getPlasmaId()+"\t\t"+covidPlasmaInformation.get(i).getBloodType()+
                      "\t\t"+covidPlasmaInformation.get(i).getPlasmaAvailability());
            }
            System.out.println("--------------------------");
          }
          break;

        default:
          System.out.println("Invalid Input, Please select right option");
          break;
      }
      System.out.println("\n\n==========================");
      System.out.println("Select options from below");
      System.out.println("==========================");

      System.out.println("1 = View Available Beds");
      System.out.println("2 = Request for a Bed");
      System.out.println("3 = Explore availability of plasma");
      System.out.println("\nPress 0 (Zero) to for Main-Menu");
      System.out.println("\nEnter your choice: ");

      try {
        userChoice = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("WARNING: Please select from the given numbers only");
        menu();
      }
    }
    dashboard.HomeMenu();
  }
}
