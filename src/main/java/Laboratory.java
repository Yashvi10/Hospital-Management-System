import Interface.ListOfTestsDAO;
import Model.*;
import Service.*;


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
                System.out.println("\n"+"Press 1 for Vitamin D test\nPress 2 for Complete Blood Count test\nPress 3 for uric-acid test\n" +
                        "Press 4 for urine test\nPress 5 to exit");

                Scanner test_input = new Scanner(System.in);
                int test_choice = test_input.nextInt();

                switch (test_choice){
                    case 1 :
                        System.out.println("You selected Vitamin D checkup test.");
                        registerTestService.registerTest();
                        LaboratoryMenu();
                        break;

                    case 2 :
                        System.out.println("You selected CBC test.");
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
                        System.out.println("report of Vitamin D test");
                        vitaminDReport();
                        break;
                    case 2 :
                        System.out.println("report of CBC test");
                        bloodReport();

//                        BloodTestReportsService bloodTestReportsService = new BloodTestReportsService();
//                        bloodTestReportsService.generatePDF();
                        break;
                    case 3 :
                        System.out.println("report of uric-acid test");
                        uric_acidReports();
                        break;
                    case 4 :
                        System.out.println("report of urine test");
                        urineReports();
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

//            if(!generateLabReportsList.contains(generateLabReportsList.get(i).getTest_id())) {
//                System.out.println("Invalid input");
//                return;
//            }
        }
    }

    public void bloodReport() {
        BloodTestReportsService bloodTestReportsService = new BloodTestReportsService();
        List<BloodTestReports> bloodTestReportsList = bloodTestReportsService.bloodTestReport(uid_input);

        for(int i = 0; i < bloodTestReportsList.size(); i++) {
            System.out.println(bloodTestReportsList.get(i).getTest_id() + " " + bloodTestReportsList.get(i).getUser_id()
            + " " + bloodTestReportsList.get(i).getBlood_group() + " " + bloodTestReportsList.get(i).getWhite_blood_cell_count()
            + " " + bloodTestReportsList.get(i).getPlatelet_count() + " " + bloodTestReportsList.get(i).getRed_blood_cell_count()
            + " " + bloodTestReportsList.get(i).getHemoglobin() + " " + bloodTestReportsList.get(i).getHematocrit());
        }
        LaboratoryMenu();
    }

    public void vitaminDReport() {
      VitaminDTestReportsService vitaminDTestReportsService = new VitaminDTestReportsService();
      List<VitaminDTestReports> vitaminDTestReportsList = vitaminDTestReportsService.vitaminDReports(uid_input);

      for(int i = 0 ; i < vitaminDTestReportsList.size(); i++) {
          System.out.println(vitaminDTestReportsList.get(i).getTest_id() + " " + vitaminDTestReportsList.get(i).getUser_id()
          + " " + vitaminDTestReportsList.get(i).getHydroxy_VitaminD_serum() + " " + vitaminDTestReportsList.get(i).getUnits());
      }
      LaboratoryMenu();
    }

    public void urineReports() {
        UrineTestReportsService urineTestReportsService = new UrineTestReportsService();
        List<UrineTestReports> urineTestReportsList = urineTestReportsService.urineReports(uid_input);

        for(int i = 0; i < urineTestReportsList.size(); i++) {
            System.out.println(urineTestReportsList.get(i).getTest_id() + " " + urineTestReportsList.get(i).getUser_id()
            + " " + urineTestReportsList.get(i).getColor() + " " + urineTestReportsList.get(i).getSpecific_gravity()
            + " " + urineTestReportsList.get(i).getpH() + " " + urineTestReportsList.get(i).getBlood()
            + " " + urineTestReportsList.get(i).getGlucose() + " " + urineTestReportsList.get(i).getUrobilinogen()
            + " " + urineTestReportsList.get(i).getProtein() + " " + urineTestReportsList.get(i).getRbc()
            + " " + urineTestReportsList.get(i).getPus_cells() + " " + urineTestReportsList.get(i).getCrystals()
            + " " + urineTestReportsList.get(i).getCasts() + " " + urineTestReportsList.get(i).getTurbidity()
            + " " + urineTestReportsList.get(i).getWbc());
        }
        LaboratoryMenu();
    }

    public void uric_acidReports() {
      UricAcidTestReportsService uricAcidTestReportsService = new UricAcidTestReportsService();
      List<UricAcidTestReports> uricAcidTestReportsList = uricAcidTestReportsService.uricacidReports(uid_input);

      for(int i = 0; i < uricAcidTestReportsList.size(); i++) {
        System.out.println(uricAcidTestReportsList.get(i).getTest_id() + " " + uricAcidTestReportsList.get(i).getUser_id()
        + " " + uricAcidTestReportsList.get(i).getSerum_uric_acid() + " " + uricAcidTestReportsList.get(i).getUnits());
      }
      LaboratoryMenu();
    }
}
