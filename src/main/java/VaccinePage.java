import Interface.FeatureMenu;
import Model.Vaccine;
import Service.VaccineService;

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

    /*
    *  A method which gets vaccine information from the database
    *  And displays it to the console.
    *  Hence, user can get idea which vaccines are available
    **/
    private void getVaccineData() {
        VaccineService vaccineService = new VaccineService();
        List<Vaccine> vaccineList = vaccineService.getVaccines();   // Fetches and stores all vaccine information

        // Below are string decorators, enhanced for end user
        System.out.println("------------------------------------------------");
        System.out.format("%-4s%2s%-29s%5s\n", "Number", "", "Vaccine Name", "Quantity");
        System.out.println("------------------------------------------------");

        // Prints all vaccine information to the console
        for (int i = 0; i < vaccineList.size(); i++) {
            System.out.format("\t%-4d%-29s%5d\n", vaccineList.get(i).getVaccineId(),
                    vaccineList.get(i).getVaccineName(), vaccineList.get(i).getAvailableDoses());
        }
        System.out.println("------------------------------------------------");
    }

    // Method which is responsible to call Vaccine Menu (Sub-menu of the system)
    @Override
    public void menu() {
        VaccinePage vaccinePage = new VaccinePage();

        System.out.println("\n==========================");
        System.out.println("Select options from below");
        System.out.println("==========================");

        System.out.println("1 = View Available Vaccines");
        System.out.println("\nPress 0 (Zero) to stop");
        System.out.println("\nEnter your choice: ");

        // Takes input from the user about their preference
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        try {
            userChoice = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("WARNING: Please select from the given numbers only");
            menu();
        }

        // Loop iterates until user enters number 0 (Zero)
        while (userChoice != 0) {
            switch (userChoice) {
                case 1 :
                    System.out.println("\nAll vaccines are as follows");
                    vaccinePage.getVaccineData();
                    break;

                default:
                    System.out.println("Invalid Input, Please select right option");
                    break;
            }
            System.out.println("\n\n==========================");
            System.out.println("Select options from below");
            System.out.println("==========================");

            System.out.println("1 = View Available Vaccines");
            System.out.println("\nPress 0 (Zero) to stop");
            System.out.println("\nEnter your choice: ");

            try {
                userChoice = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("WARNING: Please select from the given numbers only");
                menu();
            }
        }
    }
}
