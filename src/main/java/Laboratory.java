import Interface.ListOfTestsDAO;
import Model.GenerateLabReports;
import Model.ListOfTests;
import Model.RegisterTest;
import Service.GenerateLabReportsService;
import Service.RegisterTestService;


import java.util.List;
import java.util.Scanner;

public class Laboratory {

    private ListOfTestsDAO listOfTestsDAO;

    private RegisterTestService registerTestService=new RegisterTestService();

    private GenerateLabReports generateLabReports;

    private static Integer uid_input;

    public Laboratory(){};

    public Laboratory(ListOfTestsDAO listOfTestsDAO){
        this.listOfTestsDAO = listOfTestsDAO;
    }

//    public Laboratory(RegisterTestService registerTestService) {
//        this.registerTestService = registerTestService;
//    }

    public Laboratory(GenerateLabReports generateLabReports) {
        this.generateLabReports = generateLabReports;
    }

    public void LaboratoryMenu(){

        System.out.println("===============================");
        System.out.println("Press 1 to register for test\nPress 2 for Listing all Tests\nPress 3 for Generating your test reports\n" +
                "Press 4 to exit");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch(choice){
            case 1 :
                System.out.println("============Test Registration============");
                System.out.println("**List of available tests**");
                listOfTests();
                System.out.println("\n"+"Press 1 for sugar test\nPress 2 for blood test\nPress 3 for uric-acid test\n" +
                        "Press 4 for urine test\nPress 5 to exit");

                Scanner test_input = new Scanner(System.in);
                int test_choice = test_input.nextInt();

                switch (test_choice){
                    case 1 :
                        System.out.println("You selected sugar test.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 2 :
                        System.out.println("You selected blood test.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 3 :
                        System.out.println("You selected uric-acid test.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 4 :
                        System.out.println("You selected urine test.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 5 :
                        System.out.println("Exited");
                        break;
                }

                break;

            case 2 :
                System.out.println("============List of all tests============");
                listOfTests();
                LaboratoryMenu();
                break;

            case 3 :
                System.out.println("Generate reports");
                System.out.println("Enter your user_id");
                Scanner user_id = new Scanner(System.in);
                uid_input = user_id.nextInt();
                listOfReports();

                System.out.println("\n"+"Press 1 for test_id 1\nPress 2 for test_id 2\nPress 3 for test_id 3\n" +
                        "Press 4 for test_id 4\nPress 5 to exit");

                Scanner report = new Scanner(System.in);
                Integer report_input = report.nextInt();

                switch (report_input) {
                    case 1 :
                        System.out.println("report of sugar test");
                        break;
                    case 2 :
                        System.out.println("report of blood test");
                        break;
                    case 3 :
                        System.out.println("report of uric-acid test");
                        break;
                    case 4 :
                        System.out.println("report of urine test");
                        break;
                    case 5 :
                        System.out.println("Exited");
                        break;
                }
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

        List<ListOfTests> listOfTestsList = listOfTestsDAO.getListOfTests();

        for(int i =0;i<listOfTestsList.size();i++) {
            System.out.println(listOfTestsList.get(i).getTest_id() + " " + listOfTestsList.get(i).getTest_name());
        }
    }

    public void listOfReports() {
        GenerateLabReportsService generateLabReportsService = new GenerateLabReportsService();
        List<GenerateLabReports> generateLabReportsList = generateLabReportsService.generateReports(uid_input);

        for(int i = 0; i < generateLabReportsList.size(); i++) {
            System.out.println(generateLabReportsList.get(i).getTest_id() + " "+ generateLabReportsList.get(i).getUser_id()
            +" " + generateLabReportsList.get(i).getFirstname()+" "+generateLabReportsList.get(i).getLastname()
            +" " +generateLabReportsList.get(i).getTest_name()+" "+generateLabReportsList.get(i).getContact()+" "
            + generateLabReportsList.get(i).getEmail()+generateLabReportsList.get(i).getGender() + " "
            + generateLabReportsList.get(i).getDate_of_test()+" "+generateLabReportsList.get(i).getReport_generation_date());
        }
    }

}
