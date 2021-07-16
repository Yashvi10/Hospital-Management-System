package Model;

/*
 *  Name of file: Accounts.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class maps the Accounts with DB models
 *  Description: This class will set and get the variables for managing the expenses
 * */

import java.sql.Date;

public class Accounts {

  private Integer id;
  private String accountType;
  private String category;
  private String payName;
  private Double amount;
  private Date date;

  Accounts(Integer id, String accountType, String category, String payName, Double amount, Date date){
    this.id=id;
    this.accountType=accountType;
    this.category=category;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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
