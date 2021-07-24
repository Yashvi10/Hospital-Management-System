package Model;

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
