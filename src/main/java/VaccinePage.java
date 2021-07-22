import Interface.FeatureMenu;
import Interface.VaccineBLInterface;
import Interface.VaccineRegistrationBLInterface;
import Model.Vaccine;
import Service.UserSession;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: VaccinePage.java
 *  Author:  Kushang Mistry
 *  Purpose: The primary class which provides all Vaccine related options to end-user
 *  Description: The class calls methods based on user's choice
 *               Sub-menu (Vaccine menu) iterates itself until user selects
 * */
public class VaccinePage implements FeatureMenu {

  private VaccineBLInterface vaccineBlInterface;

  private VaccineRegistrationBLInterface vaccineRegistrationBlInterface;

  private List<Vaccine> vaccineList;

  public VaccinePage(VaccineBLInterface vaccineBlInterface, VaccineRegistrationBLInterface vaccineRegistrationBlInterface) {
    this.vaccineBlInterface = vaccineBlInterface;
    this.vaccineRegistrationBlInterface = vaccineRegistrationBlInterface;
  }

  /*
   *  2 - This methods registers the uer for vaccination
   *      Specifically for the first dose
   **/
  public void registerPatientForVaccination() {

  }

  // Method which is responsible to call Vaccine Menu (Sub-menu of the system)
  @Override
  public void menu() {

    System.out.println("\n==========================");
    System.out.println("Select options from below");
    System.out.println("==========================");

    System.out.println("1 = View Available Vaccines");
    System.out.println("2 = Register for Vaccination");
    System.out.println("\nPress 0 (Zero) to stop");
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
          System.out.println("\nAll vaccines are as follows");
          vaccineList = vaccineBlInterface.getVaccineData();

          /*
           *  Prints all vaccine information to the console
           *  Proceed further only if there is vaccine data available in database
           *  This is one of the boundary test case
           *  Generally private methods are not to check with unit tests
           *
           *  Below are string decorators, enhanced for end user
           **/
          System.out.println("------------------------------------------------");
          System.out.format("%-4s%2s%-29s%5s\n", "Number", "", "Vaccine Name", "Quantity");
          System.out.println("------------------------------------------------");

          if (vaccineList.size() != 0) {
            for (int i = 0; i < vaccineList.size(); i++) {
              System.out.format("\t%-4d%-29s%5d\n", vaccineList.get(i).getVaccineId(),
                      vaccineList.get(i).getVaccineName(), vaccineList.get(i).getAvailableDoses());
            }
            System.out.println("------------------------------------------------");
          }
          break;

        case 2 :
          System.out.println("------------------------------------------------");
          System.out.println("    Welcome to Vaccine Registration Process");
          System.out.println("------------------------------------------------");

          if(vaccineRegistrationBlInterface.checkUserRegistration(UserSession.userId) == 1) {
            System.out.println("Congratulations! - You have completed the first dose of the vaccination");
            System.out.println("Information! - You will get second dose of same Vaccine");
            System.out.println("Processing for the registration of second dose: ");
            System.out.println("User Registered for Second dose? "+
                    vaccineRegistrationBlInterface.registerUserVaccine(vaccineRegistrationBlInterface.getUserDetails(UserSession.userId)));
          }
          else if (vaccineRegistrationBlInterface.checkUserRegistration(UserSession.userId) == 2) {
            System.out.println("User already completed two dose and Fully Vaccinated - Congratulations!");
          }
          else {
            if(vaccineRegistrationBlInterface.getUserInformation())
              System.out.println("User Registered for vaccination successfully.");
            else
              System.out.println("Invalid Input parameters OR Vaccine is not available at moment");
          }
          break;

        default:
          System.out.println("Invalid Input, Please select right option");
          break;
      }
      System.out.println("\n\n==========================");
      System.out.println("Select options from below");
      System.out.println("==========================");

      System.out.println("1 = View Available Vaccines");
      System.out.println("2 = Register for Vaccination");
      System.out.println("\nPress 0 (Zero) to stop");
      System.out.println("\nEnter your choice: ");

      try {
        userChoice = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("WARNING: Please select from the given numbers only");
        menu();
      }
    }
  }
}
