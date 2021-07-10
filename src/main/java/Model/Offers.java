package Model;

/*
 *  Name of file: Offers.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */
public class Offers {

    private Integer offer_id;

    private String name;

    private Double discount;

    private Boolean isActive;

    public Offers(){}

    public Offers(String name, Double discount)  {
        this.name = name;
        this.discount = discount;
    }

    public Offers(String name, Double discount, Boolean isActive)  {
        this.name = name;
        this.discount = discount;
        this.isActive = isActive;
    }

    public Integer getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Integer offer_id) {
        this.offer_id = offer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
