package Model;

public class Pharmacy  {

    private Integer p_id;

    private String product_name;

    private Double price;

    private Integer stock;

    private String expiry_date;

    public Pharmacy(){}

    public Pharmacy(Integer p_id, String product_name, Double price, Integer stock,
                    String expiry_date)  {
        this.p_id = p_id;
        this.product_name = product_name;
        this.price = price;
        this.stock = stock;
        this.expiry_date = expiry_date;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
