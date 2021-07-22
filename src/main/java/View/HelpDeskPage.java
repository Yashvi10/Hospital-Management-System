package View;

import Interface.FeatureMenu;
import Interface.HelpDeskFaqBLInterface;
import Model.HelpDeskFaq;
import Model.Vaccine;
import Service.UserSession;

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

  private List<HelpDeskFaq> helpDeskFaqs;

  public HelpDeskPage (HelpDeskFaqBLInterface helpDeskFaqBLInterface) {
    this.helpDeskFaqBLInterface = helpDeskFaqBLInterface;
  }

  // Method which is responsible to call Help Desk Menu (Sub-menu of the system)
  @Override
  public void menu() {

    System.out.println("\n==========================");
    System.out.println("Select options from below");
    System.out.println("==========================");

    System.out.println("1 = View General FAQ");
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

        default:
          System.out.println("Invalid Input, Please select right option");
          break;
      }
      System.out.println("\n\n==========================");
      System.out.println("Select options from below");
      System.out.println("==========================");

      System.out.println("1 = View General FAQ");
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
