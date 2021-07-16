package Model;

/*
 *  Name of file: OrderItem.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class maps the Account with DB models
 *  Description: This class will set and get the variables for managing the expenses
 * */

import java.sql.Date;

public class Account {

  private Integer id;
  private String accountType;
  private String payName;
  private Double amount;
  private Date date;

  Account(Integer id, String accountType, String payName, Double amount, Date date){
    this.id=id;
    this.accountType=accountType;
    this.payName=payName;
    this.date=date;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
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
}
