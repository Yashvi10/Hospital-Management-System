package View;

import BL.MedicalHistoryBL;
import Interface.FeatureMenu;
import Service.MedicalHistoryService;

import java.util.Scanner;

/*
 *  Name of file: MedicalHistoryMenu.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This class shows the main menu for navigating through services offered by medical history feature
 *  Description: This class contains menu to navigate through the services such as view, upload and download medical history
 *  and call the services respectively
 * */

public class MedicalHistoryMenu implements FeatureMenu {

  public static void main(String[] args) {
    MedicalHistoryMenu medicalHistoryMenu = new MedicalHistoryMenu();
    medicalHistoryMenu.menu();
  }

  @Override
  public void menu() {
    System.out.println("*************************************");
    System.out.println("Press v to View your medical records\n" +
            "Press d to Download your medical records\n" +
            "Press u to Upload your medical records");
    System.out.println("*************************************");

    Scanner scanner = new Scanner(System.in);
    String inputFromUser = scanner.nextLine();

    if (inputFromUser.equals("v") || inputFromUser.equals("V")){
      view_medical_history();
    }else if (inputFromUser.equals("d") || inputFromUser.equals("D")){
      download_medical_history();
    }else if (inputFromUser.equals("u") || inputFromUser.equals("U")){
      upload_medical_history();
    } else {
      invalid();
    }
  }

  private void upload_medical_history() {

    MedicalHistoryService medicalHistoryService = new MedicalHistoryService();
    System.out.println("Please enter filepath which you want to upload:");
    Scanner scanner = new Scanner(System.in);
    String path = scanner.nextLine();
    System.out.println("Please enter name of document which you want to upload:");
    String document_name = scanner.nextLine();
    if (medicalHistoryService.upload_medical_history(path, document_name)){
      System.out.println("Uploaded successfully!");
      menu();
    }else {
      System.out.println("There was an error while uploading the file. Please try again!");
      menu();
    }

  }

  private void download_medical_history() {

    MedicalHistoryService medicalHistoryService = new MedicalHistoryService();
    if (medicalHistoryService.download_medical_history()) {
      System.out.println("Please enter document_id which you want to download:");
      Scanner scanner = new Scanner(System.in);
      Integer document_id = scanner.nextInt();
      MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
      if (!medicalHistoryBL.view_medical_history(document_id)) {
        System.out.println("There was an error while downloading the file. Please try again!");
      } else {
        medicalHistoryService.validateDocumentIdForDownload(document_id);
        System.out.println("Downloaded successfully!");
      }
    }
    menu();

  }

  private void view_medical_history() {

    MedicalHistoryService medicalHistoryService = new MedicalHistoryService();
    if (medicalHistoryService.view_medical_history()){
      System.out.println("Please enter document_id which you want to view:");
      Scanner scanner = new Scanner(System.in);
      Integer document_id = scanner.nextInt();
      MedicalHistoryBL medicalHistoryBL = new MedicalHistoryBL();
      if (!medicalHistoryBL.view_medical_history(document_id)){
        System.out.println("Something was wrong!");
      }
    }
    menu();

  }

  public void invalid(){
    System.out.println("Invalid input!");
  }

}
