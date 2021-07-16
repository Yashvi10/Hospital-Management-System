import Interface.FeatureMenu;
import Model.Vaccine;
import Service.VaccineService;

import java.util.List;
import java.util.Scanner;

public class VaccinePage implements FeatureMenu {

    private void getVaccineData() {
        VaccineService vaccineService = new VaccineService();
        List<Vaccine> vaccineList = vaccineService.getVaccines();

        System.out.println("Number");
        System.out.printf("%15s", "Vaccine Name");
        System.out.printf("%40s", "Quantity");

        for (int i = 0; i < vaccineList.size(); i++) {
            System.out.println("\n"+vaccineList.get(i).getVaccineId());
            System.out.printf("%15s", vaccineList.get(i).getVaccineName());
            System.out.printf("%40s", vaccineList.get(i).getAvailableDoses());

            /*System.out.println(vaccineList.get(i).getVaccineId()+"\t"+
                    vaccineList.get(i).getVaccineName()+"\t\t\t\t"+
                    vaccineList.get(i).getAvailableDoses());*/
        }

        menu();
    }

    @Override
    public void menu() {
        VaccinePage vaccinePage = new VaccinePage();

        System.out.println("\n==========================");
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
            System.out.println("\n==========================");
            System.out.println("Select options from below");
            System.out.println("==========================");

            System.out.println("1 = View Available Vaccines");
            System.out.println("Any number greater than 1 to stop");
            System.out.println("\nEnter your choice: ");
            userChoice = scanner.nextInt();
        }
    }
}
