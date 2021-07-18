package Model;

public class DoctorRating {

    private Integer id;

    private Integer doctor_id;

    private Double rating;

    private Integer count;

    private Double total;

    public DoctorRating(){}

    public DoctorRating(Integer doctor_id, Double rating, Integer count, Double total) {
        this.doctor_id = doctor_id;
        this.rating = rating;
        this.count = count;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
