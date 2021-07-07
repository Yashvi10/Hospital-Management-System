package Model;

/*
 *  Name of file: CartItem.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */
public class CartItem  {

    private String name;

    private Integer qty;

    private Double price;

    private Double totalPrice;

    private Double finalPrice;

    public CartItem(){}
    public CartItem(String name, Integer qty, Double price, Double totalPrice)  {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
