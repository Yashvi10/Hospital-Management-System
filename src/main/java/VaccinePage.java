import Interface.FeatureMenu;
import Model.Vaccine;
import Service.VaccineService;

import java.util.List;
import java.util.Scanner;

public class VaccinePage implements FeatureMenu {

    private void getVaccineData() {
        VaccineService vaccineService = new VaccineService();
        List<Vaccine> vaccineList = vaccineService.getVaccines();

        System.out.println("------------------------------------------------");
        System.out.format("%-4s%2s%-29s%5s\n", "Number", "", "Vaccine Name", "Quantity");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < vaccineList.size(); i++) {
            System.out.format("\t%-4d%-29s%5d\n", vaccineList.get(i).getVaccineId(),
                    vaccineList.get(i).getVaccineName(), vaccineList.get(i).getAvailableDoses());
        }

        menu();
    }

    @Override
    public void menu() {
        VaccinePage vaccinePage = new VaccinePage();

        System.out.println("\n\n==========================");
        System.out.println("Select options from below");
        System.out.println("==========================");

        System.out.println("1 = View Available Vaccines");
        System.out.println("\nEnter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        while (userChoice<2) {
            switch (userChoice) {
                case 1 :
                    System.out.println("\nAll vaccines are as follows");
                    vaccinePage.getVaccineData();
                    break;

                default:
                    System.out.println("Invalid Input, exiting ...");
                    break;
            }
            System.out.println("\n\n==========================");
            System.out.println("Select options from below");
            System.out.println("==========================");

            System.out.println("1 = View Available Vaccines");
            System.out.println("Any number greater than 1 to stop");
            System.out.println("\nEnter your choice: ");
            userChoice = scanner.nextInt();
        }
    }
}
