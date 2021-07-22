package Model;

/*
 *  Name of file: Accounts.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class maps the Accounts with DB models
 *  Description: This class will set and get the variables for managing the expenses
 * */


public class Accounts {

  private String payName;
  private Double amount;
  private String date;
  private int expenseType;


  public Accounts( String payName, Double amount, String date, int expenseType){
    this.payName=payName;
    this.amount=amount;
    this.date=date;
    this.expenseType = expenseType;
  }

  public String getPayName() {
    return payName;
  }

  public void setPayName(String payName) {
    this.payName = payName;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getExpenseType() {
    return expenseType;
  }

  public void setExpenseType(int expenseType) {
    this.expenseType = expenseType;
  }
}
