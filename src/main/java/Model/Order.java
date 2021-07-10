package Model;

/*
 *  Name of file: Order.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like mapper help us in interacting with DB models
 *  Description: This class will behaves like a mapper so it will be easy when
 *               we are working with DB by using getter and setter methods
 * */
public class Order  {

    private Integer order_id;

    private Integer user_id;

    public Order(Integer user_id)  {
        this.user_id = user_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}

