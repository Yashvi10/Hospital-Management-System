import Interface.ListOfTestsDAO;
import Model.ListOfTests;
import Service.RegisterTestService;


import java.util.List;
import java.util.Scanner;

public class Laboratory {

    private ListOfTestsDAO listOfTestsDAO;

    private RegisterTestService registerTestService=new RegisterTestService();

    public Laboratory(){};

    public Laboratory(ListOfTestsDAO listOfTestsDAO){
        this.listOfTestsDAO = listOfTestsDAO;
    }

//    public Laboratory(RegisterTestService registerTestService) {
//        this.registerTestService = registerTestService;
//    }

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
                        System.out.println("You selected test for sugar checkup.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 2 :
                        System.out.println("Blood");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 3 :
                        System.out.println("uric acid");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 4 :
                        System.out.println("urine");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 5 :
                        System.out.println("Exited");
                        registerTestService.registerTest();
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

//        LaboratoryMenu();
    }

}
