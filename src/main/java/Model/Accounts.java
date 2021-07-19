package Model;

/*
 *  Name of file: Accounts.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class maps the Accounts with DB models
 *  Description: This class will set and get the variables for managing the expenses
 * */

import Enum.EnumExpenseType;
import Enum.EnumAccountType;
import java.sql.Date;

public class Accounts {

  private Integer id;
  private int accountType;
  private String payName;
  private Double amount;
  private Date date;
  private int expenseType;


  Accounts(Integer id, int accountType, String payName, Double amount, Date date, int expenseType){
    this.id=id;
    this.accountType=accountType;
    this.payName=payName;
    this.date=date;
    this.expenseType = expenseType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getAccountType() {
    return accountType;
  }

  public void setAccountType(int accountType) {
    this.accountType = accountType;
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getExpenseType() {
    return expenseType;
  }

  public void setExpenseType(int expenseType) {
    this.expenseType = expenseType;
  }
}
