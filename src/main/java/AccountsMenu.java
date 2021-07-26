import Interface.FeatureMenu;
import Interface.IDateValidation;
import Interface.IPrint;
import Model.Accounts;
import Service.CustomConnection;
import Service.ManageAccountService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class is a presentation layer. It implement the FeatureMenu and is responsible for
 *               showing different menus related to Accounts module. It uses the IPrint interface to present its
 *               output to the userIt implements the single responsibility principle by extending the
 *               ManageAccountService class.
 * */

public class AccountsMenu extends ManageAccountService implements FeatureMenu, IPrint, IDateValidation {

  Scanner sc = new Scanner(System.in);
  Accounts account=new Accounts("",0.00,"",0);
  CustomConnection conn = new CustomConnection();

  @Override
  public void menu( ) {

    int menuOption;
    int accType;

    System.out.println("******Accounts Menu******");
    do {
      System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
      accType = sc.nextInt();
      switch (accType) {
        case 1:
          printRecord(getIncome(conn));
          System.out.println();
          break;
        case 2:
          do {
            System.out.println("***********************");
            System.out.println("Press 1: View Expenses\nPress 2: Add Expenses \nPress 0: Exit");
            menuOption = sc.nextInt();
            switch (menuOption) {
              case 1:
                printRecord(getExpenses(conn));
                break;
              case 2:
                printOutput(addExpense(conn,userInput()));
                break;
              case 0:
              default:
                break;
            }
          }
          while (menuOption != 0);

        case 0:
        default:
          break;
      }

    } while (accType != 0);

  }

  @Override
  public void printOutput(boolean response) {
    if(response){
      System.out.println("Successful.");
    }
    else{
      System.out.println("Failed.");
    }

  }

  @Override
  public void printRecord(List<List<String>> rows) {
    for(List<String> record: rows){
      for(String col: record){
        System.out.print(col+" || ");
      }
      System.out.println( );
    }
  }

  @Override
  public boolean validateDate(String date){
    boolean dateCheck=false;
    if(!date.trim().equals("")){
      SimpleDateFormat format=new SimpleDateFormat( "dd/MM/yyyy");
      format.setLenient(false);
      try{
        Date recordDate=format.parse(date);
        dateCheck=true;
      }
      catch( ParseException e){
        e.getMessage();
      }
    }
    return dateCheck;
  }

  public Accounts userInput( ) {
    Scanner scan=new Scanner(System.in);
    System.out.println("Provide Expense Payment Details" );
    System.out.println("Enter Receiver Name: ");
    String payName=scan.nextLine() ;
    String date;
    boolean checkResult;
    do {
      System.out.println("Enter Payment Date (dd/MM/yyyy): ");
      date = scan.nextLine();
      checkResult=validateDate(date);
      if (!checkResult){
        System.out.println("Invalid Date")  ;
      }
    }
    while(!checkResult );

    int expenseType;
    do{
      System.out.println("Enter Payment purpose\nPress 1 for Maintenance\nPress 2 for Supplies\nPress 3 for Salary" );
      expenseType=scan.nextInt() ;
    }
    while((expenseType!=1) &&(expenseType!=2)&&(expenseType!=3));

    System.out.println("Enter Amount: ");
    Double amount=scan.nextDouble() ;
    account=new Accounts( payName,amount,date,expenseType);
    return account;
  }
}
