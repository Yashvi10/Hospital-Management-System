package Service;

import java.util.Scanner;

/*
 *  Name of file: AccountsMenu.java
 *  Author:  Abimbola Babalola
 *  Purpose & Description: This class highlights the Income and expense operation
 *                         to be performed
 * */

public class AccountSelectionService {

 Scanner sc = new Scanner(System.in);
 int accType;

 public int AccountType() {
  System.out.println("Press 1: Income\nPress 2: Expense\nPress 0: Exit");
  if (sc.hasNextInt()) {
   accType = sc.nextInt();
   sc.nextLine();
  } else {
   accType = -1;
   sc.next();
  }
  return accType;
 }

 public void AccountMenuType() {
  System.out.println("Press 1: ViewAll Records\nPress 2: View Period Records\nPress 3: Add New Record" +
          "\nPress 4: Delete Record\nPress 5: Change Account Type\nPress 0: Exit");
  accType = sc.nextInt();
  switch (accType) {
   case 1:
   case 2:
   case 3:
   case 4:
   case 5:

   default:
    System.out.println("Invalid Entry. Try again.");
    break;

  }

 }
}
