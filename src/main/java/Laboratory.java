import Interface.GenerateLabReportsDAO;
import Model.GenerateLabReports;
import Service.GenerateLabReportsService;
import java.util.List;
import java.util.Scanner;

public class Laboratory {

    private GenerateLabReportsDAO generateLabReportsDAO;

    public Laboratory(){};

    public Laboratory(GenerateLabReportsDAO generateLabReportsDAO){
        this.generateLabReportsDAO = generateLabReportsDAO;
    }

    public void LaboratoryMenu(){

        Laboratory laboratory = new Laboratory();
        System.out.println("===============================");
        System.out.println("Press 1 for Applying for Tests\nPress 2 for Listing all Tests\nPress 3 for Generating your test reports\n" +
                "Press 4 to exit");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch(choice){
            case 1 :
                System.out.println("To apply for any test provide your details.");

                break;

            case 2 :
                System.out.println("============List of all tests============");
                laboratory.listOfTests();
                break;

            case 3 :
                System.out.println("Generate reports");

                break;

            case 4 :
                System.out.println("Exited");

                break;

            default :
                System.out.println("Your input is not valid. Check for valid input!");

                break;

        }
    }

    /* This method returns list of all tests from lab_tests
     */
    public void listOfTests() {

        GenerateLabReportsService generateLabReportsService = new GenerateLabReportsService();
        List<GenerateLabReports> generateLabReportsList = generateLabReportsService.getListOfTests();

        for(int i =0;i<generateLabReportsList.size();i++) {
            System.out.println(generateLabReportsList.get(i).getTest_id() + " " + generateLabReportsList.get(i).getTest_name());
        }

        LaboratoryMenu();
    }
}
