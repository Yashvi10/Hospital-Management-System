package Model;

public class OrderItem {
    private Integer id;

    private Integer pid;

    private String name;

    private Integer qty;

    private Double price;

    private Double total_bill;

    private Double final_bill;

    private Integer order_id;

    public OrderItem(){}
    public OrderItem(Integer pid, String name, Integer qty, Double price,
                     Double total_bill,Double final_bill, Integer order_id)  {
        this.pid = pid;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total_bill = total_bill;
        this.final_bill = final_bill;
        this.order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal_bill() {
        return total_bill;
    }

    public void setTotal_bill(Double total_bill) {
        this.total_bill = total_bill;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Double getFinal_bill() {
        return final_bill;
    }

    public void setFinal_bill(Double final_bill) {
        this.final_bill = final_bill;
    }
}
