import Interface.ListOfTestsDAO;
import Interface.RegisterTestDAO;
import Model.*;
import Service.*;


import java.util.List;
import java.util.Scanner;

public class Laboratory {

    private ListOfTestsDAO listOfTestsDAO;

    private RegisterTestService registerTestService=new RegisterTestService();

    private GenerateLabReports generateLabReports;

    private static Integer uid_input;

    private RegisterTestDAO registerTestDAO;

    public Laboratory(){};

    public Laboratory(ListOfTestsDAO listOfTestsDAO, RegisterTestDAO registerTestDAO){
        this.listOfTestsDAO = listOfTestsDAO;
        this.registerTestDAO = registerTestDAO;
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
//                        registerTestService.registerTest();
                        registerTestDAO.registerTest();
                        LaboratoryMenu();
                        break;

                    case 2 :
                        System.out.println("You selected CBC test.");
                        registerTestDAO.registerTest();
                        LaboratoryMenu();
                        break;

                    case 3 :
                        System.out.println("You selected uric-acid test.");
                        registerTestDAO.registerTest();
                        LaboratoryMenu();
                        break;

                    case 4 :
                        System.out.println("You selected urine test.");
                        registerTestDAO.registerTest();
                        LaboratoryMenu();
                        break;

                    case 5 :
                        System.out.println("Exited");
                        LaboratoryMenu();
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

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT, "Test Name"));

        for(int i =0;i<listOfTestsList.size();i++) {
            System.out.println(String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT, listOfTestsList.get(i).getTest_name()));
        }
    }

    public void listOfReports() {
        GenerateLabReportsService generateLabReportsService = new GenerateLabReportsService();
        List<GenerateLabReports> generateLabReportsList = generateLabReportsService.generateReports(uid_input);

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT,"User_id") + " " +
                String.format(Constant.STRING_FORMAT, "Firstname") + " " +
                String.format(Constant.STRING_FORMAT, "Lastname") + " " +
                String.format(Constant.STRING_FORMAT,"Test Name") + " " +
                String.format(Constant.STRING_FORMAT, "Contact") + " " +
                String.format(Constant.STRING_FORMAT, "Email")+ " " +
                String.format(Constant.STRING_FORMAT,"Gender")+ " " +
                String.format(Constant.STRING_FORMAT,"Date of test") + " " +
                String.format(Constant.STRING_FORMAT,"Report Generation Date"));

        for(int i = 0; i < generateLabReportsList.size(); i++) {
            System.out.println(String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getTest_id()) + " "+ String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getUser_id())
            +" " + String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getFirstname())+" "+String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getLastname())
            +" " +String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getTest_name())+" "+String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getContact())+" "
            + String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getEmail())+String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getGender()) + " "
            + String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getDate_of_test())+" "+String.format(Constant.STRING_FORMAT,generateLabReportsList.get(i).getReport_generation_date()));

//            if(!generateLabReportsList.contains(generateLabReportsList.get(i).getTest_id())) {
//                System.out.println("Invalid input");
//                return;
//            }
        }
    }

    public void bloodReport() {
        BloodTestReportsService bloodTestReportsService = new BloodTestReportsService();
        List<BloodTestReports> bloodTestReportsList = bloodTestReportsService.bloodTestReport(uid_input);

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT,"User_id") + " " +
                String.format(Constant.STRING_FORMAT, "Blood Group") + " " +
                String.format(Constant.STRING_FORMAT, "White Blood Cells") + " " +
                String.format(Constant.STRING_FORMAT,"Platelet Count") + " " +
                String.format(Constant.STRING_FORMAT, "Blood Cell Count") + " " +
                String.format(Constant.STRING_FORMAT, "Hemoglobin")+ " " +
                String.format(Constant.STRING_FORMAT,"Hematocrit"));

        for(int i = 0; i < bloodTestReportsList.size(); i++) {
            System.out.println(String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getUser_id())
            + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getBlood_group()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getWhite_blood_cell_count())
            + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getPlatelet_count()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getRed_blood_cell_count())
            + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getHemoglobin()) + " " + String.format(Constant.STRING_FORMAT,bloodTestReportsList.get(i).getHematocrit()));
        }

//        bloodTestReportsService.generatePDF("hello");

        LaboratoryMenu();
    }

    public void vitaminDReport() {
      VitaminDTestReportsService vitaminDTestReportsService = new VitaminDTestReportsService();
      List<VitaminDTestReports> vitaminDTestReportsList = vitaminDTestReportsService.vitaminDReports(uid_input);

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT,"User_id") + " " +
                String.format(Constant.STRING_FORMAT, "Hydroxy VitaminD Serum") + " " +
                String.format(Constant.STRING_FORMAT, "Units"));

      for(int i = 0 ; i < vitaminDTestReportsList.size(); i++) {
          System.out.println(String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getUser_id())
          + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getHydroxy_VitaminD_serum()) + " " + String.format(Constant.STRING_FORMAT,vitaminDTestReportsList.get(i).getUnits()));
      }
      LaboratoryMenu();
    }

    public void urineReports() {
        UrineTestReportsService urineTestReportsService = new UrineTestReportsService();
        List<UrineTestReports> urineTestReportsList = urineTestReportsService.urineReports(uid_input);

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT,"User_id") + " " +
                String.format(Constant.STRING_FORMAT, "Colour") + " " +
                String.format(Constant.STRING_FORMAT, "Specific Gravity") + " " +
                String.format(Constant.STRING_FORMAT,"pH") + " " +
                String.format(Constant.STRING_FORMAT, "Blood") + " " +
                String.format(Constant.STRING_FORMAT, "Glucose")+ " " +
                String.format(Constant.STRING_FORMAT,"Urobilinogen")+ " " +
                String.format(Constant.STRING_FORMAT,"Protein") + " " +
                String.format(Constant.STRING_FORMAT,"Red blood cells") + " " +
                String.format(Constant.STRING_FORMAT,"Pus Cells") + " " +
                String.format(Constant.STRING_FORMAT,"Crystals") + " " +
                String.format(Constant.STRING_FORMAT,"Casts") + " " +
                String.format(Constant.STRING_FORMAT,"Turbidity") + " " +
                String.format(Constant.STRING_FORMAT,"White blood cells"));

        for(int i = 0; i < urineTestReportsList.size(); i++) {
            System.out.println(String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getUser_id())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getColor()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getSpecific_gravity())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getpH()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getBlood())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getGlucose()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getUrobilinogen())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getProtein()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getRbc())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getPus_cells()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getCrystals())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getCasts()) + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getTurbidity())
            + " " + String.format(Constant.STRING_FORMAT,urineTestReportsList.get(i).getWbc()));
        }
        LaboratoryMenu();
    }

    public void uric_acidReports() {
      UricAcidTestReportsService uricAcidTestReportsService = new UricAcidTestReportsService();
      List<UricAcidTestReports> uricAcidTestReportsList = uricAcidTestReportsService.uricacidReports(uid_input);

        System.out.println(String.format(Constant.STRING_FORMAT,"Test_id") + " " +
                String.format(Constant.STRING_FORMAT,"User_id") + " " +
                String.format(Constant.STRING_FORMAT, "Serum Uric Acid: %f") + " " +
                String.format(Constant.STRING_FORMAT, "Units"));

      for(int i = 0; i < uricAcidTestReportsList.size(); i++) {
        System.out.println(String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getTest_id()) + " " + String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getUser_id())
        + " " + String.format(Constant.STRING_FORMAT,uricAcidTestReportsList.get(i).getSerum_uric_acid()) + " " + String.format(uricAcidTestReportsList.get(i).getUnits(),uricAcidTestReportsList.get(i).getUnits()));
      }
      LaboratoryMenu();
    }
}
