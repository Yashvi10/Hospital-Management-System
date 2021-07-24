package View;

import Interface.FeatureMenu;
import Interface.HelpDeskFaqBLInterface;
import Interface.HelpDeskRequestRegisterBLInterface;
import Interface.IDashboard;
import Model.HelpDeskFaq;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: HelpDeskPage.java
 *  Author:  Kushang Mistry
 *  Purpose: The primary class which provides all Help Desk related options to end-user
 *  Description: The class calls methods based on user's choice
 *               Sub-menu (Help Desk menu) iterates itself until user selects
 * */
public class HelpDeskPage implements FeatureMenu {

  private HelpDeskFaqBLInterface helpDeskFaqBLInterface;

  private HelpDeskRequestRegisterBLInterface helpDeskRequestRegisterBLInterface;

  private IDashboard dashboard;

  private List<HelpDeskFaq> helpDeskFaqs;

  static Scanner userInput;

  public HelpDeskPage () {}

  public HelpDeskPage (HelpDeskFaqBLInterface helpDeskFaqBLInterface,
                       HelpDeskRequestRegisterBLInterface helpDeskRequestRegisterBLInterface,
                       IDashboard dashboard) {
    this.helpDeskFaqBLInterface = helpDeskFaqBLInterface;
    this.helpDeskRequestRegisterBLInterface = helpDeskRequestRegisterBLInterface;
    this.dashboard = dashboard;
  }

  // Method which is responsible to call Help Desk Menu (Sub-menu of the system)
  @Override
  public void menu() throws IOException {

    System.out.println("\n==========================");
    System.out.println("Select options from below");
    System.out.println("==========================");

    System.out.println("1 = View General FAQ");
    System.out.println("2 = To register a request");
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
          System.out.println("------------------------------------------------");
          System.out.println("\t\t\tAll FAQs are as follows");
          System.out.println("------------------------------------------------");

          helpDeskFaqs = helpDeskFaqBLInterface.getFaqData();

          if (helpDeskFaqs.size() != 0) {
            for (int i = 0; i < helpDeskFaqs.size(); i++) {
              System.out.println("\nQuestion: "+(i+1)+"\t"+helpDeskFaqs.get(i).getQuestion());
              System.out.println("Answer:\t\t"+helpDeskFaqs.get(i).getAnswer());
            }
            System.out.println("------------------------------------------------");
          }
          break;

        case 2 :
          System.out.println("------------------------------------------------");
          System.out.println("\t\t\tPlease register your concerns here");
          System.out.println("------------------------------------------------");

          String description = getDescription();
          if(description == null) {
            System.out.println("Description can not be null");
            break;
          }

          if(!helpDeskRequestRegisterBLInterface.getRequestInformation(description)) {
            System.out.println("There was problem registering your request - either staff is unavailable or your request is not registered");
            break;
          } else {
            System.out.println("Your concerns are very important to us - We noted your request, our staff will contact you shortly");
          }
          break;
        default:
          System.out.println("Invalid Input, Please select right option");
          break;
      }
      System.out.println("\n\n==========================");
      System.out.println("Select options from below");
      System.out.println("==========================");

      System.out.println("1 = View General FAQ");
      System.out.println("2 = To register a request");
      System.out.println("\nPress 0 (Zero) for main menu");
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

  private String getDescription() {

    String description=null;

    userInput = new Scanner(System.in);
    System.out.println("Enter your concern: ");
    description = userInput.nextLine();

    description = description.trim().toLowerCase();

    if(validateDescription(description))
      return description;
    else
      return null;
  }

  public Boolean validateDescription(String description) {
    if (description != null && !description.equals("") && description.length() < 500)
      return true;
    else
      return false;
  }
}
